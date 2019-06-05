package mschlatt.pathfinder.JsonImport;

import java.util.List;

public class GraphForm {

    public List<Node> nodes = null;
    public List<Edge> edges = null;

    public Node getNode(int i) {
        return nodes.get(i);
    }
    public Edge getEdge(int i) {
        return edges.get(i);
    }

}



