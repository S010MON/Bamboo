package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.Tile;

import java.util.*;

public class test implements Heuristic{

    @Override
    public Vector getNextMove(Game game) {
        Queue<Vector> queue = new PriorityQueue<>(new legalMovesLeft());
        ArrayList<Tile> tiles = (ArrayList<Tile>) game.getAllTiles();
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
            if(game.getGrid().isLegalMove(v, game.getCurrentPlayer().getColor()))
                return v;
        }
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    public static class legalMovesLeft implements Comparator<Vector>{
        @Override
        public int compare(Vector x, Vector y){
            //x better than y
            return 1;
        }
    }
}
