package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public interface Grid
{
    Tile getTile(Vector v);

    void setTile(Vector v, Color c);

    List<Tile> getAllNeighbours(Vector v);

    List<Tile> getAllTiles();

    List<Vector> getAllVectors();

    boolean isLegalMove(Vector vector, Color color);

    ArrayList<ArrayList<Vector>> getAllGroupsOfColour(Color color);

    ArrayList<Vector> getGroup(Vector vector);

    int getMaxGroupSize(Color colour);

    int evaluateGame();

    boolean isFinished(Color currentColour);

    Grid copy();
}