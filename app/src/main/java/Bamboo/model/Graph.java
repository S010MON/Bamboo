package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.awt.*;
import java.util.*;

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

    public boolean testLegalMove(CubeVector vector, Color color)
    {
        nodes.get(vector.toString()).setColor(color);
        setAllUnvisited();
        Stack<CubeVector> stack = getNewUnvisitedStack();
        ArrayList<ArrayList<CubeVector>> groups = new ArrayList<>();

        while (!stack.isEmpty())
        {
            CubeVector currentVector = stack.pop();
            Node currentNode = nodes.get(currentVector.toString());
            if(currentNode.isNotVisited())
                groups.add(getGroupFromVector(currentVector));
        }

        int blueGroups = 0;
        int redGroups = 0;
        int maxSize = 0;
        for(ArrayList<CubeVector> group: groups)
        {
            Node firstElement = nodes.get(group.get(0).toString());

            if(group.size() > maxSize)
                maxSize = group.size();

            if(firstElement.getColor().equals(Color.BLUE))  // Count blue groups
                blueGroups++;

            else if (firstElement.getColor().equals(Color.RED)) // Count red groups
                redGroups++;
        }

        nodes.get(vector.toString()).setColor(Color.WHITE);
        setAllUnvisited();
        return blueGroups < maxSize || redGroups < maxSize;
    }

    private ArrayList<CubeVector> getGroupFromVector(CubeVector vector)
    {
        ArrayList<CubeVector> group = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(vector.toString()));
        while (!stack.isEmpty())
        {
            Node currentNode = stack.pop();
            currentNode.visit();
            group.add(currentNode.getVector());
            for(Node n: currentNode.getUnvisitedNeighbours())
            {
                stack.push(n);
            }
        }
        return group;
    }

    private void setAllUnvisited()
    {
        Set<String> keySet = nodes.keySet();
        for(String key : keySet)
        {
           nodes.get(key).setUnvisied();
        }
    }

    private Stack<CubeVector> getNewUnvisitedStack()
    {
        Stack<CubeVector> stack = new Stack<>();
        Set<String> keySet = nodes.keySet();
        for(String key : keySet)
        {
            stack.add(nodes.get(key).getVector());
        }
        return stack;
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
