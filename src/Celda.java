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
        if(tipo==Tipo.SUELO){
            result=true;
        }
        return result;
    }
}
