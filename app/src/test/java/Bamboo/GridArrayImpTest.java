package Bamboo;

import Bamboo.controller.CubeVector;
import Bamboo.model.Colour;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;

import Bamboo.model.Tile;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridArrayImpTest
{
    @Test void testGridWithRadius2()
    {
        int radius = 2;
        int diameter = (2 * radius) + 1;
        GridArrayImp grid = new GridArrayImp(radius);

        for (int x = 0; x < diameter; x++)
        {
            for (int y = 0; y < diameter; y++)
            {
                for (int z = 0; z < diameter; z++)
                {
                    if(x + y + z == 0)
                        assertEquals(Colour.NONE, grid.getTile(new CubeVector(x,y,z)).getColour());
                    else
                        assertNull(grid.getTile(new CubeVector(x,y,z)));
                }
            }
        }
    }

    /*
     * Testing Schema - Get all neighbours
     * Centre       [0,0,0] -> [0,1,-1],[0,-1,1],[1,0,-1],[1,-1,0],[-1,0,1],[-1,1,0]
     * Off-Centre   [-1,1,0] -> [0,0,0],[0,1,-1],[-1,2,-1],[-1,0,1],[-2,1,1],[-2,2,0]
     * Edge         [-2,2,0] -> [-1,2,-1],[-1,1,0],[-2,1,1]
     */

    @Test void testGetAllNeighbours_centre()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new CubeVector( 0, 1,-1)));
        exp.add(new Tile(new CubeVector( 0,-1, 1)));
        exp.add(new Tile(new CubeVector( 1, 0,-1)));
        exp.add(new Tile(new CubeVector( 1,-1, 0)));
        exp.add(new Tile(new CubeVector(-1, 0, 1)));
        exp.add(new Tile(new CubeVector(-1, 1, 0)));

        Grid grid = new GridArrayImp(3);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new CubeVector(0,0,0));

        for(Tile tile: exp)
        {
            CubeVector v = tile.getVector();
            assertTrue(listContainsVector(act, v));
        }
    }

    @Test void testGetAllNeighbours_offCentre()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new CubeVector( 0, 0, 0)));
        exp.add(new Tile(new CubeVector( 0, 1,-1)));
        exp.add(new Tile(new CubeVector(-1, 2,-1)));
        exp.add(new Tile(new CubeVector(-1, 0, 1)));
        exp.add(new Tile(new CubeVector(-2, 1, 1)));
        exp.add(new Tile(new CubeVector(-2, 2, 0)));

        Grid grid = new GridArrayImp(3);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new CubeVector(-1,1,0));

        for(Tile tile: exp)
        {
            CubeVector v = tile.getVector();
            assertTrue(listContainsVector(act, v));
        }
    }

    @Test void testGetAllNeighbours_edge()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new CubeVector(-1, 2,-1)));
        exp.add(new Tile(new CubeVector(-1, 1, 0)));
        exp.add(new Tile(new CubeVector(-2, 1, 1)));

        Grid grid = new GridArrayImp(2);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new CubeVector(-2,2,0));

        for(Tile tile: exp)
        {
            CubeVector v = tile.getVector();
            assertTrue(listContainsVector(act, v));
        }
    }

    private boolean listContainsVector(List<Tile> list, CubeVector vector)
    {
        for(Tile t: list)
        {
            if(t.getVector().equals(vector))
                return true;
        }
        return false;
    }
}
