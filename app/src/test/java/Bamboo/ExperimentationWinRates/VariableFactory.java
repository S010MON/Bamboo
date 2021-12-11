package Bamboo.ExperimentationWinRates;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;

public class VariableFactory {
    public static Mutable getValueFromVariable(Variable v, Agent a, Tester t){
        return switch (v){
            case GRID_SIZE: yield t.boardSize;
            case SEARCH_DEPTH: yield a.getDepth();
            case C: yield a.getC();
            case ITERATIONS: yield a.getIterations();
            case HIDDEN_LAYER_SIZE: yield null;
            case NUM_HIDDEN_LAYERS: yield null;
        };
    }
}
