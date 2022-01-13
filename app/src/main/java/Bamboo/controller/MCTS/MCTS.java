package Bamboo.controller.MCTS;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.heuristics.OuterWeighted;
import Bamboo.controller.heuristics.Uniform;
import Bamboo.model.Game;

import java.awt.Color;

public class MCTS implements Agent
{
    private Color colour;
    private Node root;
    private boolean testing = false;
    public Mutable<Integer> iterations = new Mutable<>(1000);
    public Mutable<Float> c = new Mutable<>(1.414f);
    public Mutable<Heuristic> heuristic = new Mutable<>(new Uniform());

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
        Node lastMove = null;
        if(root != null)
            lastMove = root.selectChild(game.getPreviousMove());

        if(lastMove != null)
            root = lastMove;
        else
            root = new Node(game.getGrid(), game.getCurrentPlayer().getColor(), null, null);
        UCB.C = c.get();
        int mutableValue = 0;
        if(testing)
            mutableValue = Math.round((float)(Number)iterations.get());
        int iter = testing ? mutableValue : iterations.get();
        for(int i = 0; i < iter; i++)
        {
            Node n = root.select();
            n.setHeuristic(heuristic.get());
            if(n != null)
            {
                n.playout();
                n.backprop();
            }
        }

        root = root.bestSelect();
        root.pruneAbove();
        return root.move();
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
        testing = true;
        return this.c;
    }

    @Override
    public Mutable<Heuristic> getHeuristic() {
        return heuristic;
    }
        
    @Override
    public Mutable<Integer> getSwitchThreshold() {
        return null;
    }

    public int getTreeWidth()
    {
        return 0;
    }

    public int getTreeHeight()
    {
        return root.getHeight();
    }
}