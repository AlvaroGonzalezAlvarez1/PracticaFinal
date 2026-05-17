package Pruebas.Interacciones;

import Pruebas.Personajes.Jugador;

public abstract class Interactuable {
    protected int x;
    protected int y;
    protected int habitacion;

    public Interactuable(int habitacion,int x,int y) {
        this.habitacion=habitacion;
        this.x=x;
        this.y=y;
    }

    public abstract void interactuar(Jugador jugador);

    public abstract String getSprite();

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getHabitacion(){
        return habitacion;
    }

    public int getOffsetX() {
        return 0;
    }

    public int getOffsetY() {
        return 0;
    }
}
