package Pruebas.Mapa;

import Estructuras.Graph;
import Estructuras.IndexedList;
import Pruebas.Interactuable.*;
import Pruebas.Mapa.Habitacion.Habitacion;
import Pruebas.Mapa.Puerta.Puerta;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;

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

    //Lo usa luego JuegoFX para cargar los Sprites
    public IndexedList<Interactuable> getInteractuables() {
        return interactuables;
    }
}
