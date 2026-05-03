package Pruebas.Mapa;

import Estructuras.EdgeGraph;
import Estructuras.Graph;
import Estructuras.IndexedList;

public class Mapa {
    private Habitacion[] habitaciones;
    private Graph<Integer, Puerta> grafo;

    public Mapa(){
        habitaciones=new Habitacion[4];
        grafo=new Graph<>();

        crearHabitaciones();
        crearGrafo();
    }

    public Habitacion getHabitacion(int index) {
        Habitacion resultado=null;
        if (index >= 0 && index < habitaciones.length) {
            resultado = habitaciones[index];
        }
        return resultado;
    }

    public EdgeGraph<Integer, Puerta> getConexion(int habitacion, int x, int y) {
        IndexedList<EdgeGraph<Integer, Puerta>>edges=grafo.edgesFrom(habitacion);
        int i=0;
        EdgeGraph<Integer, Puerta>resultado=null;
        boolean encontrado=false;
        while (i<edges.len() && encontrado==false) {
            EdgeGraph<Integer, Puerta>edge=edges.get(i);
            Puerta p=edge.getData();
            if (p.getXOrigen()==x && p.getYOrigen()==y) {
                resultado=edge;
                encontrado=true;
            }
            i++;
        }
        return resultado;
    }

    private void crearHabitaciones(){
        habitaciones[0] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p()},
                {p(), s(), s(), s(), p()},
                {p(), s(), s(), s(), d()},
                {p(), s(), s(), s(), p()},
                {p(), p(), d(), p(), p()}
        });

        habitaciones[1] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p()},
                {p(), s(), s(), s(), s(), p()},
                {d(), s(), s(), s(), s(), p()},
                {p(), s(), s(), s(), s(), p()},
                {p(), p(), s(), s(), s(), p()},
                {v(), p(), s(), s(), s(), p()},
                {v(), p(), s(), s(), s(), d()},
                {v(), p(), s(), s(), s(), p()},
                {v(), p(), s(), s(), s(), p()},
                {v(), p(), s(), s(), s(), p()},
                {v(), p(), s(), s(), s(), p()},
                {v(), d(), s(), s(), s(), p()},
                {v(), p(), s(), s(), s(), p()},
                {v(), p(), p(), p(), p(), p()}
        });

        habitaciones[2] = new Habitacion(new Celda[][]{
                {p(), p(), d(), p(), p(), v()},
                {p(), s(), s(), s(), p(), v()},
                {p(), s(), s(), s(), p(), p()},
                {p(), s(), s(), s(), s(), p()},
                {p(), s(), s(), s(), s(), d()},
                {p(), s(), s(), s(), s(), p()},
                {p(), p(), p(), p(), p(), p()}
        });

        habitaciones[3] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p(), p()},
                {p(), s(), s(), s(), s(), s(), p()},
                {p(), s(), p(), s(), p(), s(), p()},
                {d(), s(), s(), s(), s(), s(), p()},
                {p(), s(), p(), s(), p(), s(), p()},
                {p(), s(), s(), s(), s(), s(), p()},
                {p(), p(), p(), p(), p(), p(), p()}
        });
    }


    private void crearGrafo() {
        for (int i = 0; i < habitaciones.length; i++) {
            grafo.addNode(i);
        }
        //Ciclo de habitaciones 0-1-2
        grafo.addEdge(0, 1,new Puerta(4,2,0,2));
        grafo.addEdge(1, 0,new Puerta(0,2,4,2));
        grafo.addEdge(1,2,new Puerta(1,11,5,4));
        grafo.addEdge(2,1,new Puerta(5,4,1,11));
        grafo.addEdge(0, 2,new Puerta(2,4,2,0));
        grafo.addEdge(2, 0,new Puerta(2,0,2,4));
        //Habitacion contigua a 1 independiente del ciclo
        grafo.addEdge(1, 3,new Puerta(5,6,0,3));
        grafo.addEdge(3,1,new Puerta(0,3,5,6));
    }

    private Celda p() {
        return new Celda(Tipo.PARED);
    }

    private Celda s() {
        return new Celda(Tipo.SUELO);
    }

    private Celda v() {
        return new Celda(Tipo.VACIO);
    }

    private Celda d() {
        return new Celda(Tipo.PUERTA);
    }
}
