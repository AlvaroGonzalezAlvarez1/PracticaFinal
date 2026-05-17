package Pruebas.Mapa;

import Estructuras.Graph;
import Estructuras.IndexedList;
import Pruebas.Interactuable.*;
import Pruebas.Mapa.Habitacion.Habitacion;
import Pruebas.Mapa.Puerta.Puerta;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;

public class Mapa {
    private IndexedList<Habitacion> habitaciones;  //Cambiar a indexedList para no modificar cada vez que añado una habitación
    private Graph<Integer,Integer> grafo;
    private IndexedList<Puerta> puertas;
    private IndexedList<Interactuable> interactuables;

    public Mapa(){
        habitaciones= MapaLoader.cargarHabitaciones();
        puertas=MapaLoader.cargarPuertas();
        interactuables=new IndexedList<>();
        grafo=new Graph<>();
        crearInteractuables();
        crearGrafo();
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
        for (int i=0; i<habitaciones.len();i++) {
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

    //Lo usa luego JuegoFX para cargar los Sprites
    public IndexedList<Interactuable> getInteractuables() {
        return interactuables;
    }
}
