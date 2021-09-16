package Bamboo.model;

import Bamboo.controller.CubeVector;
import java.awt.Color;

public class Tile
{
    private Color color;
    private CubeVector vector;
    private boolean coloured;

    public Tile(CubeVector vector)
    {
        this.color = Color.white;
        this.vector = vector;
        this.coloured = false;
    }

    public void setColour(Color color) throws TileAlreadyColouredException
    {
        if(this.color != Color.WHITE)
            throw new TileAlreadyColouredException();
        this.color = color;
    }

    public Color getColour()
    {
        return color;
    }

    public CubeVector getVector()
    {
        return vector;
    }

    public boolean isCouloured()
    {
        return coloured;
    }

    public void colouring()
    {
        coloured = true;
    }
}
