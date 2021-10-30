package Bamboo.controller;

import Bamboo.controller.MiniMax.*;

import java.awt.*;

public class AgentFactory
{
    public static Agent makeAgent(AgentType type, Color color)
    {
        switch (type)
        {
            case RANDOM: return new Random(color);
            case MINIMAX: return new MiniMax(color);
            case MINIMAX_SORTED: return new sortedABMiniMax(color);
            case MINIMAX_AB: return new abMiniMax(color);
            case MCTS: return new Random(color);                // TODO Replace with MCTS implementation
            case NEURAL_NET: return new Random(color);          // TODO Replace with NNet implementation
        }
        return null;
    }
}
