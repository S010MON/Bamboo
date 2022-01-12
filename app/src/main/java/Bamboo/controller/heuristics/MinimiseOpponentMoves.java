package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.*;

import java.awt.Color;
import java.util.*;


public class MinimiseOpponentMoves implements Heuristic
{
    @Override
    public Vector getNextMove(Grid grid, Color playerColour)
    {
        Color opponentColour = flip(playerColour);
        Queue<Move> moves = new PriorityQueue<>(new MinMovesComparator());

        ArrayList<Vector> remainingMovesList = (ArrayList<Vector>) grid.getRemainingMovesList();
        Collections.shuffle(remainingMovesList);
        for(Vector v: remainingMovesList)
        {
            Grid g = grid.copy();
            g.setTile(v, playerColour);
            int remainingMoves = countLegalMoves(g, opponentColour);
            moves.add(new Move(v, remainingMoves));
        }

        while (!moves.isEmpty())
        {
            Move m = moves.poll();
            if(grid.isLegalMove(m.v, playerColour))
                return m.v;
        }
        return null;
    }

    private int countLegalMoves(Grid grid, Color playerColour)
    {
        int sum = 0;
        for(Tile t: grid.getAllTiles())
        {
            if(t.getColour() == playerColour)
                sum++;
        }
        return sum;
    }

    private Color flip(Color colour)
    {
        if(colour.equals(Color.RED))
            return Color.blue;
        return Color.RED;
    }

    @Override
    public String getType() {
        return "MinOpponentMoves";
    }

    /**
     * A class to hold a Move (the tuple of vector and number of remaining move after that
     * vector is played).
     */
    class Move
    {
        public Vector v;
        public int remainingMoves;

        public Move(Vector v, int remainingMoves)
        {
            this.v = v;
            this.remainingMoves = remainingMoves;
        }
    }

    /**
     * A class to compare moves by the number of remaining moves the opponent has once plaued
     */
    class MinMovesComparator implements Comparator<Move>
    {
        @Override
        public int compare(Move x, Move y)
        {
            return Integer.compare(x.remainingMoves, y.remainingMoves);
        }
    }
}