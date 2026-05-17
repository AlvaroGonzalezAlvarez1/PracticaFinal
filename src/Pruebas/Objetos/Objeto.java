package Pruebas.Objetos;

public class Objeto {
    private String nombre;
    private TipoObjeto tipo;

    public Objeto(String nombre,TipoObjeto tipo) {
        this.nombre=nombre;
        this.tipo=tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoObjeto getTipo() {
        return tipo;
    }

}