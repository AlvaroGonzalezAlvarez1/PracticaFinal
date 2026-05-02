public class Mapa {
    private Habitacion[] habitaciones;

    public Mapa(){
        habitaciones=new Habitacion[3];
        crearHabitaciones();
    }

    private void crearHabitaciones(){

        habitaciones[0] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p()},
                {p(), s(), s(), s(), p()},
                {p(), s(), s(), s(), p()},
                {p(), s(), s(), s(), p()},
                {p(), p(), p(), p(), p()}
        });

        habitaciones[1] = new Habitacion(new Celda[][]{
                {p(), p(), p(), p(), p()},
                {p(), s(), p(), s(), p()},
                {p(), s(), p(), s(), p()},
                {p(), s(), s(), s(), p()},
                {p(), p(), p(), p(), p()}
        });

        habitaciones[2] = new Habitacion(new Celda[][]{
                {v(), v(), p(), p(), p()},
                {v(), v(), p(), s(), p()},
                {p(), p(), p(), s(), p()},
                {p(), s(), s(), s(), p()},
                {p(), p(), p(), p(), p()}
        });
    }

    public Habitacion getHabitacion(int index) {
        Habitacion resultado;
        if (index>=0 && index<habitaciones.length) {
            resultado=habitaciones[index];
        }
        else{
            resultado=null;
        }
        return resultado;
    }

    public int getNumeroHabitaciones() {
        return habitaciones.length;
    }

    private Celda p() {
        return new Celda(Tipo.PARED);
    }

    private Celda s() {
        return new Celda(Tipo.SUELO);
    }

    private Celda v() {
        return new Celda(Tipo.VACIO);
    }
}
