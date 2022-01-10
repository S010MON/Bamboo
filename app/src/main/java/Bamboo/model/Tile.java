package Bamboo.model;

import Bamboo.controller.Vector;
import java.awt.Color;

import java.awt.*;
import java.util.ArrayList;

public class Tile
{
    private Color color;
    private Vector vector;

    private Color outline;
    private BasicStroke circle_thickness;
    private ArrayList<Tile> groupNeighbours;
    private boolean isCouloured;

    public Tile(Vector vector)
    {
        this.color = Color.white;
        this.vector = vector;
        this.outline = Color.black;
        this.circle_thickness = new BasicStroke(3);
        this.groupNeighbours = new ArrayList<>();
    }

    public void setColour(Color color)
    {
        this.color = color;
    }

    public ArrayList<Tile> getGroupNeighbours()
    {
        return groupNeighbours;
    }

    public void addNeighbour(Tile neighbour)
    {
        groupNeighbours.add(neighbour);
    }

    public void removeNeighbour(Tile neighbour)
    {
        groupNeighbours.remove(neighbour);
    }

    public Color getColour()
    {
        return color;
    }

    public Vector getVector()
    {
        return vector;
    }

    public Color getOutline()
    {
        return outline;
    }

    public void setOutline(Color greenOutline)
    {    
        outline = greenOutline;
    }

    public void setCircle_thickness(BasicStroke ct)
    {
        circle_thickness = ct;
    }

    public BasicStroke getCircle_thickness()
    {
        return circle_thickness;
    }

    public String toCSV()
    {
        return vector.toCSV() + "," + getColourAsString();
    }

    private String getColourAsString()
    {
        if(color.equals(Color.BLUE))
            return "BLUE";
        else if (color.equals(Color.RED))
            return "RED";
        else
            return "WHITE";
    }

    public void color()
    {
        isCouloured = true;
    }

    public boolean isCouloured()
    {
        return isCouloured;
    }
}
