package Bamboo;

import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.controller.Vector;
import Bamboo.model.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.*;

class DeepCopyTest
 {
    @Test void testArrayGrid()
    {
        Grid grid = new GridArrayImp(3);

        for(Tile tile: grid.getAllTiles())
        {
            tile.setColour(Color.BLUE);
        }

        Grid copiedGrid = grid.copy();

        for(Vector v: grid.getAllVectors())
        {
            assertEquals(grid.getTile(v).getColour(), copiedGrid.getTile(v).getColour());
        }
    }

    @Test void testGraphGrid()
    {
        Grid grid = new GridGraphImp(3);

        for(Tile tile: grid.getAllTiles())
        {
            tile.setColour(Color.BLUE);
        }

        Grid copiedGrid = grid.copy();

        for(Vector v: grid.getAllVectors())
        {
            assertEquals(grid.getTile(v).getColour(), copiedGrid.getTile(v).getColour());
        }
    }

    @Test void testDeepCopy_array()
    {
         testDeepCopy(new GridArrayImp(3));
    }

     @Test void testDeepCopy_graph()
     {
         testDeepCopy(new GridGraphImp(3));
     }

    private void testDeepCopy(Grid grid)
    {
        Grid copiedGrid = grid.copy();

        for(Tile tile: grid.getAllTiles())
        {
            tile.setColour(Color.BLUE);
        }
        for(Tile tile: copiedGrid.getAllTiles())
        {
            tile.setColour(Color.RED);
        }

        for(Vector v: grid.getAllVectors())
        {
            assertEquals(Color.BLUE, grid.getTile(v).getColour());
        }
        for(Vector v: grid.getAllVectors())
        {
            assertEquals(Color.RED, copiedGrid.getTile(v).getColour());
        }
    }
}
