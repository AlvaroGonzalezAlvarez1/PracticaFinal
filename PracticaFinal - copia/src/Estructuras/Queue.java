package Estructuras;

public class Queue <T>{
    private ElementS<T> head;
    private ElementS<T> tail;
    private int size;

    public Queue(){
        head=null;
        tail=null;
        size=0;
    }

    public void enqueue(T data) {
        ElementS<T> newElement=new ElementS<>(data);
        if(tail==null) {
            head=newElement;
            tail=newElement;
        } else {
            tail.setNext(newElement);
            tail=newElement;
        }
        size++;
    }

    public T dequeue(){
        T result=null;
        if(head!=null) {
            result = head.getData();
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            size--;
        }
        return result;
    }

    public T peek(){
        T result=null;
        if (head != null) {
            result=head.getData();
        }
        return result;
    }

    public boolean isEmpty() {
        boolean result=false;
        if(head==null){
            result=true;
        }
        return result;
    }

    public int size() {
        return size;
    }

    public void print() {
        ElementS<T> current=head;
        System.out.print("[");
        while (current!=null) {
            System.out.print(current.getData());
            if (current.getNext()!=null){
                System.out.print(",");
            }
            current=current.getNext();
        }
        System.out.println("]");
    }
}

