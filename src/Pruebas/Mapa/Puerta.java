package Pruebas.Mapa;

public class Puerta {
    private int xOrigen;
    private int yOrigen;
    private int xDestino;
    private int yDestino;

    public Puerta(int xOrigen, int yOrigen, int xDestino, int yDestino) {
        this.xOrigen = xOrigen;
        this.yOrigen = yOrigen;
        this.xDestino = xDestino;
        this.yDestino = yDestino;
    }

    public int getXOrigen() {
        return xOrigen;
    }

    public int getYOrigen() {
        return yOrigen;
    }

    public int getXDestino() {
        return xDestino;
    }

    public int getYDestino() {
        return yDestino;
    }
}