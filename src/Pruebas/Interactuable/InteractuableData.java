package Pruebas.Interactuable;

import Pruebas.Interactuable.Acciones.AccionData;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.ObjetoData;

public class InteractuableData {

    public String tipo;

    public int habitacion;
    public int x;
    public int y;

    // NPC
    public String nombre;
    public String sprite;
    public int offsetX;
    public int offsetY;
    public AccionData[] acciones;

    // COFRE
    public ObjetoData objeto;
}