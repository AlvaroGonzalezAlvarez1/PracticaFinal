package Pruebas.Interactuable;

import Pruebas.Personajes.Jugador;

public class Carolina extends Interactuable{
    private String[] dialogos;
    private int fase;
    private boolean hasHablado;

    public Carolina(int hab,int x,int y,String[] dialogos) {
        super(hab,x,y);
        this.dialogos=dialogos;
        fase=0;
        hasHablado=false;
    }

    @Override
    public void interactuar(Jugador jugador) {
        String mensaje="";
        jugador.activarEvento("hablo_carolina");
        if (jugador.tieneEvento("hablo_isabel")) {
            fase=2;
        }
        if(fase==0) {
            mensaje=dialogos[0];
            fase=1;
        }
        else if (fase==1) {
            mensaje=dialogos[1];
        }
        else if(fase==2){
            mensaje=dialogos[2];
            fase=3;
        }
        else{
            mensaje=dialogos[3];
        }
        System.out.println("Carolina: "+mensaje);
    }

    @Override
    public String getSprite() {
        return "file:./src/sprites/carolina.png";
    }

    @Override
    public int getOffsetX(){
        return -2;
    }

    @Override
    public int getOffsetY(){
        return -11;
    }
}
