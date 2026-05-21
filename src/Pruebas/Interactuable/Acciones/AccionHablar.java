package Pruebas.Interactuable.Acciones;

import Pruebas.Interactuable.NPC;
import Pruebas.Personajes.Jugador.Jugador;

public class AccionHablar implements AccionNPC {

    private String mensaje;

    public AccionHablar(String mensaje) {
        this.mensaje=mensaje;
    }

    @Override
    public void ejecutar(Jugador jugador, NPC npc) {
        System.out.println(npc.getNombre()+": "+mensaje);
        npc.siguienteFase();
    }

    public String getMensaje() {
        return mensaje;
    }
}