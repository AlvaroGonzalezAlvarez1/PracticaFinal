package Estructuras;

public class Graph<DN,DA>{

    private IndexedList<GraphNode<DN>> nodes;
    private IndexedList<EdgeGraph<DN,DA>> edges;

    //Constructor

    public Graph(){
        nodes=new IndexedList<>();
        edges=new IndexedList<>();
    }

    //Getters


    public IndexedList<GraphNode<DN>> getNodes() {
        return nodes;
    }

    public IndexedList<EdgeGraph<DN, DA>> getEdges() {
        return edges;
    }

    //Métodos

    //Método que devuelve un True si el grafo está vacío
    public boolean isEmpty(){
        boolean empty=false;
        if(nodes.len()==0 && edges.len()==0){
            empty=true;
        }
        return empty;
    }

    //Deja el grafo vacío
    public void clear(){
        nodes.clear();
        edges.clear();
    }

    //Devuelve true si el nodo con un determinado dato existe en el grafo
    public boolean existsNode(DN data){
        boolean exists=false;
        int i=0;
        //Recorre la lista comprando cada elemento
        while(i<nodes.len() && exists==false){
            GraphNode<DN> current=nodes.get(i);
            if(current.getData().equals(data)==true){
                exists=true;
            }
            i=i+1;
        }
        return exists;
    }


    //No se podrán tener dos nodos duplicados por dato aunque el objeto sea distinto
    public void addNode(DN data){
        //Comprueba si el nodo existe en la lista
        boolean exists=existsNode(data);
        if(exists==false){
            //Si no existe se añade el nodo a la lista de nodos
            GraphNode<DN> newNode=new GraphNode<>(data);
            nodes.append(newNode);
        }
    }

    //Devuelve el nodo, con el tipo de dato especificado
    //Si no se encuentra devuelve null
    //Todos los métodos que usen getNode controlan posibles null
    public GraphNode<DN> getNode(DN data){
        //Inicializamos el nodo con un null
        GraphNode<DN> result=null;
        boolean found=false;
        int i=0;
        while(i<nodes.len() && found==false){
            GraphNode<DN> current=nodes.get(i);
            if(current.getData().equals(data)==true){
                //Si lo encuentra se actualiza la variable resultado
                result=current;
                found=true;
            }
            i=i+1;
        }
        return result;
    }

    //Mismo procedimiento que con el nodo, comprueba si existe la arista ya en el grafo
    public boolean existsEdge(DN origin,DN destination){
        boolean exists=false;
        int i=0;
        while(i<edges.len() && exists==false){
            EdgeGraph<DN,DA> edge=edges.get(i);
            if(edge.getStart().getData().equals(origin)==true&&edge.getEnd().getData().equals(destination)==true){
                exists=true;
            }
            i=i+1;
        }
        return exists;
    }

    //Añade una arista uniendo dos nodos, si los nodos no existen, los crea
    public void addEdge(DN origin, DN destination, DA data){
        GraphNode<DN> startNode=getNode(origin);
        if(startNode==null){
            addNode(origin);
            startNode = getNode(origin);
        }
        GraphNode<DN> endNode = getNode(destination);
        if(endNode == null){
            addNode(destination);
            endNode = getNode(destination);
        }
        //Método modificado para poder añadir varias puertas entre las mismas habitaciones
        EdgeGraph<DN,DA> newEdge = new EdgeGraph<>(startNode, endNode, data);
        edges.append(newEdge);

    }

    public void addUndirectedEdge(DN origin, DN destination, DA data) {
        addEdge(origin,destination,data);
        addEdge(destination,origin,data);
    }


