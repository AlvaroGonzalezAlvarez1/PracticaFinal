package Pruebas.Mapa.Habitacion;

import Pruebas.Mapa.Celda.Celda;

public class Habitacion {
    private Celda[][] celdas;

    public Habitacion(Celda[][] celdas){
        this.celdas=celdas;
    }


    private boolean dentro(int x, int y){
        return y>=0 && y<celdas.length && x>=0 && x<celdas[y].length;
    }

    public boolean esTransitable(int x,int y) {
        boolean resultado=false;
        if (dentro(x,y)==true) {
            resultado=celdas[y][x].esTransitable();
        }
        return resultado;
    }

    public Celda[][] getCeldas() {
        return celdas;
    }
}


