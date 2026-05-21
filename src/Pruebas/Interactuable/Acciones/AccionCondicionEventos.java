package Pruebas.Interactuable.Acciones;

import Pruebas.Interactuable.NPC;
import Pruebas.Personajes.Jugador.Jugador;

public class AccionCondicionEventos implements AccionNPC {

    private String[] eventos;

    private String mensajeTodos;
    private String mensajeNinguno;
    private String mensajeAlgunos;

    public AccionCondicionEventos(String[] eventos,String mensajeTodos,String mensajeNinguno,String mensajeAlgunos) {
        this.eventos=eventos;
        this.mensajeTodos=mensajeTodos;
        this.mensajeNinguno=mensajeNinguno;
        this.mensajeAlgunos=mensajeAlgunos;
    }

    @Override
    public void ejecutar(Jugador jugador,NPC npc) {
        int contador=0;
        for(int i=0; i<eventos.length; i++) {
            if (jugador.tieneEvento(eventos[i])==true) {
                contador++;
            }
        }
        String mensaje;
        if (contador==0) {
            mensaje=mensajeNinguno;
        }
        else if (contador==eventos.length) {
            mensaje=mensajeTodos;
        }
        else {
            mensaje=mensajeAlgunos;
        }
        System.out.println(npc.getNombre() + ": "+mensaje);
        if (contador==eventos.length) {
            npc.siguienteFase();//Solo pasa al siguiente estado si han ocurrido todos los eventos
            npc.interactuar(jugador);
        }
    }
}