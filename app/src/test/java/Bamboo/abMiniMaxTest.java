package Bamboo;

import Bamboo.controller.MiniMax.MiniMax;
import Bamboo.controller.MiniMax.NodeMM;
import Bamboo.controller.MiniMax.abMiniMax;
import Bamboo.controller.Vector;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class abMiniMaxTest {
    @Test void legalChildGeneration(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        abMiniMax agent = new abMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        start.getGrid().setTile(new Vector(0,0,0),Color.BLUE);
        agent.addLegalChildren(start,Color.BLUE);
        for(NodeMM child : start.getChildren()){
            assert start.getGrid().isLegalMove(child.getMove(), Color.BLUE);
        }
        assertEquals(start.getChildren().size(),start.getGrid().getAllTiles().size() - 7);
    }

    @Test void childEvaluationEqualsMiniMaxOfChild(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        abMiniMax agent = new abMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        int evaluation = agent.minimax(start,4,-1000000,1000000,false);
        int nodeEval = start.getChildren().get(0).getValue();
        int nodeEvalMiniMax = agent.minimax(start.getChildren().get(0),3,-1000000,1000000,true);
        assertEquals(nodeEval,nodeEvalMiniMax);
    }

    @Test void miniMaxCorrectMoveReturned(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        abMiniMax agent = new abMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        Vector returnedMove = agent.minimaxMove(start,3,Color.BLUE);
        Vector bestMove = new Vector(0,0,0);
        int bestEval = 1000000;
        for(NodeMM child : start.getChildren()){
            if(child.getValue() < bestEval){
                bestEval = child.getValue();
                bestMove = child.getMove();
            }
        }
        assertEquals(bestMove,returnedMove);
    }

    @Test void minimaxCallsGrid3(){
        NodeMM start = new NodeMM(new GridGraphImp(3));
        abMiniMax agent = new abMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.minimax(start,3,-1000000,1000000,true);
        System.out.println("Calls: " + agent.getCalls());
    }

    @Test void minimaxCallsGrid4(){
        NodeMM start = new NodeMM(new GridGraphImp(4));
        abMiniMax agent = new abMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.minimax(start,3,-1000000,1000000,true);
        System.out.println("Calls: " + agent.getCalls());
    }

    @Test void minimaxCallsGrid5(){
        NodeMM start = new NodeMM(new GridGraphImp(5));
        abMiniMax agent = new abMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.minimax(start,3,-1000000,1000000,true);
        System.out.println("Calls: " + agent.getCalls());
    }
}

