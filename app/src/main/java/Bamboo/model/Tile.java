package Bamboo.model;

public class Tile
{
    public Colour colour;

    public Tile(Colour colour)
    {
        this.colour = Colour.EMPTY;
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
}
