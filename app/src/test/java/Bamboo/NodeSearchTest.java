package Bamboo;

import Bamboo.controller.MiniMax.MiniMax;
import Bamboo.controller.MiniMax.NodeMM;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeSearchTest {
    @Test void testTreeDepthTwo(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        MiniMax agent = new MiniMax(Color.RED);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        //agent.addAllChildren(start,2,Color.RED);
        //Grid test = start.getChildren().get(0).getChildren().get(0).getChildren().get(0).getGrid();
        //System.out.println("Example evaluation: " + test.evaluateGame());
        System.out.println("MiniMax eval: " + agent.minimaxMove(start,3,Color.RED));
        assertEquals(start.getChildren().get(0).getChildren().size(), new GridGraphImp(2).getAllTiles().size() - 1);
    }
}
