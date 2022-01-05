package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.awt.*;
import java.util.Collections;
import java.util.Stack;

public class Uniform implements Heuristic
{

    @Override
    public Vector getNextMove(Grid grid, Color currentPlayer)
    {
        Stack<Vector> stack = new Stack<>();
        for(Tile t: grid.getAllTiles())
        {
            if(!t.isCouloured())
                stack.add(t.getVector());
        }
        Collections.shuffle(stack);

        Vector v;
        while(!stack.empty())
        {
            v = stack.pop();
            if(grid.isLegalMove(v, currentPlayer))
                return v;
        }
        return null;
    }
}
