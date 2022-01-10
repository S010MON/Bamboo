package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.Game;
import Bamboo.model.Tile;

import java.util.Collections;
import java.util.Stack;

public class MinOpponentMoves implements Heuristic {
    @Override
    public Vector getNextMove(Game game) {
        Stack<Vector> stack = new Stack<>();
        for(Tile t: game.getAllTiles())
        {
            if(!t.isCouloured())
                stack.add(t.getVector());
        }
        Collections.shuffle(stack);

        Vector v;
        Stack<Vector> stack2 = new Stack<>();
        while(!stack.empty())
        {
            v = stack.pop();
            if(game.getGrid().isLegalMove(v, game.getCurrentPlayer().getColor()))
                stack2.add(v);
        }
        return null;
    }

    @Override
    public String getType() {
        return "MinOpponentMoves";
    }
}
