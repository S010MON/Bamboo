package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.awt.Color;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class Sparsity implements Heuristic{
    private Grid grid;
    private Color colour;

    @Override
    public Vector getNextMove(Grid grid, Color colour)
    {
        this.grid = grid;
        this.colour = colour;
        Comparator<Vector> comparator = new sparsityComparator();
        Queue<Vector> queue = new PriorityQueue<>(grid.getAllVectors().size(),comparator);
        ArrayList<Tile> tiles = (ArrayList<Tile>) grid.getAllTiles();
        Collections.shuffle(tiles);
        for (Tile t : tiles) {
            if (!t.isCouloured())
                queue.add(t.getVector());
        }
        Vector v;
        while (!queue.isEmpty()) {
            v = queue.remove();
            if (grid.isLegalMove(v, colour))
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
            Grid xgrid = grid.copy();
            xgrid.setTile(x,colour);
            Grid ygrid = grid.copy();
            ygrid.setTile(y,colour);
            int minxdist = 10000;
            int minydist = 10000;
            for(Tile t : grid.getAllTiles()){
                if(t.getColour() == colour){
                    if(t.getVector().distance(x) < minxdist) minxdist = t.getVector().distance(x);
                    if(t.getVector().distance(y) < minydist) minydist = t.getVector().distance(y);
                }
            }
            return Integer.compare(-minxdist,-minydist);
        }
    }
}
