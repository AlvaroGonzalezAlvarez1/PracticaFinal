package Pruebas.Personajes;

import Pruebas.Mapa.*;

public class Jugador {

    private int x;
    private int y;
    private int habitacionActual;

    public Jugador(int x,int y,int habitacionInicial) {
        this.x=x;
        this.y=y;
        this.habitacionActual=habitacionInicial;
    }

    public void mover(int dx, int dy, Mapa mapa) {
        Habitacion h=mapa.getHabitacion(habitacionActual);
        int nx=x+dx;
        int ny=y+dy;
        if(h.esTransitable(nx,ny)==true) {
            x=nx;
            y=ny;
            Celda celda=h.getCeldas()[ny][nx];
            if (celda.getTipo()==Tipo.PUERTA) {
                Puerta p=mapa.getPuerta(habitacionActual,nx,ny);
                if (p!=null){
                    habitacionActual=p.getHabitacionDestino();
                    x=p.getXDestino();
                    y=p.getYDestino();
                }
            }
        }
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}