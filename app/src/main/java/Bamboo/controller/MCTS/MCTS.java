package Bamboo.controller.MCTS;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.heuristics.Uniform;
import Bamboo.model.Game;

import java.awt.Color;

public class MCTS implements Agent
{
    private Color colour;
    private Node root;
    public Mutable<Integer> iterations = new Mutable<>(200);
    public Mutable<Float> c = new Mutable<>(1f);
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

        for(int i = 0; i < iterations.get(); i++)
        {
            Node n = root.select();
            n.playout();
            n.backprop();
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
        return this.iterations;
    }

    @Override
    public Mutable<Float> getC() {
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
}