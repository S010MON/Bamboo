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
            v = generateRandomVector(max);
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
     * @return a random number v where:
     *  -max < v_x < max
     *  -max < v_y < max
     *  -max < v_z < max
     *  and v_x + v_y + v_z = 0
     */
    public Vector generateRandomVector(int max)
    {
        boolean keepGoing = true;
        Vector v = new Vector(0,0,0);
        while (keepGoing)
        {
            int x = generateRandomInt(max);
            int y = generateRandomInt(max);
            int z = (-x) + (-y);

            if( x+y+z == 0)
                keepGoing = false;

            v = new Vector(x,y,z);
        }
        return v;
    }

    public int generateRandomInt(int max)
    {
        boolean negative = Math.random() < 0.5;
        int n = 1;
        if(negative)
            n = -1;

        return (int) (Math.random() * n * max);
    }
}
