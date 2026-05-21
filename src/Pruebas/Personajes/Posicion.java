package Pruebas.Personajes;

import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Mapa;

import java.util.Random;

public class Posicion {
    private int x;
    private int y;

    public Posicion(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override   //Necesario porque grafo compara por equals
    public boolean equals(Object obj){
        boolean result=false;
        if(obj instanceof Posicion){
            Posicion p=(Posicion)obj;
            if(p.x==x && p.y==y){
                result=true;
            }
        }
        return result;
    }
}