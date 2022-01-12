package Bamboo;

import Bamboo.controller.miniMax.MiniMax;
import Bamboo.controller.miniMax.NodeMM;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiniMaxNodeSearchTest {
    @Test void testTreeDepthTwo(){
        NodeMM start = new NodeMM(new GridGraphImp(2));
        MiniMax agent = new MiniMax(Color.RED);
        agent.setGame(new ArrayList<>(start.getGrid().getAllVectors()));
        agent.minimax(start,2,true);
        assertEquals(start.getChildren().get(0).getChildren().size(), new GridGraphImp(2).getAllTiles().size() - 1);
    }
}
