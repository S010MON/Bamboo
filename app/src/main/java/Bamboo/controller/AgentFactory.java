package Bamboo.controller;

import Bamboo.controller.MCTS.MCTS;
import Bamboo.controller.hybridNNMM.hybridAgent;
import Bamboo.controller.miniMax.*;
import Bamboo.controller.nNet.NeuralNetwork;
import Bamboo.controller.random.Random;

import java.awt.Color;
import java.io.IOException;

public class AgentFactory
{
    public static Agent makeAgent(AgentType type, Color color) throws IOException {
        return switch (type) {
            case RANDOM -> new Random(color);
            case MINIMAX_SORTED -> new MiniMaxSortedAB(color);
            case MINIMAX_AB -> new MiniMaxAB(color);
            case MCTS -> new MCTS(color);
            case NEURAL_NET -> new NeuralNetwork(color);
            case HYBRID_NNMM -> new hybridAgent(color);
        };
    }
}
