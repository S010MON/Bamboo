package Bamboo;

import Bamboo.controller.CubeVector;
import Bamboo.model.Grid;
import Bamboo.model.GridGraphImp;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import Bamboo.model.Tile;
import Bamboo.model.TileAlreadyColouredException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GridGraphImpTest
{
    @Test void testGridWithRadius2()
    {
        int radius = 2;
        int diameter = (2 * radius) + 1;
        GridGraphImp grid = new GridGraphImp(radius);

        for (int x = 0; x < diameter; x++)
        {
            for (int y = 0; y < diameter; y++)
            {
                for (int z = 0; z < diameter; z++)
                {
                    if(x + y + z == 0)
                        assertEquals(Color.WHITE, grid.getTile(new CubeVector(x,y,z)).getColour());
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

        Grid grid = new GridGraphImp(3);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new CubeVector(0,0,0));

        for(Tile tile: exp)
        {
            CubeVector v = tile.getVector();
            assertTrue(this.tileListContainsVector(act, v));
        }
    }

    @Test void testGetAllNeighbours_offCentre()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new CubeVector( 0, 0, 0))); //
        exp.add(new Tile(new CubeVector( 0, 1,-1))); //
        exp.add(new Tile(new CubeVector(-1, 2,-1))); //
        exp.add(new Tile(new CubeVector(-1, 0, 1))); //
        exp.add(new Tile(new CubeVector(-2, 1, 1))); //
        exp.add(new Tile(new CubeVector(-2, 2, 0))); //

        Grid grid = new GridGraphImp(3);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new CubeVector(-1,1,0));

        for(Tile tile: exp)
        {
            CubeVector v = tile.getVector();
            assertTrue(this.tileListContainsVector(act, v));
        }
    }

    @Test void testGetAllNeighbours_edge()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new CubeVector(-1, 2,-1)));
        exp.add(new Tile(new CubeVector(-1, 1, 0)));
        exp.add(new Tile(new CubeVector(-2, 1, 1)));

        Grid grid = new GridGraphImp(2);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new CubeVector(-2,2,0));

        for(Tile tile: exp)
        {
            CubeVector v = tile.getVector();
            assertTrue(this.tileListContainsVector(act, v));
        }
    }

    private boolean tileListContainsVector(List<Tile> list, CubeVector vector)
    {
        for(Tile t: list)
        {
            if(t.getVector().equals(vector))
                return true;
        }
        return false;
    }

    private boolean vectorListContainsVector(List<CubeVector> list, CubeVector vector)
    {
        for(CubeVector v: list)
        {
            if(v.equals(vector))
                return true;
        }
        return false;
    }

    @Test void testGetGroup_single()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<CubeVector> exp = new ArrayList<>();
        exp.add(new CubeVector( 0, 1,-1));

        for(CubeVector vector: exp)
        {
            try {
                grid.setTile(vector, Color.BLUE);
            } catch (TileAlreadyColouredException e) {
                e.printStackTrace();
            }
        }

        ArrayList<CubeVector> act = grid.getGroup(exp.get(0));

        assertEquals(act.size(), exp.size());
        for(CubeVector v: exp)
        {
            assertTrue(vectorListContainsVector(act, v));
        }
    }

    @Test void testGetGroup_double_connected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<CubeVector> exp = new ArrayList<>();
        exp.add(new CubeVector( 0, 1,-1));
        exp.add(new CubeVector(-1, 1, 0));

        for(CubeVector vector: exp)
        {
            try {
                grid.setTile(vector, Color.BLUE);
            } catch (TileAlreadyColouredException e) {
                e.printStackTrace();
            }
        }

        ArrayList<CubeVector> act = grid.getGroup(exp.get(0));

        assertEquals(act.size(), exp.size());
        for(CubeVector v: exp)
        {
            assertTrue(vectorListContainsVector(act, v));
        }
    }

    @Test void testGetGroup_double_unconnected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<CubeVector> exp = new ArrayList<>();
        exp.add(new CubeVector( 0, 1,-1));
        exp.add(new CubeVector( 0,-1, 1));

        for(CubeVector vector: exp)
        {
            try {
                grid.setTile(vector, Color.BLUE);
            } catch (TileAlreadyColouredException e) {
                e.printStackTrace();
            }
        }

        for (CubeVector vector : exp)
        {
            ArrayList<CubeVector> act = grid.getGroup(vector);
            int expSize = 1;
            assertEquals(expSize, act.size());
            assertTrue(vectorListContainsVector(act, vector));
        }
    }

    @Test void testGetGroup_multiple_connected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<CubeVector> exp = new ArrayList<>();
        exp.add(new CubeVector( 0, 1,-1));
        exp.add(new CubeVector( 0,-1, 1));
        exp.add(new CubeVector( 1, 0,-1));
        exp.add(new CubeVector( 0, 0, 0));

        for(CubeVector vector: exp)
        {
            try {
                grid.setTile(vector, Color.BLUE);
            } catch (TileAlreadyColouredException e) {
                e.printStackTrace();
            }
        }

        ArrayList<CubeVector> act = grid.getGroup(exp.get(0));

        assertEquals(exp.size(), act.size());
        for(CubeVector v: exp)
        {
            assertTrue(vectorListContainsVector(act, v));
        }
    }

    @Test void testGetGroup_multiple_unconnected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<CubeVector> exp = new ArrayList<>();
        exp.add(new CubeVector( 0, 1,-1));
        exp.add(new CubeVector( 0,-1, 1));
        exp.add(new CubeVector(-2, 1, 1));

        for(CubeVector vector: exp)
        {
            try {
                grid.setTile(vector, Color.BLUE);
            } catch (TileAlreadyColouredException e) {
                e.printStackTrace();
            }
        }

        for (CubeVector vector : exp)
        {
            ArrayList<CubeVector> act = grid.getGroup(vector);
            int expSize = 1;
            assertEquals(expSize, act.size());
            assertTrue(vectorListContainsVector(act, vector));
        }
    }
}
