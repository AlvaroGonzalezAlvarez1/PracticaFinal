package Pruebas.Interactuable;

import Pruebas.Objetos.TipoObjeto;
import Pruebas.Personajes.Jugador;

public class PuertaCerrada extends Interactuable{
    private String orientacion;//0,180,izquierda,derecha
    private boolean cerrada;

    public PuertaCerrada(int habitacion,int x,int y,String orientacion){
        super(habitacion,x,y);
        this.orientacion=orientacion;
        cerrada=true;
    }

    @Override
    public void interactuar(Jugador jugador){
        String evento="puerta_abierta_"+getHabitacion()+"_"+getX()+"_"+getY();
        if(jugador.tieneObjeto(TipoObjeto.LLAVE) && jugador.tieneEvento(evento)==false){
            jugador.eliminarObjeto(TipoObjeto.LLAVE);
            jugador.activarEvento(evento);
            cerrada=false;
        }
    }

    @Override
    public String getSprite() {
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

    @Override
    public boolean esVisible() {
        return cerrada;
    }
}


