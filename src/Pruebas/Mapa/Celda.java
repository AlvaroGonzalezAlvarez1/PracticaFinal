package Pruebas.Mapa;

public class Celda {
    private Tipo tipo;
    private int spriteId;

    public Celda(Tipo tipo){
        this.tipo=tipo;
        spriteId=0;
    }

    public Celda(Tipo tipo,int spriteId){
        this.tipo=tipo;
        this.spriteId=spriteId;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getSpriteId() {
        return spriteId;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public boolean esTransitable(){
        boolean result=false;
        if(tipo==Tipo.SUELO || tipo==Tipo.PUERTA){
            result=true;
        }
        return result;
    }
}
