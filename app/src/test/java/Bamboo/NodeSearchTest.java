package Bamboo;

import Bamboo.controller.MiniMax;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeSearchTest {
    @Test void testTreeDepthTwo(){
        Node start = new Node(new GridGraphImp(4));
        MiniMax agent = new MiniMax(Color.RED);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.addAllChildren(start,2,Color.RED);
        assertEquals(start.getChildren().get(0).getChildren().size(), new GridGraphImp(4).getAllTiles().size() - 1);
    }
}
