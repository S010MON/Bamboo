package Bamboo.controller.MCTS;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.Game;

import java.awt.Color;

public class MCTS implements Agent
{
    private Color colour;
    private NodeMCTS root;
    private int iterations = 10;

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
    public Vector getNextMove(Game game)
    {
        root = new NodeMCTS(game.getGrid(), null, game.getCurrentPlayer().getColor(), null);
        for(int i = 0; i < iterations; i++)
        {
            NodeMCTS next = root.selectAndExpand();
            int result = next.playout();
            next.backProp(result);
        }
        NodeMCTS bestMove = root.select();
        return bestMove.getMove();
    }

    @Override
    public Color getColor() {
        return colour;
    }
}
