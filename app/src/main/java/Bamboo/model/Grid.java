package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.awt.Color;
import java.util.List;

public interface Grid
{
    Tile getTile(CubeVector v);

    void setTile(CubeVector v, Color c) throws TileAlreadyColouredException;

    List<Tile> getAllNeighbours(CubeVector v);

    List<Tile> getAllTiles();

    List<CubeVector> getAllVectors();
}