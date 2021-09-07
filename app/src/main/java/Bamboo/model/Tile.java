package Bamboo.model;

import Bamboo.controller.CubeVector;

public class Tile
{
    private Colour colour;
    private CubeVector vector;

    public Tile(CubeVector vector)
    {
        this.colour = Colour.NONE;
        this.vector = vector;
    }

    public void setColour(Colour colour)
    {
        // Test that this is the first (and only) colour change
        assert colour != Colour.BLUE && colour != Colour.RED;
        this.colour = colour;
    }

    public Colour getColour()
    {
        return colour;
    }

    public CubeVector getVector() {
        return vector;
    }
}
