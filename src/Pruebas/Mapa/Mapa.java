package Pruebas.Mapa;

import Estructuras.Graph;
import Estructuras.IndexedList;
import Pruebas.Interactuable.*;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Celda.Tipo;
import Pruebas.Mapa.Habitacion.Habitacion;
import Pruebas.Mapa.Puerta.Puerta;
import Pruebas.Personajes.Jugador;

public class Mapa {
    private Graph<Integer,Integer> grafo;
    private IndexedList<Habitacion> habitaciones;  //Cambiar a indexedList para no modificar cada vez que añado una habitación
    private IndexedList<Puerta> puertas;
    private IndexedList<Interactuable> interactuables;

    public Mapa(){
        habitaciones= MapaLoader.cargarHabitaciones();
        puertas=MapaLoader.cargarPuertas();
        interactuables=InteractuableLoader.cargarInteractuables();
        grafo=new Graph<>();
        for(int i=0;i< habitaciones.len();i++){
            grafo.addNode(i);
        }
        for(int i=0;i<puertas.len(); i++) {
            Puerta p=puertas.get(i);
            grafo.addEdge(p.getHabitacionOrigen(), p.getHabitacionDestino(), null);
        }
    }

    public Habitacion getHabitacion(int index) {
        Habitacion resultado=null;
        if (index>=0 && index<habitaciones.len()) {
            resultado=habitaciones.get(index);
        }
        return resultado;
    }

    public Puerta getPuerta(int habitacion,int x,int y) {
        Puerta resultado=null;
        boolean encontrada=false;
        int i=0;
        while (i<puertas.len() && encontrada==false) {
            Puerta p = puertas.get(i);
            if (p.getHabitacionOrigen()==habitacion && p.getXOrigen()==x && p.getYOrigen()==y) {
                resultado=p;
                encontrada=true;
            }
            i++;
        }
        return resultado;
    }

    public Interactuable getInteractuable(int habitacion,int x,int y){
        Interactuable resultado=null;
        boolean encontrado=false;
        int j=0;
        while(j< interactuables.len() && encontrado==false){
            Interactuable i=interactuables.get(j);
            if(i.getHabitacion()==habitacion && i.getX()==x && i.getY()==y){
                resultado=i;
                encontrado=true;
            }
            j++;
        }
        return resultado;
    }

    public void aplicarEventos(Jugador jugador){
        for(int i=0;i<jugador.getEventos().len();i++){
            String evento=jugador.getEventos().get(i);
            if(evento.startsWith("puerta_abierta_")){
                String[] partes=evento.split("_");
                String habitacion=partes[2];
                String x=partes[3];
                String y=partes[4];
                Habitacion hab=habitaciones.get(Integer.parseInt(habitacion));
                hab.getCeldas()[Integer.parseInt(y)][Integer.parseInt(x)].setTipo(Tipo.PUERTA);
            }
            else if(evento.startsWith("palanca_activa_")){
                String[] partes=evento.split("_");
                int habitacion=Integer.parseInt(partes[2]);
                Habitacion hab=habitaciones.get(habitacion);
                Celda[][] celdas= hab.getCeldas();
                for(int j=5;j<partes.length;j+=3){
                    int x=Integer.parseInt(partes[j]);
                    int y=Integer.parseInt(partes[j+1]);
                    celdas[y][x].setTipo(Tipo.SUELO);
                }
            }
        }
    }

    //Lo usa luego JuegoFX para cargar los Sprites
    public IndexedList<Interactuable> getInteractuables() {
        return interactuables;
    }
}
