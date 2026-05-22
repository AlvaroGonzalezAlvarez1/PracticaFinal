package Pruebas.Interactuable;

import Estructuras.IndexedList;
import Pruebas.Personajes.Jugador.Jugador;

public class Palanca extends Interactuable {
    private IndexedList<Integer> posiciones;

    public Palanca(int habitacion,int x,int y,IndexedList<Integer> posiciones) {
        super(habitacion,x,y);
        this.posiciones=posiciones;
    }

    @Override
    public void interactuar(Jugador jugador) {
        String evento="palanca_activa_"+getHabitacion()+"_"+getX()+"_"+getY()+"_"+getDatosEvento();
        if(jugador.tieneEvento(evento)==false){
            jugador.activarEvento(evento);
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
    public String getSprite(Jugador jugador) {
        String evento="palanca_activa_"+getHabitacion()+"_"+getX()+"_"+getY()+"_"+getDatosEvento();
        String resultado="";
        if(jugador.tieneEvento(evento)==true) {
            resultado="file:./src/sprites/palanca_activa.png";
        }
        else{
            resultado="file:./src/sprites/palanca_inactiva.png";
        }
        return resultado;
    }
}