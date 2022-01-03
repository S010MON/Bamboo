package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.Tile;

import java.util.Collections;
import java.util.Stack;

public class Uniform implements Heuristic
{

    @Override
    public Vector getNextMove(Game game)
    {
        Stack<Vector> stack = new Stack<>();
        for(Tile t: game.getAllTiles())
        {
            if(!t.isCouloured())
                stack.add(t.getVector());
        }
        Collections.shuffle(stack);

        boolean found = false;
        Vector v = null;
        while(!found)
        {
            v = stack.pop();
            if(game.getGrid().isLegalMove(v, game.getCurrentPlayer().getColor()))
                found = true;
        }
        return v;
    }
}
