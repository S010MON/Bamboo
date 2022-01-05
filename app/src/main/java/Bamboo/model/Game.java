package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;

import java.util.List;

public interface Game
{
    Grid getGrid();

    Agent getCurrentPlayer();

    Agent getCurrentOpponent();

    List<Tile> getAllTiles();

    Game copy();

    boolean isFinished();

    Vector getPreviousMove();
}
