package Bamboo;


import Bamboo.controller.CubeVector;
import Bamboo.controller.Group;
import Bamboo.model.Colour;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {
    @Test void testNoGroups(){
        Grid grid = makeMockup(3,0,0,1,1);
        assertEquals(Group.checkFinish(grid,0), false);
    }

    @Test void testNoEmptyTiles(){
        Grid grid = makeMockup(3,10,0,1,1);
        assertEquals(Group.checkFinish(grid, 0), true);
    }

    public Grid makeMockup(int size, int red, int blue, int red_groups, int blue_groups){
        Grid grid = new GridArrayImp(size);
        int red_counter = 0;
        int red_group_counter = 1;
        int blue_counter = 0;
        int blue_group_counter = 1;
        for(Tile tile : grid.getAllTiles()){
            CubeVector vector = tile.getVector();
            if(red_counter < red){
                grid.setTile(vector, Color.RED);
                red_counter++;
            }


        }
        return grid;
    }
}
