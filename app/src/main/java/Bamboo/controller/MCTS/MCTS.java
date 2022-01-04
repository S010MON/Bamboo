package Bamboo.controller.MCTS;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.GameWithoutGUI;

import java.awt.Color;

public class MCTS implements Agent
{
    private Color colour;
    private NodeMCTS root;
    private int iterations = 10000;

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
        int iter;
        if(game instanceof GameWithoutGUI)
            iter = ((GameWithoutGUI) game).MCTSiterations;
        else
            iter = iterations;

        root = new NodeMCTS(game, null, game.getCurrentPlayer().getColor(), null);
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
}