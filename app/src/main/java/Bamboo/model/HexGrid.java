package Bamboo.model;

import Bamboo.controller.Vector3d;

import java.util.List;

public interface HexGrid
{
    Tile getTile(Vector3d v);

    void setTile(Vector3d v, Colour c);

    List<Tile> getAllNeighbours(Vector3d v);
}