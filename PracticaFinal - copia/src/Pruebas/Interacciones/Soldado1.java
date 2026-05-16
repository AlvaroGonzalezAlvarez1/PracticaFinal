package Pruebas.Interacciones;

import Pruebas.Objetos.Objeto;
import Pruebas.Objetos.TipoObjeto;
import Pruebas.Personajes.Jugador;

public class Soldado1 extends Interactuable{
    private String[] dialogos;
    private int fase;
    private boolean recompensaDada;

    public Soldado1(int hab,int x,int y,String[] dialogos) {
        super(hab,x,y);
        this.dialogos=dialogos;
        fase=0;
        recompensaDada=false;
    }

    @Override
    public void interactuar(Jugador jugador) {
        String mensaje = "";
        if(fase!=4){
            if (jugador.tieneEvento("hablo_carolina") && jugador.tieneEvento("hablo_isabel")){
                fase=3;
            }
            else if (jugador.tieneEvento("hablo_carolina") || jugador.tieneEvento("hablo_isabel")){
                fase = 2;
            }
        }
        if(fase==0) {
            mensaje=dialogos[0];
            fase=1;
        }
        else if (fase == 1) {
            mensaje=dialogos[1];
        }
        else if(fase==2){
            mensaje=dialogos[2];
        }
        else if (fase == 3) {
            mensaje=dialogos[3];
            if(recompensaDada == false){
                darRecompensa(jugador);
                recompensaDada = true;
            }
            fase=4;
        }
        else{
            mensaje=dialogos[4];
        }
        System.out.println("Soldado: "+mensaje);
    }

    private void darRecompensa(Jugador jugador) {
        Objeto antorcha=new Objeto("Antorcha", TipoObjeto.ANTORCHA);
        jugador.anadirObjeto(antorcha);
    }

    @Override
    public String getSprite() {
        return "file:./src/sprites/soldado.png";
    }

    @Override
    public int getOffsetX(){
        return -1;
    }

    @Override
    public int getOffsetY(){
        return -11;
    }
}

