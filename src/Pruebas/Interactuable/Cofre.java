package Pruebas.Interactuable;

import Pruebas.Objetos.Objeto;
import Pruebas.Personajes.Jugador;

public class Cofre extends Interactuable{
    private boolean abierto;
    private Objeto objeto;

    public Cofre(int habitacion,int x,int y,Objeto objeto){
        super(habitacion,x,y);
        this.objeto=objeto;
        abierto=false;
    }

    public boolean isAbierto() {
        return abierto;
    }

    @Override
    public void interactuar(Jugador jugador){
        String evento="cofre_"+getHabitacion()+"_"+getX()+"_"+getY();
        if(abierto==false){
            abierto=true;
            jugador.activarEvento(evento);
            jugador.anadirObjeto(objeto);
        }
    }

    @Override
    public String getSprite() {
        String resultado="";
        if(isAbierto()==true){
            resultado="file:./src/sprites/cofre_abierto.png";
        }
        else{
            resultado="file:./src/sprites/cofre_cerrado.png";
        }
        return resultado;
    }
}
