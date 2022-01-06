package Bamboo.controller.MCTS;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.model.Game;

import java.awt.Color;

public class MCTS implements Agent
{
    private Color colour;
    private NodeMCTS root;
    public Mutable<Integer> iterations = new Mutable<>(100);
    private int iter = 10000;
    private boolean testing = false;
    public Mutable<Float> c = new Mutable<>(2.5f);

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

        if(root == null)
            root = new NodeMCTS(game.getGrid(), null, game.getCurrentPlayer().getColor());
        // Make the move that has just been played (to prune tree)
        else
            root = root.selectNode(game.getPreviousMove());


        for(int i = 0; i < iterations.get(); i++)
        {
            root.selectAndExpand();
        }
        NodeMCTS bestMove = root.selectBest();
        root = root.selectBest();
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