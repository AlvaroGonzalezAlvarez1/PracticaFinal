package Estructuras;


public class IteratorIL<T> implements MyIterator<T>{
    private ElementD<T> current;
    private int steps;
    private int size;

    public IteratorIL(ElementD<T> start,int size){
        this.current=start;
        this.steps=0;
        this.size=size;
    }
    @Override
    public boolean hasNext(){
        return current!=null && steps<size;
    }

    @Override
    public T next(){
        T data=null;
        if(hasNext()==true){
            data= current.getData();
            current=current.getNext();
            steps++;
        }
        return data;
    }
}

