package Bamboo.controller;

import Bamboo.controller.heuristics.Heuristic;
import Bamboo.model.Game;

import java.awt.Color;

public interface Agent
{
    String getName();
    String getType();
    boolean isHuman();
    Vector getNextMove(Game game);
    Color getColor();
    Mutable<Integer> getDepth();
    Mutable<Integer> getIterations();
    Mutable<Float> getC();
    Mutable<Heuristic> getHeuristic();
    Mutable<Integer> getSwitchThreshold();
}
