public class Jugador {

    private int x;
    private int y;
    private int habitacionActual;

    public Jugador(int x,int y,int habitacionInicial) {
        this.x=x;
        this.y=y;
        this.habitacionActual=habitacionInicial;
    }

    public void mover(int dx, int dy,Mapa mapa) {
        int nx=x+dx;
        int ny=y+dy;
        boolean accesible=false;
        Habitacion h=mapa.getHabitacion(habitacionActual);
        if (h.esTransitable(nx, ny)) {
            accesible=true;
        }
        if(accesible==true){
            x=nx;
            y=ny;
            Celda celda=h.getCelda(x,y);
            Tipo tipo=celda.getTipo();
            if(tipo==Tipo.PUERTA){
                int actual=habitacionActual;
                Puerta p = mapa.getPuerta(habitacionActual, x, y);

                if (p != null) {
                    int destino = mapa.getDestinoDesdePuerta(habitacionActual, x, y);

                    habitacionActual = destino;
                    x = p.getXDestino();
                    y = p.getYDestino();
                }
            }
        }
    }

    //Implementar con el grafo mas tarde
    public void cambiarHabitacion(int nuevaHabitacion, int nuevoX, int nuevoY) {
        this.habitacionActual=nuevaHabitacion;
        this.x = nuevoX;
        this.y = nuevoY;
    }

    public int getHabitacionActual() {
        return habitacionActual;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}