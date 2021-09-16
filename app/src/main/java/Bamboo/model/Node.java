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

    public void addNeighbour(Node node)
    {
        neighbours.add(node);
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

    public void visit()
    {
        visited = true;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public Color getColor()
    {
        return color;
    }

    @Override
    public String toString()
    {
        return vector.toString() + " " + color.toString() + " visited=" + visited;
    }
}
