import Estructuras.EdgeGraph;

public class Jugador {

    private int x;
    private int y;
    private int habitacionActual;

    public Jugador(int x,int y,int habitacionInicial) {
        this.x=x;
        this.y=y;
        this.habitacionActual=habitacionInicial;
    }

    public void mover(int dx, int dy, Mapa mapa) {
        Habitacion h=mapa.getHabitacion(habitacionActual);
        int nx=x+dx;
        int ny=y+dy;
        boolean accesible=h.esTransitable(nx, ny);
        if(accesible==true) {
            x=nx;
            y=ny;
            EdgeGraph<Integer,Puerta>edge=mapa.getConexion(habitacionActual, x, y);
            if (edge!=null) {
                Puerta p=edge.getData();
                habitacionActual=edge.getEnd().getData();
                x=p.getXDestino();
                y=p.getYDestino();
            }
        }
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