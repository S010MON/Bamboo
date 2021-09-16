package Bamboo.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph
{
    private ArrayList<Edge> edges;
    private HashMap<String, Node> nodes;

    public Graph(HashMap<String, Node> nodes, ArrayList<Edge> edges)
    {
        this.nodes = nodes;
        this.edges = edges;

        for(Edge e: edges)
        {
            Node v = nodes.get(e.v.toString());
            Node u = nodes.get(e.u.toString());
            v.addNeighbour(u);
            u.addNeighbour(v);
        }
    }

    public void printEdges()
    {
        edges.forEach(e -> System.out.println());
    }

    public void printNodes()
    {
        nodes.keySet().forEach(e -> System.out.println());
    }
}
