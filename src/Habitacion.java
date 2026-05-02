public class Habitacion {
    private Celda[][] celdas;

    public Habitacion(Celda[][] celdas){
        this.celdas=celdas;
    }

    public Celda getCelda(int x, int y) {
        Celda resultado;
        if(dentro(x, y)==true){
            resultado=celdas[y][x];
        }
        else {
            resultado=new Celda(Tipo.VACIO);
        }
        return resultado;
    }

    private boolean dentro(int x, int y){
        return y>=0 && y<celdas.length && x >= 0 && x < celdas[0].length;
    }

    public boolean esTransitable(int x, int y) {
        return dentro(x, y) && celdas[y][x].esTransitable();
    }

    public Celda[][] getCeldas() {
        return celdas;
    }
}

