package Bamboo.TestingAPI;

import Bamboo.controller.heuristics.*;

public class HeuristicsFactory {
     public static Heuristic getHeuristic(Heuristics h){
        return switch(h){
            case UNIFORM: yield new Uniform();
            case OUTER_WEIGHTED: yield new OuterWeighted();
            case MAX_NUMBER_OF_GROUPS: yield new MaximiseNumOfGroups();
            case MAXIMISE_MOVES: yield new MaximiseMoves();

        };
    }
}
