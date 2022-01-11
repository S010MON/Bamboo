package Bamboo.controller.heuristics;
import Bamboo.controller.Vector;
import Bamboo.model.*;

import java.awt.*;
import java.util.*;
import java.util.List;

class MinOpponentMoves implements Heuristic
{
    private Game game;

    @Override
    public Vector getNextMove(Game game)
    {

        ArrayList<Vector> moves = (ArrayList<Vector>) game.getGrid().getAllRemainingMoves();
        Vector bestMove = null;
        Color currentColor = game.getCurrentPlayer().getColor();
        Color opponentColor;
        if (currentColor == Color.RED)
            opponentColor = Color.BLUE;
        else
            opponentColor = Color.RED;
        int numRemainingMoves = 999;
        for (int i = 0; i < moves.size(); i++){
            Grid grid = game.getGrid().copy();
            int nOpponent = 0;
            if (grid.isLegalMove(moves.get(i), currentColor)){
                grid.setTile(moves.get(i), currentColor);
                int n = grid.getAllRemainingMoves().size();
                ArrayList<Vector> moves2 = (ArrayList<Vector>) grid.getAllRemainingMoves();
                for (int a = 0; a < n; a++){
                    if (grid.isLegalMove(moves2.get(a), opponentColor)){
                        nOpponent++;
                    }
                }
            }
            if (nOpponent < numRemainingMoves) {
                numRemainingMoves = nOpponent;
                bestMove = moves.get(i);
            }
        }
        return bestMove;
    }

    @Override
    public String getType() {
        return "MinOpponentMoves";
    }

    class Move {
        Vector v;
        int remainingMoves;

        public Move(Vector v, int remainingMoves){
            this.v = v;
            this.remainingMoves = remainingMoves;
        }
    }

}


