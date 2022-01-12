package Bamboo.TestingAPI;

import Bamboo.controller.heuristics.*;

public class HeuristicsFactory {
     public static Heuristic getHeuristic(Heuristics h){
        return switch(h){
            case UNIFORM: yield new Uniform();
            case OUTER_WEIGHTED: yield new OuterWeighted();
            case MAXIMISE_MOVES: yield new MaximiseMoves();
            case SPARSITY: yield new Sparsity();

            case SPARSITY_OUTER_WEIGHTED: yield new SparsityAndOuterWeighted();
            case OPPONENT_MOVES: yield new MinOpponentMoves();
        };
    }
}
