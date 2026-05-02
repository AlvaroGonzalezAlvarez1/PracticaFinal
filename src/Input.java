import Estructuras.Queue;

public class Input {

    private Queue<String> acciones;

    public Input() {
        acciones=new Queue<>();
    }

    public void addAccion(String accion) {
        acciones.enqueue(accion);
    }

    public String getAccion() {
        return acciones.dequeue();
    }

    public boolean hayAcciones() {
        return !acciones.isEmpty();
    }
}