    //Solo elimina la arista de la lista de aristas que están contenidas en el grafo
    public void removeEdge(DN origin,DN destination){
        int i=0;
        int pos=-1;
        //Recorre la lista de aristas
        while(i<edges.len() && pos==-1){
            EdgeGraph<DN,DA> edge=edges.get(i);
            //Comprueba que coinciden origen y destino
            if(edge.getStart().getData().equals(origin)==true && edge.getEnd().getData().equals(destination)==true){
                pos=i;
            }
            i=i+1;
        }
        //Si recorre toda la lista y no lo encuentra, pos no se actualiza y no hace nada
        if(pos!=-1){
            edges.delete(pos);
        }
    }

    public EdgeGraph<DN,DA> getEdge(DN origin,DN destination){
        EdgeGraph<DN,DA> result=null;
        boolean exists=existsEdge(origin,destination);
        int i=0;
        boolean found=false;
        while(i<edges.len()&&found==false){
            EdgeGraph<DN,DA> edge=edges.get(i);
            if(edge.getStart().getData().equals(origin)==true&&edge.getEnd().getData().equals(destination)==true){
                result=edge;
                found=true;
            }
            i=i+1;
        }
        return result;
    }

    //cuidado con return lista vacía
    //Método que devuelve una lista con todos los nodos a los que "apunta" el nodo en cuestión
    public IndexedList<GraphNode<DN>> neighbors(DN node){
        IndexedList<GraphNode<DN>> result=new IndexedList<>();
        //Buscamos primero el nodo en el grafo
        GraphNode<DN> origin=getNode(node);
        //Si no existe devuelve lista vacía
        if(origin!=null){
            int i=0;
            //Se recorre todas las aristas
            while(i<edges.len()){
                EdgeGraph<DN,DA> edge=edges.get(i);
                //Si coincide se añade a la lista que va a retornar
                if(edge.getStart().getData().equals(node)==true){
                    result.append(edge.getEnd());
                }
                i=i+1;
            }
        }
        return result;
    }

    //Devuelve una lista con las aristas que "salen del nodo"
    public IndexedList<EdgeGraph<DN,DA>> edgesFrom(DN node){
        IndexedList<EdgeGraph<DN,DA>> result=new IndexedList<>();
        GraphNode<DN> origin=getNode(node);
        if(origin!=null){
            int i=0;
            while(i<edges.len()){
                EdgeGraph<DN,DA> edge=edges.get(i);
                if(edge.getStart().getData().equals(node)==true){
                    result.append(edge);
                }

                i=i+1;
            }
        }
        return result;
    }

    //Igual pero con las que "llegan" al nodo
    public IndexedList<EdgeGraph<DN,DA>> edgesTo(DN node){
        IndexedList<EdgeGraph<DN,DA>> result=new IndexedList<>();
        GraphNode<DN> destination=getNode(node);
        if(destination!=null){
            int i=0;
            while(i<edges.len()){
                EdgeGraph<DN,DA> edge=edges.get(i);
                if(edge.getEnd().getData().equals(node)==true){
                    result.append(edge);
                }
                i=i+1;
            }
        }
        return result;
    }

    //Elimina el nodo, y todas las aristas que lo contienen
    public void removeNode(DN data){
        //Primero busca el nodo por el dato, si no existe no hace nada
        GraphNode<DN> node=getNode(data);
        if(node!=null){
            int i=0;
            //Recorre la lista de aristas
            while(i<edges.len()){
                EdgeGraph<DN,DA> edge=edges.get(i);
                //Si encuentra una arista con el nodo como origen o destino, se elimina
                if(edge.getStart().getData().equals(data)==true || edge.getEnd().getData().equals(data)==true){
                    edges.delete(i);
                    i=i-1;
                }
                i=i+1;
            }
            i=0;
            int pos=-1;
            //Una vez eliminadas todas las aristas, se elimina el nodo de la lista de nodos
            while(i<nodes.len() && pos==-1){
                GraphNode<DN> current=nodes.get(i);
                if(current.getData().equals(data)==true){
                    pos=i;
                }
                i=i+1;
            }
            if(pos!=-1){
                nodes.delete(pos);
            }
        }
    }   //Se podrían eliminar las aristas que devuelven los métodos edgesTo/From
        //pero hace muchos recorridos sobre la misma lista

