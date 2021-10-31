package Bamboo.controller;

import Bamboo.controller.MiniMax.*;

import java.awt.*;

public class AgentFactory
{
    public static Agent makeAgent(AgentType type, Color color)
    {
        return switch (type) {
            case RANDOM -> new Random(color);
            case MINIMAX -> new MiniMax(color);
            case MINIMAX_SORTED -> new MiniMaxSortedAB(color);
            case MINIMAX_AB -> new MiniMaxAB(color);
            case MCTS -> new Random(color);                // TODO Replace with MCTS implementation
            case NEURAL_NET -> new Random(color);          // TODO Replace with NNet implementation
        };
    }
}
