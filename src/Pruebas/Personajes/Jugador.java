package Pruebas.Personajes;

import Estructuras.IndexedList;
import Pruebas.Interactuable.Interactuable;
import Pruebas.Mapa.*;
import Pruebas.Mapa.Celda.Celda;
import Pruebas.Mapa.Celda.Tipo;
import Pruebas.Mapa.Habitacion.Habitacion;
import Pruebas.Mapa.Puerta.Puerta;
import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;

public class Jugador {

    private int x;
    private int y;
    private int rango;
    private int habitacionActual;
    //Para saber a donde mira el jugador, de momento para poder
    //abrir cofres, cuando metamos movimiento por raton
    //lo usaremos para las animaciones
    private int dirX;
    private int dirY;
    private Estado estado;

    private IndexedList<Objeto> inventario;

    private IndexedList<String> eventos;

    public Jugador(int x,int y,int habitacionInicial) {
        this.x=x;
        this.y=y;
        rango=5;
        this.habitacionActual=habitacionInicial;
        dirX=0;
        dirY=1;
        inventario=new IndexedList<>();
        estado=Estado.NORMAL;
        eventos=new IndexedList<>();
    }

    public void mover(int dx, int dy, Mapa mapa) {
        Habitacion h=mapa.getHabitacion(habitacionActual);
        dirX=dx;
        dirY=dy;
        int nx=x+dx;
        int ny=y+dy;
        Celda celda=h.getCeldas()[ny][nx];
        if(puedeEntrar(celda)==true) {
            x=nx;
            y=ny;
            actualizarEstado(mapa);
            if (celda.getTipo()== Tipo.PUERTA) {
                Puerta p=mapa.getPuerta(habitacionActual,nx,ny);
                if (p!=null){
                    habitacionActual=p.getHabitacionDestino();
                    x=p.getXDestino();
                    y=p.getYDestino();
                    actualizarEstado(mapa);//Por si se cae a un pozo
                }
            }
        }
    }

    public boolean puedeEntrar(Celda celda) {
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

    public void eliminarObjeto(TipoObjeto tipo) {
        boolean eliminado=false;
        int i=0;
        while(i<inventario.len() && eliminado==false) {
            if(inventario.get(i).getTipo()==tipo) {
                inventario.delete(i);
                eliminado=true;
            }
            i++;
        }
    }

    public void interactuar(Mapa mapa){
        int tx=x+dirX;
        int ty=y+dirY;
        Interactuable i= mapa.getInteractuable(habitacionActual,tx,ty);
        if(i!=null){
            i.interactuar(this);
        }
    }

    public void anadirObjeto(Objeto objeto){
        inventario.append(objeto);
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

    public void actualizarEstado(Mapa mapa) {
        Habitacion h=mapa.getHabitacion(habitacionActual);
        Celda celda=h.getCeldas()[y][x];
        if(celda.getTipo()==Tipo.AGUA) {
            estado=Estado.AGUA;
        } else {
            estado=Estado.NORMAL;
        }
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

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRango() {
        return rango;
    }

    public Estado getEstado() {
        return estado;
    }
}