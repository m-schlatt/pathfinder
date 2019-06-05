package mschlatt.pathfinder;

import mschlatt.pathfinder.JsonImport.Edge;
import mschlatt.pathfinder.JsonImport.GraphForm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DijkstraImp {
    private List<AllNodes> nodes = new ArrayList<>();

    private List<Edge> queue = new ArrayList<>();

    private GraphForm jsonImport;

    private List<Edge> queueVisited = new ArrayList<>();

    private int start;

    private int end;

    public DijkstraImp(int start, int end) {
        this.start = start;
        this.end = end;
        jsonImport = Directions.getJsonImport();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }


    public double getCostsAtTarget() {
        return nodes.get(end).getCosts();
    }

    public List<AllNodes> getNodes() {
        return nodes;
    }


    public void findPath() {
        initResources(start, jsonImport.nodes.size());
        setUpQueue(start);

        if (!queue.isEmpty()) {
            visitNode(queue.get(0));
        }

        runningLoop();
    }


    public void initResources(int startPoint, int numberNodes) {
        initNodes(startPoint, numberNodes);
    }

    public void initNodes(int startPoint, int numberNodes) {
        for (int i = 0; i < numberNodes; i++) {
            AllNodes element = new AllNodes(i, 9999, 0);
            this.nodes.add(element);
        }
        nodes.get(start).costs = 0;
    }


    public void setUpQueue(int startPoint) {
        // von wo wir sind, das nächste Element - also der nächste Knoten zu den geringsten Kosten
        List<Edge> nextNodes = findNextNodes(startPoint);

        for (Edge q : nextNodes) {
            Edge p = new Edge(); // Inversion von q weil Wege auch umgekehrt durchschritten werden
            p.target = q.getSource();
            p.source = q.getTarget();
            if (!queueVisited.contains(q) && !queueVisited.contains(p)) {
                queue.add(q);
            }
        }
        queue.sort(Comparator.comparing(Edge::getCost));
    }

    public List<Edge> findNextNodes(int startPoint) {
        List<Edge> nextNodes = new ArrayList<>();

        for (Edge e : jsonImport.edges) {
            Edge f = new Edge();
            f.target = e.source;
            f.source = e.target;
            f.cost = e.cost;

            if (!queue.contains(e) && !queue.contains(f)) {
                if (e.source == startPoint && e.target != nodes.get(startPoint).parent) {
                    nextNodes.add(e);
                }
                if (e.target == startPoint && e.source != nodes.get(startPoint).parent) {
                    Edge a = new Edge();
                    a.target = e.source;
                    a.source = e.target;
                    a.cost = e.cost;
                    nextNodes.add(a);
                }
            }
        }
        return nextNodes;
    }

    public void runningLoop() {
        while (!queue.isEmpty()) {
            visitNode(queue.get(0));
        }
    }


    public void visitNode(Edge fromQueue) {
        // geht die Edge  - von source die Kosten:
        double costForPath = nodes.get(fromQueue.source).costs + fromQueue.getCost();

        if (costForPath < nodes.get(fromQueue.target).costs) {
            nodes.get(fromQueue.target).costs = costForPath;
            nodes.get(fromQueue.target).parent = fromQueue.getSource();
            // gib die weiteren Verbindungen von diesem Punkt wieder frei:
            for(Edge visitableAgain: findNextNodes(fromQueue.target)) {
                queueVisited.remove(visitableAgain);
            }
        }
        queueVisited.add(queue.get(0));
        queue.remove(0);
        setUpQueue(fromQueue.target);
    }

}
