package Pruebas.Interactuable;

import Pruebas.Objetos.TipoObjeto;
import Pruebas.Personajes.Jugador.Jugador;

public class Fogata extends Interactuable{
    public Fogata(int habitacion, int x, int y){
        super(habitacion,x,y);
    }

    @Override
    public void interactuar(Jugador jugador){
        String evento="fogata_encendida_"+getHabitacion()+"_"+getX()+"_"+getY();
        if(jugador.tieneObjeto(TipoObjeto.ANTORCHA) && jugador.tieneEvento(evento)==false){
            jugador.activarEvento(evento);
        }
    }

    @Override
    public String getSprite(Jugador jugador) {
        String evento="fogata_encendida_"+getHabitacion()+"_"+getX()+"_"+getY();
        String resultado="";
        if(jugador.tieneEvento(evento)==true){
            resultado="file:./src/sprites/fogata_encendida.png";
        }
        else{
            resultado="file:./src/sprites/fogata_apagada.png";
        }
        return resultado;
    }
}
