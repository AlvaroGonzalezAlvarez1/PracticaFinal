package Pruebas.Mapa;

import Estructuras.Graph;
import Estructuras.IndexedList;

public class Mapa {
    private Habitacion[] habitaciones;  //Cambiar a indexedList para no modificar cada vez que añado una habitación
    private Graph<Integer,Integer> grafo;
    private IndexedList<Puerta> puertas;

    public Mapa(){
        habitaciones=new Habitacion[4];
        puertas=new IndexedList<>();
        grafo=new Graph<>();
        crearHabitaciones();
        crearPuertas();
        crearGrafo();
    }

    public Habitacion getHabitacion(int index) {
        Habitacion resultado=null;
        if (index>=0 && index<habitaciones.length) {
            resultado=habitaciones[index];
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
    public void crearPuertas(){
        puertas.append(new Puerta(0,1,4,2,0,2));
        puertas.append(new Puerta(1,0,0,2,4,2));
        puertas.append(new Puerta(1,2,1,11,5,4));
        puertas.append(new Puerta(2,1,5,4,1,11));
        puertas.append(new Puerta(0,2,2,4,2,0));
        puertas.append(new Puerta(2,0,2,0,2,4));
        puertas.append(new Puerta(1,3,5,6,0,3));
        puertas.append(new Puerta(3,1,0,3,5,6));
    }

    private void crearGrafo() {
        for (int i=0; i<habitaciones.length;i++) {
            grafo.addNode(i);
        }
        //Ciclo de habitaciones 0-1-2
        grafo.addEdge(0, 1,null);
        grafo.addEdge(1, 0,null);
        grafo.addEdge(1,2,null);
        grafo.addEdge(2,1,null);
        grafo.addEdge(0, 2,null);
        grafo.addEdge(2, 0,null);
        //Habitacion contigua a 1 independiente del ciclo
        grafo.addEdge(1, 3,null);
        grafo.addEdge(3,1,null);
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
