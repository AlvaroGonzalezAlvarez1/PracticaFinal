package Estructuras;

public class IndexedList <T>{
    protected ElementD<T> head;
    protected ElementD<T> tail;
    protected int size;

    //CONSTRUCTOR

    public IndexedList(){
        head=null;
        tail=null;
        size=0;
    }

    //MÉTODOS
    //Siempre que se devuelva una lista y el tipo de dato sea mutable,
    //cualquier cambio posterior modificará la lista original

    public void insert(T data, int pos){
        if(pos>=0 && pos<=size){
            ElementD<T>newElement=new ElementD<>(data);
            if(pos==0){
                newElement.setNext(head);
                if(head!=null){
                    head.setLast(newElement);
                }
                head=newElement;
                if(tail == null){
                    tail = newElement;
                }
            }
            else{
                ElementD<T> current = head;
                int index=0;
                while(index<pos-1){
                    current=current.getNext();
                    index++;
                }
                ElementD<T>nextElement=current.getNext();
                current.setNext(newElement);
                newElement.setLast(current);
                newElement.setNext(nextElement);
                if(nextElement!=null){
                    nextElement.setLast(newElement);
                }
                else{
                    tail = newElement;
                }
            }
            size++;
        }
    }
    //Añade un elemento en la posicion elegida, desplazando el reto

    public void delete(int pos){
        if(head!=null && pos>=0 && pos<size){
            if(pos==0){
                head=head.getNext();
                if(head!=null){
                    head.setLast(null);
                }
                else{
                    tail=null;
                }
            }
            else{
                ElementD<T>current=head;
                int index=0;

                while(index<pos-1){
                    current=current.getNext();
                    index++;
                }
                ElementD<T>toDelete=current.getNext();
                ElementD<T>next=toDelete.getNext();
                current.setNext(next);
                if(next!=null){
                    next.setLast(current);
                }
                else{
                    tail=current;
                }
            }
            size--;
        }
    }
    //Elimina un elemento de la lista


    public void append(T data){
        insert(data,size);
    }
    //Añade al final de la lista el nuevo dato

    public T get(int pos){
        ElementD<T>current=head;
        int index=0;
        T data=null;    //Si la posición es invalida devuelve null
        if (pos>=0 && pos<size){
            while(index<pos){
                current=current.getNext();
                index++;
            }
            data=current.getData();
        }
        return data;
    }

    public void set(int pos,T data){
        ElementD<T>current=head;
        int index=0;
        if(pos>=0 && pos<size){
            while(index<pos){
                current=current.getNext();
                index++;
            }
            current.setData(data);
        }
    }
    //Sobreescribe el elemento en la posición i por el nuevo dato
    //Si posicion es invalida no hace nada

    public MyIterator<T> iterator(){
        return new IteratorIL<>(head,size);
    }

    public void extend(IndexedList<T> other){
        MyIterator<T> it=other.iterator();
        while(it.hasNext()){
            append(it.next());
        }
    }
    //Añade la lista que recibe como parámetro a la lista

    public IndexedList<T> slice(int start,int end){
        IndexedList<T>result=new IndexedList<>();
        if(start<0){
            start=0;
        }
        if(end>size){
            end=size;
        }
        ElementD<T>current=head;
        int index=0;
        while(current!=null && index<end){
            if(index>=start){
                result.append(current.getData());
            }
            current=current.getNext();
            index++;
        }
        return result;
    }
    //Equivalente a: lista[i:j] en python

    public void reverse(){
        ElementD<T>current=head;
        ElementD<T>aux=null;
        while(current!=null){
            aux=current.getLast();
            current.setLast(current.getNext());
            current.setNext(aux);
            current=current.getLast();
        }
        aux=head;
        head=tail;
        tail=aux;
    }
    //Invierte los elementos de posición

    public int len(){
        return size;
    }
    //Devuelve el tamaño de la lista

    public boolean contains(T data){
        ElementD<T> current=head;
        boolean result=false;
        while(current!=null && result==false){
            T element=current.getData();
            boolean equal;
            if(element==null){
                equal=(data==null);
            }
            else{
                equal=element.equals(data);
            }
            if(equal==true){
                result=true;
            }
            current=current.getNext();
        }
        return result;
    }
    //True si contiene elemento,False si no

    public int count(T data){
        ElementD<T> current=head;
        int total=0;
        while(current!=null){
            T element=current.getData();
            boolean equal;
            if(element==null){
                equal=(data==null);
            }
            else{
                equal=element.equals(data);
            }
            if(equal==true){
                total++;
            }
            current=current.getNext();
        }
        return total;
    }
    //Número de veces que se contiene un elemento


    public IndexedList<T> duplicate() {
        IndexedList<T>copy=new IndexedList<>();
        ElementD<T>current=head;
        while(current!=null) {
            copy.append(current.getData());
            current=current.getNext();
        }
        return copy;
    }
    //Duplica la lista, cuidado con la mutabilidad

    public void clear(){
        head=null;
        tail=null;
        size=0;
    }
    //Vacia la lista

    public boolean isEmpty(){
        return size == 0;
    }

    public void print() {
        ElementD<T>current=head;
        System.out.print("[");
        while (current!=null){
            System.out.print(current.getData());
            if (current.getNext()!=null){
                System.out.print(",");
            }
            current = current.getNext();
        }
        System.out.println("]");
    }

    @Override
    public String toString(){
        String s="[";
        for(int i=0; i< len(); i++){
            s += get(i);
            if(i < len() - 1){
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
