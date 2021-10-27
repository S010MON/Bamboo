package Bamboo.controller.MCTS;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.Game;

import java.awt.Color;

public class MCTS implements Agent
{
    private Color colour;
    private NodeMCTS root;

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
    public Vector getNextMove(Game game) {
        return null;
    }

    @Override
    public Color getColor() {
        return colour;
    }
}
