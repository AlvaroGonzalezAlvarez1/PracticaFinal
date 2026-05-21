package Pruebas.Personajes.Jugador;

import Estructuras.IndexedList;
import Pruebas.Interactuable.Interactuable;
import Pruebas.Mapa.*;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Celda.Tipo;
import Pruebas.Mapa.Habitacion.Habitacion;
import Pruebas.Mapa.Puerta.Puerta;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;
import Pruebas.Personajes.Enemigo.Enemigo;
import Pruebas.Personajes.Entidad;
import Pruebas.Personajes.Estado;

public class Jugador extends Entidad {

    private int rango;
    private Estado estado;

    private IndexedList<Objeto> inventario;

    private IndexedList<String> eventos;

    public Jugador(int x,int y,int habitacionInicial) {
        super(x,y,10,10,2,0,habitacionInicial);
        rango=5;
        inventario=new IndexedList<>();
        estado=Estado.NORMAL;
        eventos=new IndexedList<>();
    }

    @Override
    public void moverA(int xDestino, int yDestino, Mapa mapa){
        boolean puedeMoverse=true;
        Habitacion h = mapa.getHabitacion(habitacionActual);
        Celda celda=h.getCeldas()[yDestino][xDestino];
        if (h.sinEnemigo(xDestino,yDestino)==false) {
            puedeMoverse=false;
        }
        if (puedeEntrar(celda)==false) {
            puedeMoverse=false;
        }
        if (puedeMoverse==true) {
            dirX=xDestino-x;
            dirY=yDestino-y;
            x=xDestino;
            y=yDestino;
            actualizarEstado(mapa);
            if (celda.getTipo()==Tipo.PUERTA) {
                Puerta p=mapa.getPuerta(habitacionActual,xDestino,yDestino);
                if (p!=null) {
                    habitacionActual=p.getHabitacionDestino();
                    x=p.getXDestino();
                    y=p.getYDestino();
                    actualizarEstado(mapa);
                }
            }
        }
    }

    public boolean estaAlLado(int tx,int ty){
        int dx=Math.abs(tx-x);
        int dy=Math.abs(ty-y);
        return dx+dy==1;
    }

    @Override
    public boolean puedeEntrar(Celda celda){
        boolean resultado=false;
        Tipo tipo=celda.getTipo();
        if (tipo==Tipo.SUELO || tipo==Tipo.PUERTA) {
            resultado = true;
        }
        else if(tipo==Tipo.AGUA) {
            if(tieneObjeto(TipoObjeto.ALETA)) {
                resultado=true;
            }
        }
        return resultado;
    }

    public void eliminarObjeto(TipoObjeto tipo){    //Cambiar a eliminar Objeto
        boolean eliminado=false;
        int i=0;
        while(i<inventario.len() && eliminado==false) {
            if(inventario.get(i).getTipo()==tipo) {
                inventario.delete(i);
                eliminado=true;
                String evento="uso_"+tipo.name();
                activarEvento(evento);
                System.out.println("Has usado: "+tipo);
            }
            i++;
        }
    }

    public void anadirObjeto(Objeto objeto){
        inventario.append(objeto);
        String evento="recibir_"+objeto.getTipo().name();
        activarEvento(evento);
        System.out.println("Has recibidio: "+objeto.getNombre());

    }

    public boolean tieneObjeto(TipoObjeto tipo) {
        boolean resultado=false;
        for (int i=0;i<inventario.len();i++) {
            if (inventario.get(i).getTipo()==tipo) {
                resultado=true;
            }
        }
        return resultado;
    }

    public void interactuar(Mapa mapa){
        int tx=x+dirX;
        int ty=y+dirY;
        Habitacion h=mapa.getHabitacion(habitacionActual);
        if(h.sinEnemigo(tx,ty)==false){
            Enemigo enemigo=h.getEnemigo(tx,ty);
            if(enemigo!=null){
                atacar(enemigo);
                System.out.println("Vida enemigo: "+enemigo.getVida()+"/"+enemigo.getVidaMax());
                if(enemigo.estaVivo()==false){
                    h.eliminarEnemigo(enemigo);
                    System.out.println("Enemigo derrotado");
                }
            }
        }
        else{
            Interactuable i=mapa.getInteractuable(habitacionActual,tx,ty);

            if(i!=null){
                i.interactuar(this);
            }
        }
    }

    public void actualizarEstado(Mapa mapa) {
        Habitacion h=mapa.getHabitacion(habitacionActual);
        Celda celda=h.getCeldas()[y][x];
        if(celda.getTipo()==Tipo.AGUA) {
            estado=Estado.AGUA;
        } else {
            estado=Estado.NORMAL;
        }
    }

    public IndexedList<String> getEventos() {
        return eventos;
    }
    //Para dialogos dinamicos, abrir puertas, derrotar enemigos etc
    public void activarEvento(String evento) {
        if(eventos.contains(evento)==false) {
            eventos.append(evento);
        }
    }

    public boolean tieneEvento(String evento) {
        return eventos.contains(evento);
    }

    //Mejora para implementar animaciones
    //Preguntar a Antonio si es una buena práctica
    public String getSprite() {
        String resultado="file:./src/sprites/jugador.png";
        if (estado==Estado.AGUA) {
            resultado="file:./src/sprites/jugador_agua.png";
        }
        return resultado;
    }

    public int getRango() {
        return rango;
    }

    public Estado getEstado() {
        return estado;
    }

    public void imprimirEventos(){
        System.out.println("=== EVENTOS DEL JUGADOR ===");
        for(int i=0;i<eventos.len();i++){
            System.out.println(eventos.get(i));
        }
        System.out.println("===========================");
    }
}