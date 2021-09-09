package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.awt.*;

public class Tile
{
    private Color color;
    private CubeVector vector;

    public Tile(CubeVector vector)
    {
        this.color = Color.WHITE;
        this.vector = vector;
    }

    public void setColor(Color color)
    {
        // Test that this is the first (and only) colour change
        assert this.color != Color.BLUE && this.color != Color.RED;
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }

    public CubeVector getVector() {
        return vector;
    }
}
