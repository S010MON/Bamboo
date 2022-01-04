package Bamboo.controller.MCTS;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.GameWithoutGUI;

import java.awt.Color;

public class MCTS implements Agent
{
    private Color colour;
    private NodeMCTS root;
    public Mutable<Integer> iterations = new Mutable<>(10000);
    private int iter = 10000;
    private boolean testing = false;
    public Mutable<Float> c = new Mutable<>(0.5f);

    public MCTS(Color colour)
    {
        this.colour = colour;
    }

    @Override
    public String getName() {
        return "MCTS";
    }

    @Override
    public String getType() {
        return "AI";
    }

    @Override
    public boolean isHuman()
    {
        return false;
    }

    @Override
    public Vector getNextMove(Game game)
    {
        UCB.C = this.c.get();
        root = new NodeMCTS(game.getGrid(), null, game.getCurrentPlayer().getColor(), null);
        if(!testing)iter = iterations.get();
        else iter = Math.round((float)(Number)iterations.get());
        for(int i = 0; i < iter; i++)
        {
            NodeMCTS next = root.select();
            root.expand(next);
            next.backProp(next.playout());
        }
        NodeMCTS bestMove = root.selectBest();
        return bestMove.getMove();
    }

    @Override
    public Vector getNextMove(GameWithoutGUI game)
    {
        UCB.C = this.c.get();
        root = new NodeMCTS(game.getGrid(), null, game.getCurrentPlayer().getColor(), null);
        if(!testing)iter = iterations.get();
        else iter = Math.round((float)(Number)iterations.get());
        for(int i = 0; i < iter; i++)
        {
            NodeMCTS next = root.select();
            root.expand(next);
            next.backProp(next.playout());
        }
        NodeMCTS bestMove = root.selectBest();
        return bestMove.getMove();
    }

    @Override
    public Color getColor() {
        return colour;
    }

    @Override
    public Mutable<Integer> getDepth() {
        return null;
    }

    @Override
    public Mutable<Integer> getIterations() {
        testing = true;
        return this.iterations;
    }

    @Override
    public Mutable<Float> getC() {
        return this.c;
    }
}