    //RECORRIDOS

    //Búsqueda en anchura, usando una cola
    public IndexedList<DN> BFS(DN start){
        IndexedList<DN> result=new IndexedList<>();
        IndexedList<DN> visited=new IndexedList<>();
        Queue<GraphNode<DN>> queue=new Queue<>();
        //Primero buscamos el nodo
        GraphNode<DN> startNode=getNode(start);
        if(startNode!=null){
            //Se añade el nodo a la cola y a la lista
            queue.enqueue(startNode);
            visited.append(start);
            //Una vez que la lista esté vacia se termina
            while(queue.isEmpty()==false){
                //Se saca al nodo que se va a "analizar de la cola"
                GraphNode<DN> current=queue.dequeue();
                //Se añade a la lista final
                result.append(current.getData());
                //Se crea una lista de los vecinos de ese nodo
                IndexedList<GraphNode<DN>> neigh=neighbors(current.getData());
                int i=0;
                while(i<neigh.len()){
                    //Para cada vecino:
                    GraphNode<DN> aux=neigh.get(i);
                    DN data=aux.getData();
                    //Si no está visitado
                    if(visited.contains(data)==false){
                        //Lo añado a visitado
                        visited.append(data);
                        //Lo meto en la cola
                        queue.enqueue(aux);
                    }
                    i=i+1;
                }
            }
        }
        return result;
    }

    public IndexedList<DN> BFSConDistancia(DN start, int maxDistancia){
        IndexedList<DN> result=new IndexedList<>();
        IndexedList<DN> visited=new IndexedList<>();
        Queue<GraphNode<DN>> queue=new Queue<>();
        Queue<Integer> distQueue=new Queue<>();
        GraphNode<DN> startNode=getNode(start);
        if(startNode!=null){
            queue.enqueue(startNode);
            distQueue.enqueue(0);
            visited.append(start);
            while(queue.isEmpty()==false){
                GraphNode<DN> current=queue.dequeue();
                int dist=distQueue.dequeue();
                result.append(current.getData());
                if(dist<maxDistancia){
                    IndexedList<GraphNode<DN>> neigh=neighbors(current.getData());
                    int i=0;
                    while(i<neigh.len()){
                        GraphNode<DN> aux=neigh.get(i);
                        DN data=aux.getData();
                        if(visited.contains(data)==false){
                            visited.append(data);
                            queue.enqueue(aux);
                            distQueue.enqueue(dist + 1);
                        }
                        i++;
                    }
                }
            }
        }
        return result;
    }

    //Búsqueda en profundidad, usando la cola
    public IndexedList<DN> DFS(DN start){
        IndexedList<DN> result=new IndexedList<>();
        IndexedList<DN> visited=new IndexedList<>();
        Stack<GraphNode<DN>> stack=new Stack<>();
        //Buscamos el nodo
        GraphNode<DN> startNode=getNode(start);
        if(startNode!=null){
            //Se añade el nodo a la pila y a la lista de visitados
            stack.push(startNode);
            visited.append(start);
            while(stack.isEmpty()==false){
                //Se añade el nodo que se va a analizar de la pila
                GraphNode<DN> current=stack.pop();
                //Se añade a la lista final
                result.append(current.getData());
                //Se obtiene la lista de vecinos de ese nodo
                IndexedList<GraphNode<DN>> neigh=neighbors(current.getData());
                int i=0;
                while(i<neigh.len()){
                    //Para cada vecino:
                    GraphNode<DN> aux=neigh.get(i);
                    DN data=aux.getData();
                    //Si no está visitado
                    if(visited.contains(data)==false){
                        //Se añade en visitados
                        visited.append(data);
                        //Se mete en la pila para seguir buscando a sus vecinos
                        stack.push(aux);
                    }
                    i=i+1;
                }
            }
        }
        return result;
    }

