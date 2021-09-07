package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.util.List;

public interface Grid
{
    Tile getTile(CubeVector v);

    void setTile(CubeVector v, Colour c);

    List<Tile> getAllNeighbours(CubeVector v);

    List<Tile> getAllTiles();
}