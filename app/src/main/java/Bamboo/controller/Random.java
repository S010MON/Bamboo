package Bamboo.controller;

import Bamboo.model.Game;

import java.awt.*;

public class Random implements Agent
{
    private String name = "Ronald";
    private Color colour;

    public Random(Color colour)
    {
        this.colour = colour;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getType()
    {
        return "random";
    }

    @Override
    public Vector getNextMove(Game game)
    {
        int max = game.getBoardSize();
        Vector v = new Vector(0,0,0);

        boolean keepGoing = true;
        while(keepGoing)
        {
            int x = generateRandomInt(max);
            int y = generateRandomInt(max);
            int z = generateRandomInt(max);

            v = new Vector(x, y, z);
            if (game.getGrid().isLegalMove(v, colour))
                keepGoing = false;
        }
        return v;
    }

    @Override
    public Color getColor()
    {
        return colour;
    }

    /**
     * @return a random number n where:
     *  -max < n < max
     */
    public int generateRandomInt(int max)
    {
        boolean negative = Math.random() < 0.5;
        int n = 1;
        if(negative)
            n = -1;

        return (int) (Math.random() * n * max);
    }
}
