package Pruebas.Interactuable;

import Estructuras.IndexedList;
import Pruebas.Personajes.Jugador.Jugador;

public class Palanca extends Interactuable {
    private IndexedList<Integer> posiciones;
    private boolean activada;

    public Palanca(int habitacion,int x,int y,IndexedList<Integer> posiciones) {
        super(habitacion,x,y);
        this.posiciones=posiciones;
        activada=false;
    }

    @Override
    public void interactuar(Jugador jugador) {
        String evento="palanca_activa_"+getHabitacion()+"_"+getX()+"_"+getY()+"_"+getDatosEvento();
        if(jugador.tieneEvento(evento)==false){
            jugador.activarEvento(evento);
            activada=true;
        }
    }

    private String getDatosEvento(){
        String resultado="";
        for(int i=0;i<posiciones.len();i++){
            resultado+=posiciones.get(i);
            if(i<posiciones.len()-1){
                resultado+="_";
            }
        }
        return resultado;
    }

    public IndexedList<Integer> getPosiciones() {
        return posiciones;
    }

    @Override
    public String getSprite() {
        if(activada==true) {
            return "file:./src/sprites/palanca_activa.png";
        }
        return "file:./src/sprites/palanca_inactiva.png";
    }
}