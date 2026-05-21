package Pruebas.Interactuable;

import Estructuras.IndexedList;
import Pruebas.Interactuable.Acciones.AccionNPC;
import Pruebas.Personajes.Jugador.Jugador;

public class NPC extends Interactuable {

    private String nombre;

    private int offsetX;
    private int offsetY;
    private String sprite;

    private IndexedList<AccionNPC> acciones;
    private int faseActual;
    private boolean primerContacto;


    public NPC(String nombre,String sprite,int hab,int x,int y,int offsetX,int offsetY) {
        super(hab,x,y);
        this.nombre=nombre;
        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.sprite=sprite;
        acciones=new IndexedList<>();
        faseActual=0;
        primerContacto=true;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void interactuar(Jugador jugador) {
        int index=faseActual;
        if (index>=acciones.len()) {
            index=acciones.len()-1;//Siempre repite el ultimo dialogo
        }
        acciones.get(index).ejecutar(jugador,this);
        if(primerContacto==true){
            jugador.activarEvento("hablo_"+nombre);
            primerContacto=false;
        }
    }

    public void anadirAccion(AccionNPC accion){
        acciones.append(accion);
    }

    public void siguienteFase(){
        faseActual++;
    }

    @Override
    public String getSprite() {
        return "file:./src/sprites/"+sprite+".png";
    }

    @Override
    public int getOffsetX() {
        return offsetX;
    }

    @Override
    public int getOffsetY() {
        return offsetY;
    }
}