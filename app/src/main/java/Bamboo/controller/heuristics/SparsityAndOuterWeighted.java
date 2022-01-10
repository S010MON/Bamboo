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

public class SparsityAndOuterWeighted implements Heuristic
{
    private Grid grid;
    private Color colour;

    @Override
    public Vector getNextMove(Grid grid, Color colour) {
        this.grid = grid;
        this.colour = colour;
        Comparator<Vector> comparator = new sparsityOWComparator();
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
        return "Sparsity+OuterWeighted";
    }

    class sparsityOWComparator implements Comparator<Vector> {
        @Override
        public int compare(Vector x, Vector y) {
            Grid xgrid = grid.copy();
            xgrid.setTile(x,colour);
            Grid ygrid = grid.copy();
            ygrid.setTile(y,colour);
            int distX = 10000;
            int distY = 10000;
            for(Tile t : grid.getAllTiles()){
                if(t.getColour() == colour){
                    if(t.getVector().distance(x) < distX){
                        distX = t.getVector().distance(x);
                    }
                    if(t.getVector().distance(y) < distY){
                        distY = t.getVector().distance(y);
                    }
                }
            }
            int finalDistanceX = distX + x.distFromZero();
            int finalDistanceY = distY + y.distFromZero();

            return Integer.compare(-finalDistanceX, -finalDistanceY);
        }
    }
}
