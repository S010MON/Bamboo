package Bamboo;

import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NodeTreeTest {
    @Test void testNodeCount(){
        Node start = new Node(new GridGraphImp(4));
        Tree tree = new Tree(start);
        start.addChild(new GridGraphImp(3));
        start.addChild(new GridGraphImp(5));
        assertEquals(tree.getNodesFromLevel(0).size(),1);
        assertEquals(tree.getNodesFromLevel(1).size(),2);
    }

    @Test void testNodeLevels(){
        Node start = new Node(new GridGraphImp(4));
        Tree tree = new Tree(start);
        start.addChild(new GridGraphImp(3));
        start.addChild(new GridGraphImp(5));
        assertEquals(tree.getNodesFromLevel(0).get(0).getLevel(),0);
        assertEquals(tree.getNodesFromLevel(1).get(0).getLevel(),1);
    }

    @Test void testTreeLevels(){
        Node start = new Node(new GridGraphImp(4));
        Tree tree = new Tree(start);
        start.addChild(new GridGraphImp(3));
        start.addChild(new GridGraphImp(5));
        assertEquals(tree.getLevels(),2);
    }

    @Test void testTreeLevelSizes(){
        Node start = new Node(new GridGraphImp(4));
        Tree tree = new Tree(start);
        start.addChild(new GridGraphImp(3));
        start.addChild(new GridGraphImp(5));
        assertEquals(tree.getNodesFromLevel(0).size(),1);
        assertEquals(tree.getNodesFromLevel(1).size(),2);
    }

    @Test void testTreeLevelOrder(){
        Node start = new Node(new GridGraphImp(4));
        Tree tree = new Tree(start);
        start.addChild(new GridGraphImp(3));
        start.addChild(new GridGraphImp(5));
        assertEquals(tree.getNodesFromLevel(0).get(0).getGrid().getAllTiles().size(),new GridGraphImp(4).getAllTiles().size());
        assertEquals(tree.getNodesFromLevel(1).get(0).getGrid().getAllTiles().size(),new GridGraphImp(3).getAllTiles().size());
        assertEquals(tree.getNodesFromLevel(1).get(1).getGrid().getAllTiles().size(),new GridGraphImp(5).getAllTiles().size());
    }

    @Test void testTreeLateAddition(){
        Node start = new Node(new GridGraphImp(4));
        Tree tree = new Tree(start);
        start.addChild(new GridGraphImp(3));
        start.addChild(new GridGraphImp(5));
        tree.getNodesFromLevel(0).get(0).addChild(new GridGraphImp(6));
        tree.getNodesFromLevel(1).get(0).addChild(new GridGraphImp(7));
        assertEquals(tree.getNodesFromLevel(0).get(0).getGrid().getAllTiles().size(),new GridGraphImp(4).getAllTiles().size());
        assertEquals(tree.getNodesFromLevel(1).get(2).getGrid().getAllTiles().size(),new GridGraphImp(6).getAllTiles().size());
        assertEquals(tree.getNodesFromLevel(1).get(0).getGrid().getAllTiles().size(),new GridGraphImp(3).getAllTiles().size());
        assertEquals(tree.getNodesFromLevel(1).get(1).getGrid().getAllTiles().size(),new GridGraphImp(5).getAllTiles().size());
        assertEquals(tree.getNodesFromLevel(2).get(0).getGrid().getAllTiles().size(),new GridGraphImp(7).getAllTiles().size());
    }

}
