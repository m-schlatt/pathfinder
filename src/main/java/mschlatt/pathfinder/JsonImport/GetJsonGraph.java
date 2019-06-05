package mschlatt.pathfinder.JsonImport;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GetJsonGraph
{
    BufferedReader genGraph;

    public GraphForm loadJsonFile() {
        try {
            genGraph = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/generatedGraph.json")));

        }
        catch (Exception e) {
            System.err.println("File konnte nicht geladen werden!");
        }
        Gson jsonGraph = new Gson();
        GraphForm jGraph = jsonGraph.fromJson(genGraph, GraphForm.class);
        return jGraph;
    }
}
