package Pruebas.Mapa.Habitacion;

import Estructuras.Graph;
import Estructuras.IndexedList;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Personajes.Enemigo.Enemigo;
import Pruebas.Personajes.Jugador.Jugador;
import Pruebas.Personajes.Posicion;

public class Habitacion {
    private Celda[][] celdas;
    private IndexedList<Enemigo> enemigos;

    public Habitacion(Celda[][] celdas){
        this.celdas=celdas;
        this.enemigos=new IndexedList<>();
    }

    public void addEnemigo(Enemigo e){
        enemigos.append(e);
    }

    private boolean dentro(int x, int y){
        return y>=0 && y<celdas.length && x>=0 && x<celdas[y].length;
    }

    public boolean sinEnemigo(int x, int y) {
        boolean resultado=true;
        if (dentro(x, y)==false) {
            resultado=false;
        }
        else{
            Celda celda = celdas[y][x];
            if (celda.esTransitable()==false) {
                resultado=false;
            }
            else {
                int i = 0;
                while (i<enemigos.len() && resultado==true) {
                    Enemigo e=enemigos.get(i);
                    if (e.getX()==x && e.getY()==y && e.estaVivo()){
                        resultado=false;
                    }
                    i++;
                }
            }
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
                if(jugador.puedeEntrar(celdas[y][x]) && sinEnemigo(x,y)){
                    Posicion actual=new Posicion(x,y);
                    grafo.addNode(actual);
                    // DERECHA
                    if(x+1 < celdas[y].length && jugador.puedeEntrar(celdas[y][x+1]) && sinEnemigo(x+1,y)){
                        Posicion derecha=new Posicion(x+1,y);
                        grafo.addNode(derecha);
                        grafo.addEdge(actual,derecha,null);
                        grafo.addEdge(derecha,actual,null);
                    }
                    // ABAJO
                    if(y+1 < celdas.length && jugador.puedeEntrar(celdas[y+1][x]) && sinEnemigo(x,y+1)){
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

    public IndexedList<Enemigo> getEnemigos() {
        return enemigos;
    }

    public Enemigo getEnemigo(int x,int y){
        Enemigo resultado=null;
        int i=0;
        while(i<enemigos.len() && resultado==null){
            Enemigo e=enemigos.get(i);
            if(e.getX()==x && e.getY()==y && e.estaVivo()){
                resultado=e;
            }
            i++;
        }
        return resultado;
    }

    public void eliminarEnemigo(Enemigo enemigo){
        int i=0;
        boolean eliminado=false;
        while(i<enemigos.len() && eliminado==false){
            if(enemigos.get(i)==enemigo){
                enemigos.delete(i);
                eliminado=true;
            }
            i++;
        }
    }
}


