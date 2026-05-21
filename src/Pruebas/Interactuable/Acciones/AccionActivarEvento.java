package Pruebas.Interactuable.Acciones;

import Pruebas.Interactuable.NPC;
import Pruebas.Personajes.Jugador.Jugador;


//Implementado de otra manera, no se quita por si se usa en un futuro

public class AccionActivarEvento implements AccionNPC {
    private String evento;

    public AccionActivarEvento(String evento) {
        this.evento=evento;
    }

    @Override
    public void ejecutar(Jugador jugador,NPC npc) {
        jugador.activarEvento(evento);
        npc.siguienteFase();
    }

    public String getEvento() {
        return evento;
    }
}