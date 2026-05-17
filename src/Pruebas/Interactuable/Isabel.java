package Pruebas.Interactuable;

import Pruebas.Personajes.Jugador;

public class Isabel extends Interactuable{
    private String[] dialogos;
    private int fase;
    private boolean hasHablado;

    private Carolina carolina;


    public Isabel(int hab,int x,int y,String[] dialogos) {
        super(hab,x,y);
        this.dialogos=dialogos;
        fase=0;
        hasHablado=false;
    }

    public boolean isHasHablado() {
        return hasHablado;
    }

    @Override
    public void interactuar(Jugador jugador) {
        jugador.activarEvento("hablo_isabel");
        String mensaje="";
        if (jugador.tieneEvento("hablo_carolina")) {
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
        System.out.println("Isabel: "+mensaje);
    }

    @Override
    public String getSprite() {
        return "file:./src/sprites/isabel.png";
    }

    @Override
    public int getOffsetY(){
        return -13;
    }
}
