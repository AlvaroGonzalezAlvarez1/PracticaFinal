public class Jugador {

    private int x;
    private int y;

    private Habitacion habitacionActual;

    public Jugador(int x,int y,Habitacion habitacionInicial) {
        this.x=x;
        this.y=y;
        this.habitacionActual=habitacionInicial;
    }

    //Implementar interfaces para que sea comun con enemigos etc
    public void mover(int dx, int dy) {
        int nx=x+dx;
        int ny=y+dy;

        if (habitacionActual.esTransitable(nx, ny)) {
            x = nx;
            y = ny;
        }
    }

    //Implementar con el grafo mas tarde
    public void cambiarHabitacion(Habitacion nuevaHabitacion, int nuevoX, int nuevoY) {
        this.habitacionActual=nuevaHabitacion;
        this.x = nuevoX;
        this.y = nuevoY;
    }

    public Habitacion getHabitacionActual() {
        return habitacionActual;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}