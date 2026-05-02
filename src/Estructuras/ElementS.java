package Estructuras;

public class ElementS<T>{
    private T data;
    protected ElementS<T> next;

    //CONSTRUCTORES

    public ElementS(T data){
        this.data=data;
        next=null;
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ElementS<T> getNext() {
        return next;
    }

    public void setNext(ElementS<T> next) {
        this.next = next;
    }
}
