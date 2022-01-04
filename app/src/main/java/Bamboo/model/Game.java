package Bamboo.model;

import Bamboo.controller.Agent;

import java.util.List;

public interface Game
{
    public Grid getGrid();

    public Agent getCurrentPlayer();

    public List<Tile> getAllTiles();

    public Game copy();
}
