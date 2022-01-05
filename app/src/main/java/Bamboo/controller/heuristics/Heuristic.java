package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;

import java.awt.*;

public interface Heuristic
{
    public Vector getNextMove(Grid grid, Color currentPlayer);
}
