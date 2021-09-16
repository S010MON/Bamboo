package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.awt.Color;
import java.util.ArrayList;

public class Node
{
    private ArrayList<Node> neighbours;
    private CubeVector vector;
    private Color color;
    private boolean visited;

    public Node(CubeVector vector, Color color)
    {
        this.vector = vector;
        this.color = color;
        visited = false;
        neighbours = new ArrayList<>();
    }

    public ArrayList<Node> getUnvisitedNeighbours()
    {
        ArrayList<Node> unvisitedNeighbours = new ArrayList<>();
        for(Node n: neighbours)
        {
            if(!n.isVisited())
                unvisitedNeighbours.add(n);
        }
        return unvisitedNeighbours;
    }
    public void addNeighbour(Node node)
    {
        neighbours.add(node);
    }

    public void visit()
    {
        visited = true;
    }

    public void setUnvisied()
    {
        visited = false;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public boolean isNotVisited()
    {
        return !isVisited();
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public CubeVector getVector()
    {
        return vector;
    }

    @Override
    public String toString()
    {
        return vector.toString() + " " + color.toString() + " visited=" + visited;
    }
}
