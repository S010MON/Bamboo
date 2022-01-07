package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.util.*;

public class Sparsity implements Heuristic{
    private Game game;

    @Override
    public Vector getNextMove(Game game)
    {
        this.game = game;
        Comparator<Vector> comparator = new sparsityComparator();
        Queue<Vector> queue = new PriorityQueue<>(game.getGrid().getAllVectors().size(),comparator);
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
        return "Sparsity";
    }

    class sparsityComparator implements Comparator<Vector> {
        @Override
        public int compare(Vector x, Vector y) {
            Grid grid = game.getGrid();
            Grid xgrid = grid.copy();
            xgrid.setTile(x,game.getCurrentPlayer().getColor());
            Grid ygrid = grid.copy();
            ygrid.setTile(y,game.getCurrentPlayer().getColor());
            int minxdist = 10000;
            int minydist = 10000;
            for(Tile t : grid.getAllTiles()){
                if(t.getColour() == game.getCurrentPlayer().getColor()){
                    if(t.getVector().distance(x) < minxdist) minxdist = t.getVector().distance(x);
                    if(t.getVector().distance(y) < minydist) minydist = t.getVector().distance(y);
                }
            }
            return Integer.compare(-minxdist,-minydist);
        }
    }
}