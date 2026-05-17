package Pruebas.Interactuable;

import Estructuras.GsonUtil;
import Estructuras.IndexedList;
import Pruebas.Interactuable.Acciones.*;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;

public class InteractuableLoader {

    public static IndexedList<Interactuable> cargarInteractuables() {
        IndexedList<Interactuable> resultado=new IndexedList<>();
        NPCData[] npcs = GsonUtil.cargarArray("src/DATOS/npcs.json",NPCData[].class);
        if (npcs!=null){
            for (NPCData d:npcs) {
                resultado.append(crearNPC(d));
            }
        }

        InteractuableData[] interactuables = GsonUtil.cargarArray("src/DATOS/interactuables.json",InteractuableData[].class);
        if (interactuables!=null) {
            for (InteractuableData d:interactuables) {
                resultado.append(crear(d));
            }
        }
        return resultado;
    }

    private static NPC crearNPC(NPCData data) {
        NPC npc=new NPC(data.nombre,data.sprite,data.habitacion,data.x,data.y,data.offsetX,data.offsetY);
        if (data.acciones!=null) {
            AccionNPC[] acciones=crearAcciones(data.acciones);
            for (AccionNPC a:acciones) {
                npc.anadirAccion(a);
            }
        }
        return npc;
    }

    private static Interactuable crear(InteractuableData data) {
        Interactuable result;
        switch (data.tipo) {
            case "cofre"->result=new Cofre(data.habitacion,data.x,data.y,new Objeto(data.objeto.nombre,reconocerTipoObjeto(data.objeto.tipo)));
            default->throw new IllegalArgumentException("Interactuable desconocido: "+data.tipo);
        }
        return result;
    }

    private static TipoObjeto reconocerTipoObjeto(String tipo) {
        TipoObjeto result;
        switch (tipo){
            case "ALETA"->result=TipoObjeto.ALETA;
            case "LLAVE"->result=TipoObjeto.LLAVE;
            default -> throw new IllegalArgumentException("Tipo de objeto desconocido: "+tipo);
        }
        return result;
    }

    private static AccionNPC[] crearAcciones(AccionData[] datos) {
        AccionNPC[] acciones=new AccionNPC[datos.length];
        for (int i=0;i<datos.length;i++) {
            AccionData data=datos[i];
            switch (data.tipo) {
                case "hablar"->acciones[i]=new AccionHablar(data.mensaje);
                case "darObjeto"->acciones[i]=new AccionDarObjeto(data.objeto);
                case "condicionEventosMensaje"->acciones[i]=new AccionCondicionEventos(data.eventos,data.mensajeTodos,data.mensajeNinguno,data.mensajeAlgunos);
                default -> throw new IllegalArgumentException("Acción desconocida: "+data.tipo);
            }
        }
        return acciones;
    }
}