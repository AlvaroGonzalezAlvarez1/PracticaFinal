package Pruebas.Personajes;

import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Mapa;

import java.util.Random;

public abstract class Entidad {

    protected int x;
    protected int y;

    protected int vida;
    protected int vidaMax;
    protected int ataque;
    protected int defensa;

    protected int habitacionActual;

    protected int dirX;
    protected int dirY;

    protected Random random=new Random();

    public Entidad(int x,int y,int vida,int vidaMax,int ataque,int defensa,int habitacionInicial) {
        this.x=x;
        this.y=y;
        this.vida=vida;
        this.vidaMax=vidaMax;
        this.ataque=ataque;
        this.defensa=defensa;
        this.habitacionActual=habitacionInicial;

        this.dirX=0;
        this.dirY=1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void mirarHacia(int objetivoX, int objetivoY){
        dirX=objetivoX-x;
        dirY=objetivoY-y;
        if(dirX!=0){
            dirX=dirX/Math.abs(dirX);
        }
        if(dirY!=0){
            dirY=dirY/Math.abs(dirY);
        }
    }

    public void atacar(Entidad objetivo) {
        double aleatorio=random.nextDouble(); // 0 a 1
        double dano=ataque*(aleatorio * 2)-objetivo.defensa;
        int danoFinal=Math.max(0,(int)dano);
        objetivo.recibirDano(danoFinal);
    }

    public void recibirDano(int cantidad) {
        vida-=cantidad;
        if (vida<0) {
            vida=0;
        }
    }

    public boolean estaVivo() {
        boolean vivo=true;
        if(vida<0){
            vivo=false;
        }
        return vivo;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public abstract void moverA(int xDestino, int yDestino, Mapa mapa);

    public abstract boolean puedeEntrar(Celda celda);
}
