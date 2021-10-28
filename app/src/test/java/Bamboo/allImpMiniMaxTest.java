package Bamboo;

import Bamboo.controller.MiniMax.MiniMax;
import Bamboo.controller.MiniMax.NodeMM;
import Bamboo.controller.MiniMax.abMiniMax;
import Bamboo.controller.MiniMax.sortedABMiniMax;
import Bamboo.controller.Vector;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class allImpMiniMaxTest {
    @Test void regularAndPruning(){
        NodeMM start = new NodeMM(new GridGraphImp(3));
        MiniMax agent = new MiniMax(Color.BLUE);
        abMiniMax agent2 = new abMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent2.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        Vector move1 = agent.minimaxMove(start,2,Color.BLUE);
        Vector move2 = agent2.minimaxMove(start,2,Color.BLUE);
        assertEquals(move1,move2);
    }

    @Test void regularAndSorted(){
        NodeMM start = new NodeMM(new GridGraphImp(3));
        MiniMax agent = new MiniMax(Color.BLUE);
        sortedABMiniMax agent2 = new sortedABMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent2.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        Vector move1 = agent.minimaxMove(start,2,Color.BLUE);
        Vector move2 = agent2.minimaxMove(start,2,Color.BLUE);
        assertEquals(move1,move2);
    }

    @Test void pruningAndSorted(){
        NodeMM start = new NodeMM(new GridGraphImp(3));
        abMiniMax agent = new abMiniMax(Color.BLUE);
        sortedABMiniMax agent2 = new sortedABMiniMax(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent2.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        Vector move1 = agent.minimaxMove(start,2,Color.BLUE);
        Vector move2 = agent2.minimaxMove(start,2, Color.BLUE);
        assertEquals(move1,move2);
    }
}
