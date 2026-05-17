package Pruebas.Interactuable.Acciones;

import Pruebas.Interactuable.NPC;
import Pruebas.Objetos.Objeto;
import Pruebas.Personajes.Jugador;

public class AccionDarObjeto implements AccionNPC {

    private Objeto objeto;
    private boolean entregado;

    public AccionDarObjeto(Objeto objeto) {
        this.objeto=objeto;
        entregado=false;
    }

    @Override
    public void ejecutar(Jugador jugador, NPC npc) {
        if(entregado==false){
            jugador.anadirObjeto(objeto);
            System.out.println("Has recibido: "+objeto.getNombre());
            entregado=true;
        }
        npc.siguienteFase();
    }

    public String getMensaje(){
        return "Has recibido: "+objeto.getNombre();
    }
}