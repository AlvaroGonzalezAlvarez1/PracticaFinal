package Estructuras;

public class GraphNode<DN> {
    private DN data;

    public GraphNode(DN data) {
        this.data = data;
    }

    public DN getData() {
        return data;
    }

    public void setData(DN data) {
        this.data = data;
    }

}