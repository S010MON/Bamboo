package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public interface Grid
{
    Tile getTile(Vector v);

    void setTile(Vector v, Color c);

    List<Tile> getAllNeighbours(Vector v);

    List<Tile> getAllTiles();

    List<Vector> getAllRemainingMoves();

    List<Vector> getAllVectors();

    boolean isLegalMove(Vector vector, Color color);

    ArrayList<ArrayList<Vector>> getAllGroupsOfColour(Color color);

    ArrayList<Vector> getGroup(Vector vector);

    int getMaxGroupSize(Color colour);

    int evaluateGame(Color color);

    boolean isFinished(Color currentColour);

    Grid copy();

    int getSize();

    /**
     * @return a shuffled stack of all the remaining
     * available moves in the current grid
     */
    Stack<Vector> getRemainingMoves();
}