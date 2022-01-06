package Bamboo;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.GridGraphImp;
import Bamboo.model.Tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GridArrayImpTest
{
    @Test void test_grid_with_radius_2()
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
                        assertEquals(Color.WHITE, grid.getTile(new Vector(x,y,z)).getColour());
                    else
                        assertNull(grid.getTile(new Vector(x,y,z)));
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
    @Test void test_get_all_neighbours_centre()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new Vector( 0, 1,-1)));
        exp.add(new Tile(new Vector( 0,-1, 1)));
        exp.add(new Tile(new Vector( 1, 0,-1)));
        exp.add(new Tile(new Vector( 1,-1, 0)));
        exp.add(new Tile(new Vector(-1, 0, 1)));
        exp.add(new Tile(new Vector(-1, 1, 0)));

        Grid grid = new GridArrayImp(3);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new Vector(0,0,0));

        for(Tile tile: exp)
        {
            Vector v = tile.getVector();
            assertTrue(tileListContainsVector(act, v));
        }
    }

    @Test void test_get_all_neighbours_offCentre()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new Vector( 0, 0, 0)));
        exp.add(new Tile(new Vector( 0, 1,-1)));
        exp.add(new Tile(new Vector(-1, 2,-1)));
        exp.add(new Tile(new Vector(-1, 0, 1)));
        exp.add(new Tile(new Vector(-2, 1, 1)));
        exp.add(new Tile(new Vector(-2, 2, 0)));

        Grid grid = new GridArrayImp(3);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new Vector(-1,1,0));

        for(Tile tile: exp)
        {
            Vector v = tile.getVector();
            assertTrue(tileListContainsVector(act, v));
        }
    }

    @Test void test_get_all_neighbours_edge()
    {
        ArrayList<Tile> exp = new ArrayList<>();
        exp.add(new Tile(new Vector(-1, 2,-1)));
        exp.add(new Tile(new Vector(-1, 1, 0)));
        exp.add(new Tile(new Vector(-2, 1, 1)));

        Grid grid = new GridArrayImp(2);
        ArrayList<Tile> act = (ArrayList<Tile>) grid.getAllNeighbours(new Vector(-2,2,0));

        for(Tile tile: exp)
        {
            Vector v = tile.getVector();
            assertTrue(tileListContainsVector(act, v));
        }
    }

    private boolean tileListContainsVector(List<Tile> list, Vector vector)
    {
        for(Tile t: list)
        {
            if(t.getVector().equals(vector))
                return true;
        }
        return false;
    }

    private boolean vectorListContainsVector(List<Vector> list, Vector vector)
    {
        for(Vector v: list)
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

        ArrayList<Vector> exp = new ArrayList<>();
        exp.add(new Vector( 0, 1,-1));

        for(Vector vector: exp)
        {
            grid.setTile(vector, Color.BLUE);
        }

        ArrayList<Vector> act = grid.getGroup(exp.get(0));

        assertEquals(act.size(), exp.size());
        for(Vector v: exp)
        {
            assertTrue(vectorListContainsVector(act, v));
        }
    }

    @Test void testGetGroup_double_connected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<Vector> exp = new ArrayList<>();
        exp.add(new Vector( 0, 1,-1));
        exp.add(new Vector(-1, 1, 0));

        for(Vector vector: exp)
        {
            grid.setTile(vector, Color.BLUE);
        }

        ArrayList<Vector> act = grid.getGroup(exp.get(0));

        assertEquals(act.size(), exp.size());
        for(Vector v: exp)
        {
            assertTrue(vectorListContainsVector(act, v));
        }
    }

    @Test void testGetGroup_double_unconnected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<Vector> exp = new ArrayList<>();
        exp.add(new Vector( 0, 1,-1));
        exp.add(new Vector( 0,-1, 1));

        for(Vector vector: exp)
        {
            grid.setTile(vector, Color.BLUE);
        }

        for (Vector vector : exp)
        {
            ArrayList<Vector> act = grid.getGroup(vector);
            int expSize = 1;
            assertEquals(expSize, act.size());
            assertTrue(vectorListContainsVector(act, vector));
        }
    }

    @Test void testGetGroup_multiple_connected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<Vector> exp = new ArrayList<>();
        exp.add(new Vector( 0, 1,-1));
        exp.add(new Vector( 0,-1, 1));
        exp.add(new Vector( 1, 0,-1));
        exp.add(new Vector( 0, 0, 0));

        for(Vector vector: exp)
        {
            grid.setTile(vector, Color.BLUE);
        }

        ArrayList<Vector> act = grid.getGroup(exp.get(0));

        assertEquals(exp.size(), act.size());
        for(Vector v: exp)
        {
            assertTrue(vectorListContainsVector(act, v));
        }
    }

    @Test void testGetGroup_multiple_unconnected()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<Vector> exp = new ArrayList<>();
        exp.add(new Vector( 0, 1,-1));
        exp.add(new Vector( 0,-1, 1));
        exp.add(new Vector(-2, 1, 1));

        for(Vector vector: exp)
        {
            grid.setTile(vector, Color.BLUE);
        }

        for (Vector vector : exp)
        {
            ArrayList<Vector> act = grid.getGroup(vector);
            int expSize = 1;
            assertEquals(expSize, act.size());
            assertTrue(vectorListContainsVector(act, vector));
        }
    }

    @Test void testGetAllGroups_3_single()
    {
        int radius = 2;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<Vector> exp = new ArrayList<>();
        exp.add(new Vector( 0, 1,-1));
        exp.add(new Vector( 0,-1, 1));
        exp.add(new Vector(-2, 1, 1));

        for(Vector vector: exp)
        {
            grid.setTile(vector, Color.BLUE);
        }

        ArrayList<ArrayList<Vector>> act = grid.getAllGroupsOfColour(Color.BLUE);
        assertEquals(3, act.size());
    }

    @Test void testGetAllGroups_2_triple()
    {
        int radius = 3;
        GridGraphImp grid = new GridGraphImp(radius);

        ArrayList<Vector> exp = new ArrayList<>();
        exp.add(new Vector( 0, 2,-2));
        exp.add(new Vector( 1, 1,-2));
        exp.add(new Vector( 2, 0,-2));
        exp.add(new Vector( 0, 0, 0));
        exp.add(new Vector( 1,-1, 0));
        exp.add(new Vector(-1, 1, 0));

        ArrayList<Vector> group1 = new ArrayList<>();
        group1.add(new Vector( 0, 2,-2));
        group1.add(new Vector( 1, 1,-2));
        group1.add(new Vector( 2, 0,-2));

        ArrayList<Vector> group2 = new ArrayList<>();
        group2.add(new Vector( 0, 0, 0));
        group2.add(new Vector( 1,-1, 0));
        group2.add(new Vector(-1, 1, 0));

        for(Vector vector: exp)
        {
            grid.setTile(vector, Color.BLUE);
        }

        ArrayList<ArrayList<Vector>> act = grid.getAllGroupsOfColour(Color.BLUE);
        assertEquals(2, act.size());
        assertEquals(3, act.get(0).size());
        assertEquals(3, act.get(1).size());
    }
}
