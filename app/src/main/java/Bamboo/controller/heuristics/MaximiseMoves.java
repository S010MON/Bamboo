package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.Tile;


import java.awt.*;
import java.util.*;

public class MaximiseMoves implements Heuristic
{
    private Game game;

    @Override
    public Vector getNextMove(Game game)
    {
        this.game = game;
        Comparator<Vector> comparator = new MovesComparator();
        Queue<Vector> queue = new PriorityQueue<>(91,comparator);
        ArrayList<Tile> tiles = (ArrayList<Tile>) game.getAllTiles();
        Collections.shuffle(tiles);
        for (Tile t : tiles) {
            if (!t.isCouloured())
                queue.add(t.getVector());
        }
        Vector v;
        while (!queue.isEmpty()) {
            v = queue.remove();
            if (game.getGrid().isLegalMove(v, game.getCurrentPlayer().getColor()))
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
            Color currentColor = game.getCurrentPlayer().getColor();
            Grid grid = game.getGrid();
            Grid gridX = grid.copy();
            Grid gridY = grid.copy();
            gridX.getTile(x).setColour(currentColor);
            gridY.getTile(y).setColour(currentColor);
            int xMoves=0;
            int yMoves=0;
            for(Tile tile: gridX.getAllTiles()) {
                boolean xLegal = gridX.isLegalMove(tile.getVector(), currentColor);
                if (xLegal){
                    xMoves++;
                }
            }
            for(Tile tile: gridY.getAllTiles()){
                boolean yLegal = gridY.isLegalMove(tile.getVector(), currentColor);
                if(yLegal){
                    yMoves++;
                }
            }
            return Integer.compare(yMoves, xMoves);
        }
    }
}
