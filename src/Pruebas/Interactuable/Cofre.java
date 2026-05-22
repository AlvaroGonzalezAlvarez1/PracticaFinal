package Pruebas.Interactuable;

import Pruebas.Objetos.Objeto;
import Pruebas.Personajes.Jugador.Jugador;

public class Cofre extends Interactuable{
    private Objeto objeto;

    public Cofre(int habitacion,int x,int y,Objeto objeto){
        super(habitacion,x,y);
        this.objeto=objeto;
    }

    @Override
    public void interactuar(Jugador jugador){
        String evento="cofre_"+getHabitacion()+"_"+getX()+"_"+getY();
        if(jugador.tieneEvento(evento)==false){
            jugador.activarEvento(evento);
            jugador.anadirObjeto(objeto);
        }
    }

    @Override
    public String getSprite(Jugador jugador) {
        String evento="cofre_"+getHabitacion()+"_"+getX()+"_"+getY();
        String resultado="";
        if(jugador.tieneEvento(evento)==true){
            resultado="file:./src/sprites/cofre_abierto.png";
        }
        else{
            resultado="file:./src/sprites/cofre_cerrado.png";
        }
        return resultado;
    }
}
