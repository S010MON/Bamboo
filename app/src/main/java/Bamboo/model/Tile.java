package Bamboo.model;

import Bamboo.controller.Vector;

public class Tile
{
    private Colour colour;
    private Vector vector;

    public Tile(Colour colour, Vector vector)
    {
        this.colour = Colour.NONE;
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

    public Vector getVector() {
        return vector;
    }
}
