package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Game;

public interface Heuristic
{
    public Vector getNextMove(Game game);
    public String getType();
}
