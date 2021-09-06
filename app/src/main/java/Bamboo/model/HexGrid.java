package Bamboo.model;

import Bamboo.controller.Vector;

import java.util.List;

public interface HexGrid
{
    Tile getTile(Vector v);

    void setTile(Vector v, Colour c);

    List<Tile> getAllNeighbours(Vector v);

    List<Tile> getAllTiles();
}