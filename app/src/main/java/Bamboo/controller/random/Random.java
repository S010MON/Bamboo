package Bamboo.controller.random;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.Tile;

import java.awt.*;
import java.util.Collections;
import java.util.Stack;

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
    public boolean isHuman()
    {
        return false;
    }

    @Override
    public Vector getNextMove(Game game)
    {
        // Add a delay to the random algorithm
        try {Thread.sleep(1000); } catch (Exception exception){}

        Stack<Vector> stack = new Stack<>();
        for(Tile t: game.getAllTiles())
        {
            if(!t.isCouloured())
                stack.add(t.getVector());
        }
        Collections.shuffle(stack);

        boolean found = false;
        Vector v = null;
        while(!found)
        {
            v = stack.pop();
            if(game.getGrid().isLegalMove(v, game.getCurrentPlayer().getColor()))
                found = true;
        }
        return v;
    }

    @Override
    public Color getColor()
    {
        return colour;
    }
}
