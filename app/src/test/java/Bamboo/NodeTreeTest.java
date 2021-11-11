package Bamboo;

import Bamboo.controller.miniMax.NodeMM;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NodeTreeTest {
    @Test void testNodePosition(){
        NodeMM start = new NodeMM(new GridGraphImp(4));
        start.addChild(new GridGraphImp(3));
        start.addChild(new GridGraphImp(5));
        assertEquals(start.getChildren().get(0).getGrid().getAllTiles().size(),new GridGraphImp(3).getAllTiles().size());
        assertEquals(start.getChildren().get(1).getGrid().getAllTiles().size(),new GridGraphImp(5).getAllTiles().size());
    }

    @Test void testNodeEmptyLevel(){
        NodeMM start = new NodeMM(new GridGraphImp(4));
        GridGraphImp g1 = new GridGraphImp(3);
        start.addChild(g1);
        assertEquals(start.getChildren().get(0).getChildren().size(),0);
    }
}
