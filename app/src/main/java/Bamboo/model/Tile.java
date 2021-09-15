package Bamboo.model;

import Bamboo.controller.CubeVector;
import java.awt.Color;

import java.awt.*;

public class Tile
{
    private Color color;
    private CubeVector vector;
    private Color outline;
    private BasicStroke circle_thickness;

    public Tile(CubeVector vector)
    {
        this.color = Color.white;
        this.vector = vector;
        this.outline = Color.black;
        this.circle_thickness = new BasicStroke(3);
    }

    public void setColour(Color color)
    {
        // Test that this is the first (and only) colour change
        assert this.color != Color.BLUE && this.color != Color.RED;
        this.color = color;
    }

    public Color getColour()

    {
        return color;
    }

    public CubeVector getVector() {
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
