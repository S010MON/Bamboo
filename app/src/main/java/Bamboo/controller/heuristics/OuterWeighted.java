package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.awt.Color;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ArrayList;
import java.util.Collections;

public class OuterWeighted implements Heuristic
{
    @Override
    public Vector getNextMove(Grid grid, Color currentPlayer)
    {
        Queue<Vector> queue = new PriorityQueue<>();
        ArrayList<Tile> tiles = (ArrayList<Tile>) grid.getAllTiles();
        Collections.shuffle(tiles);
        for(Tile t: tiles)
        {
            if(!t.isCouloured())
                queue.add(t.getVector());
        }

        Vector v;
        while(!queue.isEmpty())
        {
            v = queue.remove();
            if(grid.isLegalMove(v, currentPlayer))
                return v;
        }
        return null;
    }

    @Override
    public String getType() {
        return "OuterWeighted";
    }
}
