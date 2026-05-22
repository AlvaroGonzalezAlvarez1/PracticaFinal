package DATOS;

import Estructuras.GsonUtil;
import Pruebas.Interactuable.InteractuableLoader;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;
import Pruebas.Personajes.Jugador.Jugador;

public class GuardadoLoader {

    private static String getRuta(int slot){
        return "src/DATOS/GUARDADO/guardado_"+slot+".json";
    }

    public static void guardarPartida(Jugador jugador,int slot){
        DatosGuardado datos=new DatosGuardado();
        datos.x=jugador.getX();
        datos.y=jugador.getY();
        datos.habitacion=jugador.getHabitacionActual();
        //Inventario
        datos.inventario=new String[jugador.getInventario().len()];
        for(int i=0;i<jugador.getInventario().len();i++){
            datos.inventario[i]=jugador.getInventario().get(i).getTipo().name();
        }
        //Eventos
        datos.eventos=new String[jugador.getEventos().len()];
        for(int i=0;i<jugador.getEventos().len();i++){
            datos.eventos[i]=jugador.getEventos().get(i);
        }
        GsonUtil.guardarObjetoEnArchivo(getRuta(slot),datos);
    }

    public static void cargarPartida(Jugador jugador,int slot){
        DatosGuardado datos=GsonUtil.cargarObjetoDesdeArchivo(getRuta(slot),DatosGuardado.class);
        if(datos!=null){
            jugador.setX(datos.x);
            jugador.setY(datos.y);
            jugador.setHabitacionActual(datos.habitacion);
            jugador.limpiarInventario();
            if(datos.inventario!=null) {
                for (int i = 0; i < datos.inventario.length; i++) {
                    TipoObjeto tipo = InteractuableLoader.reconocerTipoObjeto(datos.inventario[i]);
                    jugador.anadirObjeto(new Objeto(tipo.name(), tipo));
                }
            }
            jugador.limpiarEventos();
            if(datos.inventario!=null){
                for(int i=0;i<datos.eventos.length;i++){
                    jugador.activarEvento(datos.eventos[i]);
                }
            }
        }
    }
}