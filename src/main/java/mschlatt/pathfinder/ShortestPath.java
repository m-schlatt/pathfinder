package mschlatt.pathfinder;

import mschlatt.pathfinder.JsonImport.Edge;
import mschlatt.pathfinder.JsonImport.GraphForm;

import java.util.ArrayList;
import java.util.List;

public class ShortestPath {
    private List<AllNodes> nodes = new ArrayList<>();

    private GraphForm jsonImport;

    private int start;

    private int end;

    public ShortestPath(List<AllNodes> allnodes, int start, int end) {
        this.nodes.addAll(allnodes);
        this.start = start;
        this.end = end;
        jsonImport = Directions.getJsonImport();
    }

    public List<Integer> getShortestPath() {
        List<Integer> shortPath = new ArrayList<>();

        int nextOne = getNextNode(end);

        shortPath.add(nextOne);

        do {
            nextOne = getNextNode(nextOne);
            shortPath.add(nextOne);
        } while (getNextNode(nextOne) != start);

        return shortPath;
    }

    public int getNextNode(int recentNode) {
        List<Integer> allNext = new ArrayList<>();

        for (Edge e : jsonImport.edges) {
            if (e.source == recentNode) {
                allNext.add(e.getTarget());
            }
            if (e.target == recentNode) {
                allNext.add(e.getSource());
            }
        }

        double nextCost = nodes.get(allNext.get(0)).getCosts();
        int nextNode = nodes.get(allNext.get(0)).getNumber();

        // geringste Kosten aller nächsten Knoten herausfinden!

        for (int i : allNext) {
            if (nextCost > nodes.get(i).getCosts()) {
                nextCost = nodes.get(i).getCosts();
                nextNode = nodes.get(i).getNumber();
            }
        }
        return nextNode;
    }

}