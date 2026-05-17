package Pruebas.Mapa.Habitacion;

import Estructuras.Graph;
import Estructuras.IndexedList;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Personajes.Jugador;
import Pruebas.Personajes.Posicion;

public class Habitacion {
    private Celda[][] celdas;

    public Habitacion(Celda[][] celdas){
        this.celdas=celdas;
    }


    private boolean dentro(int x, int y){
        return y>=0 && y<celdas.length && x>=0 && x<celdas[y].length;
    }

    public boolean esTransitable(int x,int y) {
        boolean resultado=false;
        if (dentro(x,y)==true) {
            resultado=celdas[y][x].esTransitable();
        }
        return resultado;
    }

    public Celda[][] getCeldas() {
        return celdas;
    }

    public Graph<Posicion,Integer> crearGrafoTransitable(Jugador jugador){
        Graph<Posicion,Integer> grafo=new Graph<>();
        Celda[][] celdas=getCeldas();
        for(int y=0;y<celdas.length;y++){
            for(int x=0;x<celdas[y].length;x++){
                if(jugador.puedeEntrar(celdas[y][x])){
                    Posicion actual=new Posicion(x,y);
                    grafo.addNode(actual);
                    // DERECHA
                    if(x+1 < celdas[y].length && jugador.puedeEntrar(celdas[y][x+1])){
                        Posicion derecha=new Posicion(x+1,y);
                        grafo.addNode(derecha);
                        grafo.addEdge(actual,derecha,null);
                        grafo.addEdge(derecha,actual,null);
                    }
                    // ABAJO
                    if(y+1 < celdas.length && jugador.puedeEntrar(celdas[y+1][x])){
                        Posicion abajo=new Posicion(x,y+1);
                        grafo.addNode(abajo);
                        grafo.addEdge(actual,abajo,null);
                        grafo.addEdge(abajo,actual,null);
                    }
                }
            }
        }
        return grafo;
    }

    public IndexedList<Posicion> getAreaMovimiento(Jugador jugador,int x,int y,int distancia){
        Graph<Posicion,Integer> grafo=crearGrafoTransitable(jugador);
        return grafo.BFSConDistancia(new Posicion(x,y),distancia);
    }
}


