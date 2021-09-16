package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.Set;

public class Graph
{
    private ArrayList<Edge> edges;
    private HashMap<CubeVector, Node> nodes;

    public Graph(HashMap<CubeVector, Node> nodes, ArrayList<Edge> edges)
    {
        this.nodes = nodes;
        this.edges = edges;

        for(Edge e: edges)
        {
            Node v = nodes.get(e.v);
            Node u = nodes.get(e.u);
            v.addNeighbour(u);
            u.addNeighbour(v);
        }
    }

    public boolean testLegalMove(CubeVector vector, Color color)
    {
        nodes.get(vector).setColor(color);
        setAllUnvisited();
        Stack<CubeVector> stack = getNewUnvisitedStack();
        ArrayList<ArrayList<CubeVector>> groups = new ArrayList<>();

        while (!stack.isEmpty())
        {
            CubeVector currentVector = stack.pop();
            Node currentNode = nodes.get(currentVector);
            if(currentNode.isNotVisited())
                groups.add(getGroupFromVector(currentVector));
        }

        int blueGroups = 0;
        int maxSizeBlue = 0;
        int redGroups = 0;
        int maxSizeRed = 0;
        for(ArrayList<CubeVector> group: groups)
        {
            Node firstElement = nodes.get(group.get(0));

            if(firstElement.getColor().equals(Color.BLUE))  // Count blue groups
            {
                if(group.size() > maxSizeBlue)
                    maxSizeBlue = group.size();
                blueGroups++;
            }
            else if (firstElement.getColor().equals(Color.RED)) // Count red groups
            {
                if(group.size() > maxSizeRed)
                    maxSizeRed = group.size();
                redGroups++;
            }
        }

        nodes.get(vector).setColor(Color.WHITE);
        setAllUnvisited();

        System.out.println( "Blue:\nGroups: " + blueGroups + " Size: " + maxSizeBlue);
        System.out.println( "Red:\nGroups: " + redGroups + " Size: " + maxSizeRed);
        return maxSizeBlue <= blueGroups || maxSizeRed <= blueGroups;
    }

    private ArrayList<CubeVector> getGroupFromVector(CubeVector vector)
    {
        ArrayList<CubeVector> group = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        stack.push(nodes.get(vector));
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
        Set<CubeVector> keySet = nodes.keySet();
        for(CubeVector key : keySet)
        {
           nodes.get(key).setUnvisied();
        }
    }

    private Stack<CubeVector> getNewUnvisitedStack()
    {
        Stack<CubeVector> stack = new Stack<>();
        Set<CubeVector> keySet = nodes.keySet();
        for(CubeVector key : keySet)
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
