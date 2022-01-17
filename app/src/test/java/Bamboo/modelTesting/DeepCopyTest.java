package Bamboo.modelTesting;

import Bamboo.model.Grid;
import Bamboo.controller.Vector;
import Bamboo.model.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

class DeepCopyTest
 {
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
