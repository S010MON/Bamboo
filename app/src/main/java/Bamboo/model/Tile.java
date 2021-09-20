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
    private ArrayList<Tile> testNeighbours;  // TODO Add all test edges

    private Color testColor;
    private boolean testMode = false;

    public Tile(Vector vector)
    {
        this.color = Color.white;
        this.vector = vector;
        this.outline = Color.black;
        this.circle_thickness = new BasicStroke(3);
        this.groupNeighbours = new ArrayList<>();
    }

    public void setColour(Color color) throws TileAlreadyColouredException
    {
        if(this.color != Color.WHITE)
            throw new TileAlreadyColouredException();
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

    public Color getColour()
    {
        if(testMode)
            return testColor;
        return color;
    }

    public void activateTestMode(Color color)
    {
        testMode = true;

        testColor = color;
    }

    public void  deactivateTestMode()
    {
        testMode = false;
        testColor = Color.white;
    }

    public Vector getVector()
    {
        return vector;
    }

    public Color getOutline(){
        return outline;
    }

    public void setOutline(Color greenOutline){
        outline = greenOutline;
    }

    public void setCircle_thickness(BasicStroke ct) {
        circle_thickness = ct;
    }

    public BasicStroke getCircle_thickness() {
        return circle_thickness;
    }

    public boolean isColoured()
    {
        return !(color == Color.WHITE);
    }
}
