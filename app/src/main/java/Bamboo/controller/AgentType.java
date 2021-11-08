package Bamboo.controller;

import java.util.Arrays;

public enum AgentType
{
    MINIMAX,
    MINIMAX_AB,
    MINIMAX_SORTED,
    MCTS,
    RANDOM,
    NEURAL_NET;

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
