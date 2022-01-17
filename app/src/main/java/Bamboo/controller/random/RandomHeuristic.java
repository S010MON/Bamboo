package Bamboo.controller.random;

import Bamboo.controller.Mutable;
import Bamboo.controller.heuristics.SparsityAndOuterWeighted;

import java.awt.Color;

public class RandomHeuristic extends Random
{
    public RandomHeuristic(Color colour)
    {
        super(colour);
        heuristic = new Mutable<>(new SparsityAndOuterWeighted());
    }
}
