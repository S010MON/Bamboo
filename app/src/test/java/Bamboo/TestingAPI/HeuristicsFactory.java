package Bamboo.TestingAPI;

import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.heuristics.OuterWeighted;
import Bamboo.controller.heuristics.Sparsity;
import Bamboo.controller.heuristics.Uniform;

public class HeuristicsFactory {
     public static Heuristic getHeuristic(Heuristics h){
        return switch(h){
            case UNIFORM: yield new Uniform();
            case OUTER_WEIGHTED: yield new OuterWeighted();
            case SPARSITY: yield new Sparsity();
        };
    }
}
