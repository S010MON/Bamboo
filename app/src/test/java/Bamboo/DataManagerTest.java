package Bamboo;

import Bamboo.controller.DataManager;
import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.GridGraphImp;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataManagerTest
{
    //testing getVectorList with: positive values(a), negative values (b) and 0 value(c)
    @Test void testGetVectorList_a(){
        int max = 2;
        ArrayList<Vector> V = (ArrayList<Vector>) DataManager.enumerateTiles(max);

        ArrayList<Vector> VCheck = new ArrayList<>();
        int[] X = {-2,-2,-2,-1,-1,-1,-1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2};
        int[] Y = { 0, 1, 2,-1, 0, 1, 2,-2,-1, 0, 1, 2,-2,-1, 0, 1,-2,-1, 0};
        int[] Z = { 2, 1, 0, 2, 1, 0,-1, 2, 1, 0,-1,-2, 1, 0,-1,-2, 0,-1,-2};
        for (int i = 0; i < X.length; i++) {
            VCheck.add(new Vector(X[i],Y[i], Z[i]));
        }
        assertEquals(VCheck, V);
    }

    @Test void testGetVectorList_b(){
        int max = -2;
        ArrayList<Vector> V = (ArrayList<Vector>) DataManager.enumerateTiles(max);

        ArrayList<Vector> VCheck = new ArrayList<>();
        int[] X = {-2,-2,-2,-1,-1,-1,-1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2};
        int[] Y = { 0, 1, 2,-1, 0, 1, 2,-2,-1, 0, 1, 2,-2,-1, 0, 1,-2,-1, 0};
        int[] Z = { 2, 1, 0, 2, 1, 0,-1, 2, 1, 0,-1,-2, 1, 0,-1,-2, 0,-1,-2};
        for (int i = 0; i < X.length; i++) {
            VCheck.add(new Vector(X[i],Y[i], Z[i]));
        }
        assertEquals(VCheck, V);
    }

    @Test void testGetVectorList_c(){
        int max = 0;
        ArrayList<Vector> V = (ArrayList<Vector>) DataManager.enumerateTiles(max);

        ArrayList<Vector> VCheck = new ArrayList<>();

        assertEquals(VCheck, V);
    }

    @Test void testOneHotEncode()
    {
        int grid_size = 2;
        Vector v = new Vector(0,0,0);
        int[] exp = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] act = DataManager.oneHotEncode(grid_size, v);

        for(int i = 0; i < exp.length; i++)
        {
            assertEquals(exp[i], act[i]);
        }
    }

    @Test void testFlatten_red()
    {
        int grid_size = 2;
        Grid grid = new GridGraphImp(grid_size);
        grid.setTile(new Vector(-2,0,2), Color.red);
        grid.setTile(new Vector(2,0,-2), Color.blue);
        int[] exp = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,-1};
        int[] act = DataManager.flatten(grid, Color.red);
        for(int i = 0; i < exp.length; i++)
        {
            assertEquals(exp[i], act[i]);
        }
    }

    @Test void testFlatten_blue()
    {
        int grid_size = 2;
        Grid grid = new GridGraphImp(grid_size);
        grid.setTile(new Vector(-2,0,2), Color.red);
        grid.setTile(new Vector(2,0,-2), Color.blue);
        int[] exp = {-1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
        int[] act = DataManager.flatten(grid, Color.blue);

        for(int i = 0; i < exp.length; i++)
        {
            assertEquals(exp[i], act[i]);
        }
    }

    @Test void testFlatten_empty()
    {
        int grid_size = 2;
        Grid grid = new GridGraphImp(grid_size);
        int[] exp = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] act = DataManager.flatten(grid, Color.red);

        for(int i = 0; i < exp.length; i++)
        {
            assertEquals(exp[i], act[i]);
        }
    }
}
