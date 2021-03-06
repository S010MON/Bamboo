package Bamboo.controller.random;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.*;
import Bamboo.model.Game;

import java.awt.Color;

public class Random implements Agent
{
    private String name = "Ronald";
    private Color colour;
    public Mutable<Heuristic> heuristic = new Mutable<>(new Uniform());

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
        return heuristic.get().getNextMove(game.getGrid(), game.getCurrentPlayer().getColor());
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
    public Mutable<Heuristic> getHeuristic() {return heuristic;}

    @Override
    public Mutable<Integer> getSwitchThreshold() {
        return null;
    }
}
