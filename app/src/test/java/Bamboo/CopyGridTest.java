package Bamboo;

import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.controller.Vector;
import Bamboo.model.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.*;

class CopyGridTest
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

    @Test void testGraphGrid() {
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
}
