package Bamboo.controller.random;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.heuristics.OuterWeighted;
import Bamboo.controller.heuristics.Uniform;
import Bamboo.model.Game;

import java.awt.*;

public class Random implements Agent
{
    private String name = "Ronald";
    private Color colour;
    private Heuristic heuristic = new Uniform();

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
        try {Thread.sleep(10); } catch (Exception exception){}
        return heuristic.getNextMove(game);
    }

    @Override
    public Color getColor()
    {
        return colour;
    }

    @Override
    public Mutable<Integer> getDepth() {
        return null;
    }

    @Override
    public Mutable<Integer> getIterations() {
        return null;
    }

    @Override
    public Mutable<Float> getC() {
        return null;
    }

    @Override
    public Mutable<Integer> getSwitchThreshold() {
        return null;
    }
}
