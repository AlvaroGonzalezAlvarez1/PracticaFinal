package Estructuras;

public class Stack<T>{
    private ElementS<T> head;
    private int size;

    //CONSTRUCTOR

    public Stack() {
        head=null;
        size=0;
    }

    //MÉTODOS


    public void push(T data) {
        ElementS<T> newElement=new ElementS<>(data);
        newElement.setNext(head);
        head=newElement;
        size++;
    }
    //Introduce un dato en la cabeza de la lista


    public T pop() {
        T data=null;
        if (head!=null) {
            data=head.getData();
            head=head.getNext();
            size--;
        }
        return data;
    }
    //Devuelve el primer dato, sacandolo de la pila


    public T peek(){
        T result=null;
        if (head != null) {
            result=head.getData();
        }
        return result;
    }
    //Devuelve el dato, sin sacarlo de la pila


    public boolean isEmpty() {
        boolean result=false;
        if(head==null){
            result=true;
        }
        return result;
    }
    //True si está vacía, false si tiene elementos

    public int size() {
        return size;
    }
    //Devuelve el tamaño de la pila

    public void print() {
        ElementS<T>current=head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.getData());
            if (current.getNext()!=null){
                System.out.print(",");
            }
            current = current.getNext();
        }
        System.out.println("]");
    }
}