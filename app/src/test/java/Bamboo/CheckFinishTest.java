package Bamboo;

import Bamboo.controller.Vector;
import Bamboo.model.*;

import java.awt.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckFinishTest
{
    @Test void test_Game_Graph_size_1()
    {
        Grid grid = new GridGraphImp(1);
        grid.setTile(new Vector(0, 0, 0), Color.RED);
        assertFalse(grid.isFinished(Color.BLUE));
        grid.setTile(new Vector(1, -1, 0), Color.BLUE);
        assertTrue(grid.isFinished(Color.RED));
    }

    @Test void test_Game_Array_size_1()
    {
        Grid grid = new GridGraphImp(1);
        grid.setTile(new Vector(0, 0, 0), Color.RED);
        assertFalse(grid.isFinished(Color.BLUE));
        grid.setTile(new Vector(1, -1, 0), Color.BLUE);
        assertTrue(grid.isFinished(Color.RED));
    }

    @Test void test_Game_Graph_size_2()
    {
        Grid grid = new GridGraphImp(1);
        grid.setTile(new Vector(0, 0, 0), Color.RED);
        assertFalse(grid.isFinished(Color.BLUE));
        grid.setTile(new Vector(1, -1, 0), Color.BLUE);
        assertTrue(grid.isFinished(Color.RED));
    }

    @Test void test_Game_Array_size_2()
    {
        Grid grid = new GridGraphImp(1);
        grid.setTile(new Vector(0, 0, 0), Color.RED);
        assertFalse(grid.isFinished(Color.BLUE));
        grid.setTile(new Vector(1, -1, 0), Color.BLUE);
        assertTrue(grid.isFinished(Color.RED));
    }
}



