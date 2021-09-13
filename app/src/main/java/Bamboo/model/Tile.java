package Bamboo.model;

import Bamboo.controller.CubeVector;
import java.awt.Color;

import java.awt.*;

public class Tile
{
    private Color color;
    private CubeVector vector;

    public Tile(CubeVector vector)
    {
        this.color = Color.white;
        this.vector = vector;
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
}
