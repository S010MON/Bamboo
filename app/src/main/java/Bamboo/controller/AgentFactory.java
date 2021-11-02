package Bamboo.controller;

import Bamboo.controller.MCTS.MCTS;
import Bamboo.controller.miniMax.*;
import Bamboo.controller.random.Random;

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
            case MCTS -> new MCTS(color);
            case NEURAL_NET -> new Random(color);          // TODO Replace with NNet implementation
        };
    }
}
