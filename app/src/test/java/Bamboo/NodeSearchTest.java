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
        assertEquals(start.getChildren().get(0).getChildren().size(), new GridGraphImp(2).getAllTiles().size() - 1);
    }
}
