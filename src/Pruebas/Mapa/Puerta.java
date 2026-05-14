package Pruebas.Mapa;

public class Puerta {
    private int xOrigen;
    private int yOrigen;
    private int xDestino;
    private int yDestino;
    private int habitacionOrigen;
    private int habitacionDestino;

    public Puerta(int habitacionOrigen,int habitacionDestino,int xOrigen, int yOrigen, int xDestino, int yDestino) {
        this.xOrigen = xOrigen;
        this.yOrigen = yOrigen;
        this.xDestino = xDestino;
        this.yDestino = yDestino;
        this.habitacionOrigen=habitacionOrigen;
        this.habitacionDestino=habitacionDestino;
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

    public int getHabitacionOrigen() {
        return habitacionOrigen;
    }

    public int getHabitacionDestino() {
        return habitacionDestino;
    }
}