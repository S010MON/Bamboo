package Bamboo.minimaxTesting;

import Bamboo.controller.miniMax.NodeMM;
import Bamboo.controller.miniMax.MiniMaxAB;
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.controller.Vector;
import Bamboo.model.*;

import java.awt.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class sortedABMiniMaxTest {
    @Test void legalChildGeneration(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        MiniMaxAB agent = new MiniMaxAB(Color.BLUE);
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
        MiniMaxAB agent = new MiniMaxAB(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        int evaluation = agent.minimax(start,4,-1000000,1000000,false);
        int nodeEval = start.getChildren().get(0).getValue();
        int nodeEvalMiniMax = agent.minimax(start.getChildren().get(0),3,-1000000,1000000,true);
        assertEquals(nodeEval,nodeEvalMiniMax);
    }

    @Test void miniMaxCorrectMoveReturned(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        MiniMaxAB agent = new MiniMaxAB(Color.BLUE);
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
        MiniMaxSortedAB agent = new MiniMaxSortedAB(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.minimax(start,3,-1000000,1000000,true);
        System.out.println("Calls: " + agent.getCalls());
        assertEquals(1,1);
    }

    @Test void minimaxCallsGrid4(){
        NodeMM start = new NodeMM(new GridGraphImp(4));
        MiniMaxSortedAB agent = new MiniMaxSortedAB(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.minimax(start,3,-1000000,1000000,true);
        System.out.println("Calls: " + agent.getCalls());
        assertEquals(1,1);
    }

    /*@Test void minimaxCallsGrid5(){
        NodeMM start = new NodeMM(new GridGraphImp(5));
        sortedABMiniMax agent = new sortedABMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.minimax(start,3,-1000000,1000000,true);
        System.out.println("Calls: " + agent.getCalls());
    }*/

    @Test void sortNodes(){
        NodeMM start = new NodeMM(new GridGraphImp(5));
        MiniMaxSortedAB agent = new MiniMaxSortedAB(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.addLegalChildren(start,Color.BLUE);
        NodeMM previuosChild = start.getChildren().get(0);
        for(NodeMM child : start.getChildren()){
            assertTrue(child.getGuess() <= previuosChild.getGuess());
            previuosChild = child;
        }

    }

    @Test void sortedNodesGuesses(){
        NodeMM start = new NodeMM(new GridGraphImp(5));
        MiniMaxAB agent = new MiniMaxAB(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.addLegalChildren(start,Color.BLUE);
        long before = System.nanoTime();
        int comps = start.sortChildren();
        long after = System.nanoTime();
        float elapsed = (after-before)/(float)1000000;
        System.out.println("Elapsed ms: " + elapsed);
        for(NodeMM child : start.getChildren()){
            System.out.println(child.getGuess());
        }
        System.out.println("Comparisons: " + comps);
    }

    @Test void RadixTest(){
        NodeMM start = new NodeMM(new GridGraphImp(5));
        MiniMaxAB agent = new MiniMaxAB(Color.RED);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.addLegalChildren(start,Color.RED);
        long before = System.nanoTime();
        int comps = start.sortChildrenRadix();
        long after = System.nanoTime();
        float elapsed = (after-before)/(float)1000000;
        System.out.println("Elapsed ms: " + elapsed);
        System.out.println("Comparisons/ Actions: " + comps);
        NodeMM previuosChild = start.getChildren().get(0);
        for(NodeMM child : start.getChildren()){
            assertTrue(child.getGuess() <= previuosChild.getGuess());
            previuosChild = child;
        }
    }
}
