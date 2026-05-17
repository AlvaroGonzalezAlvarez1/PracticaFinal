package Pruebas.Interactuable.Acciones;

import Pruebas.Interactuable.NPC;
import Pruebas.Personajes.Jugador;

public interface AccionNPC {
    void ejecutar(Jugador jugador,NPC npc);
}