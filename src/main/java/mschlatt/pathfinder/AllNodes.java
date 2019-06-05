package mschlatt.pathfinder;

public class AllNodes
{
    int number;
    double costs;
    int parent;

    public AllNodes(int number, double costs, int parent) {
        this.number = number;
        this.costs = costs;
        this.parent = parent;
    }

    public int getNumber() {
        return number;
    }

    public double getCosts() {
        return costs;
    }

    public int getParent() {
        return parent;
    }
}
