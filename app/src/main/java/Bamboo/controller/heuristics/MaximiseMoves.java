package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.Tile;


import java.awt.*;
import java.util.*;

public class MaximiseMoves implements Heuristic
{
    private Grid grid;
    private Color currentColor;

    @Override
    public Vector getNextMove(Grid grid, Color color)
    {
        this.grid = grid;
        this.currentColor = color;
        Comparator<Vector> comparator = new MovesComparator();
        Queue<Vector> queue = new PriorityQueue<>(91,comparator);
        ArrayList<Vector> vectors = (ArrayList<Vector>) grid.getRemainingMovesList();
        Collections.shuffle(vectors);
        queue.addAll(vectors);
        Vector v;
        while (!queue.isEmpty()) {
            v = queue.remove();
            if (grid.isLegalMove(v, color))
                return v;
        }
        return null;
    }

    @Override
    public String getType() {
        return "MaximiseMoves";
    }

    class MovesComparator implements Comparator<Vector> {
        public int compare(Vector x, Vector y) {
            Grid gridX = grid.copy();
            Grid gridY = grid.copy();
            gridX.setTile(x,currentColor);
            gridY.setTile(y,currentColor);
            int xMoves=0;
            int yMoves=0;
            for(Vector v : gridX.getRemainingMovesList()) {
                boolean xLegal = gridX.isLegalMove(v, currentColor);
                if (xLegal){
                    xMoves++;
                }
            }
            for(Vector v : gridY.getRemainingMovesList()){
                boolean yLegal = gridY.isLegalMove(v, currentColor);
                if(yLegal){
                    yMoves++;
                }
            }
            return Integer.compare(yMoves, xMoves);
        }
    }
}
