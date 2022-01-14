package Bamboo.controllerTesting;

import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.heuristics.MaximiseMoves;
import Bamboo.model.*;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

public class HeuristicsTest {
    @Test
    public void maxLegalMovesTest(){
        Heuristic maxMoves = new MaximiseMoves();
        Grid grid = new GridGraphImp(4);
        Color currentColor = Color.RED;
        while(!grid.isFinished(currentColor)){
            Vector move = maxMoves.getNextMove(grid,currentColor);
            int chosenLegals = 0;
            int maxLegals = 0;
            for(Vector v : grid.getRemainingMovesList()){
                Grid copyGrid = grid.copy();
                copyGrid.setTile(v,currentColor);
                int legals = 0;
                for(Vector vv : copyGrid.getRemainingMovesList()){
                    if(copyGrid.isLegalMove(vv,currentColor)){
                        if(v == move) chosenLegals++;
                        legals ++;
                    }
                }
                if(legals > maxLegals) maxLegals = legals;
            }
            assertEquals(maxLegals,chosenLegals);
            grid.setTile(move,currentColor);
            currentColor = currentColor == Color.red ? Color.blue : Color.red;
        }
    }
}
