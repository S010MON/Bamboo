package Bamboo.modelTesting;

import Bamboo.controller.Vector;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LegalMovesTest
{
    @Test void test_is_legal_array_empty()
    {
        Grid grid = new GridGraphImp(2);
        for(Tile tile: grid.getAllTiles())
        {
            assertTrue(grid.isLegalMove(tile.getVector(), Color.RED));
        }
    }

    @Test void test_is_legal_graph_empty()
    {
        Grid grid = new GridGraphImp(2);
        for(Tile tile: grid.getAllTiles())
        {
            assertTrue(grid.isLegalMove(tile.getVector(), Color.RED));
        }
    }

    @Test void test_is_legal_graph_full()
    {
        Grid grid = new GridGraphImp(2);
        for(Tile tile: grid.getAllTiles())
        {
            grid.setTile(tile.getVector(), Color.BLUE);
            assertFalse(grid.isLegalMove(tile.getVector(), Color.RED));
        }
    }

    @Test void test_is_legal_graph_adjacent_same_colour()
    {
        Grid grid = new GridGraphImp(2);
        grid.setTile(new Vector(0,0,0), Color.BLUE);
        assertFalse(grid.isLegalMove(new Vector(1,-1,0), Color.BLUE));
    }

    @Test void test_is_legal_graph_adjacent_different_colour()
    {
        Grid grid = new GridGraphImp(2);
        grid.setTile(new Vector(0,0,0), Color.BLUE);
        assertTrue(grid.isLegalMove(new Vector(1,-1,0), Color.RED));
    }

    @Test void test_is_legal_graph_group_too_big()
    {
        Grid grid = new GridGraphImp(2);
        grid.setTile(new Vector(0,0,0), Color.BLUE);
        grid.setTile(new Vector(1,-1,0), Color.BLUE);
        grid.setTile(new Vector(1,0,-1), Color.BLUE);
        assertFalse(grid.isLegalMove(new Vector(0,-1,1), Color.BLUE));
    }
}

