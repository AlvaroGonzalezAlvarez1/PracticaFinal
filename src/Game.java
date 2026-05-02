public class Game {

    private Mapa mapa;
    private Jugador jugador;
    private Input input;

    public Game(Input input) {
        this.mapa=new Mapa();
        this.jugador=new Jugador(2, 2,0);
        this.input=input;
    }

    public void run() {
        while (input.hayAcciones()==true) {
            String accion=input.getAccion();
            procesarTurno(accion);
            render();
        }
    }

    private void procesarTurno(String accion) {
        boolean procesado = false;
        if (accion!=null) {
            switch (accion) {
                case "w"->{
                    jugador.mover(0, -1,mapa);
                    procesado = true;
                }
                case "s"->{
                    jugador.mover(0, 1,mapa);
                    procesado = true;
                }
                case "a"->{
                    jugador.mover(-1, 0,mapa);
                    procesado = true;
                }
                case "d"->{
                    jugador.mover(1, 0,mapa);
                    procesado = true;
                }
                default->{
                    procesado = false;
                }
            }
        }
        if (procesado==false) {
            System.out.println("Acción inválida");
        }
    }

    private void render() {
        Habitacion h=mapa.getHabitacion(jugador.getHabitacionActual());
        Celda[][] m=h.getCeldas();
        for (int y=0;y<m.length; y++) {
            for (int x=0; x < m[0].length; x++) {
                if (jugador.getX()==x && jugador.getY()==y) {
                    System.out.print("P ");
                }
                else {
                    Tipo tipo=m[y][x].getTipo();
                    if(tipo==Tipo.PARED) {
                        System.out.print("# ");
                    }
                    else if(tipo==Tipo.SUELO) {
                        System.out.print(". ");
                    }
                    else if(tipo==Tipo.PUERTA){
                        System.out.print("D ");
                    }
                    else{
                        System.out.print("  ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Habitacion: " + jugador.getHabitacionActual());
        System.out.println("Pos: " + jugador.getX() + "," + jugador.getY());
        System.out.println();
    }
}