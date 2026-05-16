package Pruebas.Mapa;

public class Celda {
    private Tipo tipo;

    public Celda(Tipo tipo){
        this.tipo=tipo;
    }


    public Tipo getTipo() {
        return tipo;
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
