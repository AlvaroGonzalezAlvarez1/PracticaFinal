package Pruebas.Mapa;

import Estructuras.Graph;
import Estructuras.IndexedList;
import Pruebas.Interacciones.*;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;

public class Mapa {
    private Habitacion[] habitaciones;  //Cambiar a indexedList para no modificar cada vez que añado una habitación
    private Graph<Integer,Integer> grafo;
    private IndexedList<Puerta> puertas;
    private IndexedList<Interactuable> interactuables;

    public Mapa(){
        habitaciones=new Habitacion[7];
        puertas=new IndexedList<>();
        interactuables=new IndexedList<>();
        grafo=new Graph<>();
        crearHabitaciones();
        crearPuertas();
        crearInteractuables();
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

    private void crearHabitaciones(){
        habitaciones[0] = new Habitacion(new Celda[][]{
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

        habitaciones[1] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), i(), s(), p(), p()},
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
                {p(), p(), p(), p(), i(), s(), s(), s(), s(), s(), s(), s(), s(), s(), s(), p(), p(), p(), p()},
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
                {p(), p(), s(), i(), s(), p(), p()},
                {p(), p(), s(), s(), s(), p(), p()},
                {p(), i(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), p(), p()},
                {p(), p(), p(), d(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p()}
        });

        habitaciones[6] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()},
                {p(), p(), s(), s(), s(), s(), i(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), a(), a(), a(), s(), s(), p(), p()},
                {p(), p(), s(), s(), a(), a(), a(), s(), s(), p(), p()},
                {p(), p(), s(), s(), a(), a(), a(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), s(), s(), s(), s(), s(), s(), s(), p(), p()},
                {p(), p(), p(), p(), p(), d(), p(), p(), p(), p(), p()},
                {p(), p(), p(), p(), p(), p(), p(), p(), p(), p(), p()}
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
        puertas.append(new Puerta(0,6,5,1,5,9));
        puertas.append(new Puerta(6,0,5,9,5,1));

    }

    private void crearInteractuables(){
        interactuables.append(new Cofre(6,6,2,new Objeto("Aleta",TipoObjeto.ALETA)));

        String[] dialogosSoldado1=new String[]{
                "Estoy buscando a mis compañeras de grupo, una bruja roja y una bruja azul",
                "Ayúdame a encontrar a la bruja azul Carolina y a la bruja roja Isabel",
                "Veo que ya encontraste a una bruja, te queda encontrar a su amiga",
                "Enhorabuena, ya encontraste a las dos brujas, toma tu regalo",
                "Ojala le des un buen uso a la antorcha"};

        interactuables.append(new Soldado1(5,3,2,dialogosSoldado1));

        String[] dialogosIsabel=new String[]{
                "Hola soy la bruja roja, te queda encontrar a la bruja azul Carolina",
                "Carolina seguramente esté cerca de alguna ventana",
                "Veo que encontraste a Carolina, sabia que estaría mirando por la ventana",
                "A ver si me termino el cigarro ya..."
        };
        interactuables.append(new Isabel(4,4,13,dialogosIsabel));

        String[] dialogosCarolina=new String[]{
                "Hola soy la bruja azul, te queda encontrar a la bruja roja Isabel",
                "Seguro que Isabel está fumandose un cigarro escondida",
                "Siempre fumando la chiquilla,como lo sabía, gracias por avisarla",
                "Siempre me hace esperar cuando baja a fumar..."
        };
        interactuables.append(new Carolina(1,7,2,dialogosCarolina));


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
        grafo.addEdge(0,3,null);
        grafo.addEdge(6,0,null);
        grafo.addEdge(0,6,null);
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

    private Celda i(){
        return new Celda(Tipo.INTERACTUABLE);
    }

    private Celda a(){
        return new Celda(Tipo.AGUA);
    }

    //Lo usa luego JuegoFX para cargar los Sprites
    public IndexedList<Interactuable> getInteractuables() {
        return interactuables;
    }
}
