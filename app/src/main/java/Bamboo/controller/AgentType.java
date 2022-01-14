package Bamboo.controller;

import java.util.Arrays;

public enum AgentType
{
    MINIMAX_AB,
    MINIMAX_SORTED,
    MCTS,
    RANDOM,
    RANDOM_WITH_HEURISTIC,
    NEURAL_NET,
    HYBRID_NNMM;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
