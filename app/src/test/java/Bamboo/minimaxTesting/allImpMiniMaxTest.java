package Bamboo.minimaxTesting;

import Bamboo.controller.miniMax.MiniMax;
import Bamboo.controller.miniMax.NodeMM;
import Bamboo.controller.miniMax.MiniMaxAB;
import Bamboo.controller.Vector;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class allImpMiniMaxTest {
    @Test void regularEqualsPruning(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        MiniMax agent = new MiniMax(Color.BLUE);
        MiniMaxAB agent2 = new MiniMaxAB(Color.BLUE);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent2.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        Vector move1 = agent.minimaxMove(start,3,Color.BLUE);
        Vector move2 = agent2.minimaxMove(start,3,Color.BLUE);
        assertEquals(move1,move2);
    }
}
