package mschlatt.pathfinder;

import mschlatt.pathfinder.JsonImport.GetJsonGraph;
import mschlatt.pathfinder.JsonImport.GraphForm;
import mschlatt.pathfinder.JsonImport.Node;

public class Directions
{
    private static GraphForm jsonImport;


    public Directions() {
        jsonImport = new GetJsonGraph().loadJsonFile();
    }

    public static GraphForm getJsonImport() {
        return jsonImport;
    }

    public int getSomePoint(String point) {
        int i = 0;
        for (Node n : jsonImport.nodes)
        {
            if (((String) jsonImport.nodes.get(i).label).equals(point))
            {
                return i;
            }
            i++;
        }
        return 0;
    }

}
