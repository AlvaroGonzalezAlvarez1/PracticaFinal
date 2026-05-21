package Pruebas.Personajes.Enemigo;

import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Mapa;
import Pruebas.Personajes.Entidad;

public class Enemigo extends Entidad {
    private int xSpawn;
    private int ySpawn;
    private int habitacionSpawn;

    private int offsetX;
    private int offsetY;
    private String sprite;

    public Enemigo(int habitacion,int x,int y,int vida,int vidaMax,int ataque,int defensa,String sprite,int offsetX,int offsetY) {
        super(x,y,vida,vidaMax,ataque,defensa,habitacion);
        //Guarda las variables por si queremos que respawne en un futuro
        xSpawn=x;
        ySpawn=y;
        habitacionSpawn=habitacion;

        this.offsetX=offsetX;
        this.offsetY=offsetY;
        this.sprite=sprite;
    }

    @Override
    public void moverA(int xDestino, int yDestino, Mapa mapa) {

    }

    @Override
    public boolean puedeEntrar(Celda celda) {
        return false;
    }

    public void respawn(){
        if(estaVivo()==false) {
            x=xSpawn;
            y=ySpawn;
            habitacionActual=habitacionSpawn;
            vida=vidaMax;
        }
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public String getSprite() {
        return "file:./src/sprites/"+sprite+".png";
    }
}
