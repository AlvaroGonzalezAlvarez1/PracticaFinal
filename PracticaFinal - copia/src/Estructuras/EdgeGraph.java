package Estructuras;

public class EdgeGraph<DN, DA> {
    private DA data;
    private GraphNode<DN> start;
    private GraphNode<DN> end;

    public EdgeGraph(GraphNode<DN> start, GraphNode<DN> end, DA data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    public GraphNode<DN> getStart() {
        return start;
    }

    public GraphNode<DN> getEnd() {
        return end;
    }

    public DA getData() {
        return data;
    }

    public void setData(DA data) {
        this.data = data;
    }

    @Override
    public String toString(){
        String result="";
        result=result+start.getData();
        result=result+" --> ";
        result=result+end.getData();
        return result;
    }
}
