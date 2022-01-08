package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;

import java.awt.Color;

public interface Heuristic
{
    Vector getNextMove(Grid grid, Color currentPlayer);
    String getType();
}
