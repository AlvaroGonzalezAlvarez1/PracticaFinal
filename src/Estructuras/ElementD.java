package Estructuras;

public class ElementD<T>{
    private T data;
    protected ElementD<T> next;
    protected ElementD<T> last;

    //CONSTRUCTORES

    public ElementD(T data){
        this.data=data;
        next=null;
        last=null;
    }

    //GETTERS

    public T getData() {
        return data;
    }

    public ElementD<T> getNext() {
        return next;
    }

    public ElementD<T> getLast() {
        return last;
    }

    //SETTERS

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(ElementD<T> next) {
        this.next = next;
    }

    public void setLast(ElementD<T> last) {
        this.last = last;
    }
}
