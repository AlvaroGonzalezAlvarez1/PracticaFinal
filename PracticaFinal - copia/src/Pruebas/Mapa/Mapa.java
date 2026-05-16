package Pruebas.Mapa;

import Estructuras.Graph;
import Estructuras.IndexedList;

public class Mapa {
    private Habitacion[] habitaciones;  //Cambiar a indexedList para no modificar cada vez que añado una habitación
    private Graph<Integer,Integer> grafo;
    private IndexedList<Puerta> puertas;

    public Mapa(){
        habitaciones=new Habitacion[6];
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
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), d(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), p(), p(), p(), d(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()}
        });

        habitaciones[1] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), d(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), p(), p(), p(), d(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()}
        });

        habitaciones[2] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), d(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), p(), p(), p(), p(), p(), s(), p(), p()},
                {p(), p(), s(), p(), p(), p(), p(), p(), s(), p(), p()},
                {p(), d(), s(), p(), p(), p(), p(), p(), s(), p(), p()},
                {p(), p(), s(), p(), p(), p(), p(), p(), s(), p(), p()},
                {p(), p(), s(), p(), p(), p(), p(), p(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()}
        });

        habitaciones[3] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), d(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), d(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), p(), p(), p(), d(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()}
        });

        habitaciones[4] = new Habitacion(new Celda[][]{
                {v(), v(), v(), v(), v(), v(), v(), v(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {v(), v(), v(), v(), v(), v(), v(), v(), p(), p(), p(), p(), p(), d(), p(), p(), p(), p(), p()},
                {v(), v(), v(), v(), v(), v(), v(), v(), p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {v(), v(), v(), v(), v(), v(), v(), v(), p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), p(), p(), d(), p(), p(), p(), p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), s(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), s(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {v(), v(), v(), v(), p(), p(), p(), v(), v(), v(), v(), p(), p(), p(), v(), v(), v(), v(), v()}
        });

        habitaciones[5] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), p(), p()},
                {p(), p(), p(), d(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p()}
        });
    }
    public void crearPuertas(){
        puertas.append(new Puerta(4,3,13,1,5,9));
        puertas.append(new Puerta(3,4,5,9,13,1));

        puertas.append(new Puerta(4,5,4,5,3,6));
        puertas.append(new Puerta(5,4,3,6,4,5));

        puertas.append(new Puerta(3,0,5,1,5,9));
        puertas.append(new Puerta(0,3,5,9,5,1));
        puertas.append(new Puerta(3,2,9,5,1,5));
        puertas.append(new Puerta(2,3,1,5,9,5));
        puertas.append(new Puerta(1,0,1,5,9,5));
        puertas.append(new Puerta(0,1,9,5,1,5));
        puertas.append(new Puerta(2,1,5,1,5,9));
        puertas.append(new Puerta(1,2,5,9,5,1));
    }

    private void crearGrafo() {
        for (int i=0; i<habitaciones.length;i++) {
            grafo.addNode(i);
        }
        //Ciclo de habitaciones 0-1-2
        grafo.addEdge(0,1,null);
        grafo.addEdge(1,0,null);
        grafo.addEdge(1,2,null);
        grafo.addEdge(2,1,null);
        grafo.addEdge(3,2,null);
        grafo.addEdge(2,3,null);
        grafo.addEdge(3,0,null);
        grafo.addEdge(3,3,null);
        //Habitacion contigua a 1 independiente del ciclo
        grafo.addEdge(4,3,null);
        grafo.addEdge(3,4,null);
        grafo.addEdge(4,5,null);
        grafo.addEdge(5,4,null);
    }

    private Celda p() {
        return new Celda(Tipo.PARED);
    }   //Para no añadir 0 a todas las p()

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