    //Devuelve el camino mínimo entre dos nodos usando BFS
    public IndexedList<DN> shortestPath(DN start,DN end){
        IndexedList<DN> path=new IndexedList<>();
        //Lista de visitados
        IndexedList<DN> visited=new IndexedList<>();
        //Para guardar el "padre" de cada nodo
        IndexedList<DN> parent=new IndexedList<>();
        //Cola para BFS
        Queue<GraphNode<DN>> queue=new Queue<>();
        GraphNode<DN> startNode=getNode(start);
        GraphNode<DN> endNode=getNode(end);
        //Si alguno no existe no hace nada
        if(startNode!=null && endNode!=null){
            //Se inicializa la búsqueda añadiendo el nodo inicial a la cola
            queue.enqueue(startNode);
            //Se marca el nodo inicial como visitado
            visited.append(start);
            //El nodo inicial no tiene padre, por eso se añade null
            //La lista parent guarda "de dónde viene" para cada nodo visitado
            parent.append(null);
            boolean found=false;
            //Bucle principal del BFS modificado, se ejecuta mientras haya nodos por explorar
            //y no se haya encontrado el destino
            while(queue.isEmpty()==false && found==false){
                //Se extrae el siguiente nodo a analizar
                GraphNode<DN> current=queue.dequeue();
                DN currentData=current.getData();
                //Si el nodo actual es el destino, terminamos la búsqueda
                if(currentData.equals(end)==true){
                    found=true;
                }
                else{
                    //Obtenemos los vecinos del nodo actual
                    IndexedList<GraphNode<DN>> neigh=neighbors(currentData);
                    int i=0;
                    //Recorremos todos los vecinos
                    while(i<neigh.len()){
                        GraphNode<DN> aux=neigh.get(i);
                        DN data=aux.getData();
                        //Si el vecino no ha sido visitado todavía:
                        if(visited.contains(data)==false){
                            //Se añade a la cola para explorarlo después
                            queue.enqueue(aux);
                            //Se marca como visitado
                            visited.append(data);
                            //Guardamos su padre
                            parent.append(currentData);
                        }
                        i=i+1;
                    }
                }
            }
            //Reconstrucción del camino mínimo desde el destino hasta el origen
            if(found==true){
                //Empezamos desde el nodo destino
                DN current=end;
                //Mientras haya nodos (hasta llegar al null del inicio)
                while(current!=null){
                    //Insertamos al principio para construir el camino en orden correcto
                    path.insert(current,0);
                    int i=0;
                    boolean done=false;
                    //Buscamos el nodo actual en la lista de visitados
                    //para obtener su padre correspondiente
                    while(i<visited.len() && done==false){
                        if(visited.get(i).equals(current)==true){
                            //Actualizamos current al padre de ese nodo
                            current=parent.get(i);
                            done=true;
                        }
                        i=i+1;
                    }
                }
            }
        }
        return path;
    }

    //Comprueba si es un grafo disjunto
    //Como es dirigido puede haber nodos que no sean accesibles desde un nodo
    //dado por lo que hay que hacer BFS de los nodos no visitados también
    public boolean isDisjoint(){
        IndexedList<DN> visited=new IndexedList<>();
        boolean result=false;
        int i=0;
        //Recorremos todos los nodos del grafo
        while(i<nodes.len()){
            DN node=nodes.get(i).getData();
            //Si no ha sido visitado en ningun BFS anterior
            if(visited.contains(node)==false){
                //Hacemos BFS
                IndexedList<DN> bfsResult=BFS(node);
                int j=0;
                //Añadimos todos los nodos a la lista de visitados
                while(j<bfsResult.len()){
                    DN data=bfsResult.get(j);
                    //Si no estaba en visitados lo añadimos
                    if(visited.contains(data)==false){
                        visited.append(data);
                    }
                    j=j+1;
                }
            }
            i=i+1;
        }
        //Si no hemos podido visitar a todos los nodos del grafo es disjunto
        if(visited.len()!=nodes.len()){
            result=true;
        }
        return result;
    }
}