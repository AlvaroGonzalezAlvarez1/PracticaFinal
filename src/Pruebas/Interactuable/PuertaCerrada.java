package Pruebas.Interactuable;

import Pruebas.Objetos.TipoObjeto;
import Pruebas.Personajes.Jugador.Jugador;

public class PuertaCerrada extends Interactuable{
    private String orientacion;//0,180,izquierda,derecha

    public PuertaCerrada(int habitacion,int x,int y,String orientacion){
        super(habitacion,x,y);
        this.orientacion=orientacion;
    }

    @Override
    public void interactuar(Jugador jugador){
        String evento="puerta_abierta_"+getHabitacion()+"_"+getX()+"_"+getY();
        if(jugador.tieneObjeto(TipoObjeto.LLAVE) && jugador.tieneEvento(evento)==false){
            jugador.eliminarObjeto(TipoObjeto.LLAVE);
            jugador.activarEvento(evento);
        }
    }

    @Override
    public String getSprite(Jugador jugador) {
        return "file:./src/sprites/puerta_cerrada_"+orientacion+".png";
    }

    @Override
    public int getOffsetX() {
        int resultado=0;
        if(orientacion.equals("0") || orientacion.equals("180")){
            resultado=-4;
        }
        return resultado;
    }

    @Override
    public int getOffsetY() {
        int resultado=0;
        if(orientacion.equals("izquierda")||orientacion.equals("derecha")){
            resultado=-4;
        }
        return resultado;
    }

    public boolean esVisible(Jugador jugador) {
        boolean resultado=true;
        String evento="puerta_abierta_"+getHabitacion()+"_"+getX()+"_"+getY();
        if(jugador.tieneEvento(evento)==true){
            resultado=false;
        }
        return resultado;
    }
}


