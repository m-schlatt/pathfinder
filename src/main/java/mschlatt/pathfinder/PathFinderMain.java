package mschlatt.pathfinder;

import java.util.ArrayList;
import java.util.List;

public class PathFinderMain {
    private final static String erde = "Erde";

    private final static String destination = "b3-r7-r4nd7";

    private static List<Integer> shortestPath = new ArrayList<>();

    private static double sumCosts;

    public static void main(String[] args) {
        Directions waypoints = new Directions();

        int start = waypoints.getSomePoint(erde);
        int end = waypoints.getSomePoint(destination);

        System.out.println(start + " bis " + end);

        DijkstraImp findPath = new DijkstraImp(start, end);

        findPath.findPath();

        ShortestPath shortCut = new ShortestPath(findPath.getNodes(), start, end);

        shortestPath = shortCut.getShortestPath();

        sumCosts = findPath.getCostsAtTarget();

        printShortestPath(shortestPath);
    }

    public static void printShortestPath(List<Integer> shortestPath) {
        System.out.println(erde);
        // Der Algorithmus sucht den Weg vom Ziel zum Start, nachdem alle Kosten an den Knoten definiert sind.
        // Daher ist der Pfad in unterschiedlicher Reheihenfolge vor der Ausgabe
        for (int i = shortestPath.size() - 1; i >= 0; i--) {
            System.out.println(shortestPath.get(i));
        }
        System.out.println(destination);
        System.out.println("Gesamtkosten " + sumCosts);
    }
}
