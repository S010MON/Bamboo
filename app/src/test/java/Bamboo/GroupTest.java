package Bamboo;


import Bamboo.controller.CubeVector;
import Bamboo.controller.Group;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.Tile;
import org.junit.jupiter.api.Test;
import java.util.List;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {
    @Test void testNoGroups(){
        Grid grid = makeMockup(3,0,0,1,1);
        assertEquals(Group.countGroups(Color.RED,grid), 0);
    }

    @Test void testOneGroup(){
        Grid grid = makeMockup(3,1,0,1,1);
        assertEquals(Group.countGroups(Color.RED,grid), 1);
    }

    @Test void testRedGrid_red(){
        Grid grid = makeMockup(3,100,0,1,1);
        assertEquals(Group.countGroups(Color.RED,grid), 1);
    }

    @Test void testRedGrid_blue(){
        Grid grid = makeMockup(3,100,0,1,1);
        assertEquals(Group.countGroups(Color.BLUE,grid), 0);
    }

    @Test void testTwoGroups(){
        int[] indices = {0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(2, indices);
        assertEquals(Group.countGroups(Color.RED, grid), 2);
    }

    @Test void testOpponentTwoGroups(){
        int[] indices = {0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(2, indices);
        assertEquals(Group.countGroups(Color.BLUE, grid), 0);
    }

    @Test void testRealGrid_red(){
        int[] indices = {0,0,2,2,1,1,2,2,2,2,0,0,1,2,2,2,2,0,0};
        Grid grid = specificMockup(2, indices);
        assertEquals(Group.countGroups(Color.RED, grid), 3);
    }

    @Test void testRealGrid_blue(){
        int[] indices = {0,0,2,2,1,1,2,2,2,2,0,0,1,1,2,2,2,0,0};
        Grid grid = specificMockup(2, indices);
        assertEquals(Group.countGroups(Color.BLUE, grid), 2);
    }

    public Grid makeMockup(int size, int red, int blue, int red_groups, int blue_groups){
        Grid grid = new GridArrayImp(size);
        int max_red_group_size = red_groups;
        int red_counter = 0;
        int red_group_counter = 0;
        int blue_counter = 0;
        int blue_group_counter = 1;
        List<Tile> tiles = grid.getAllTiles();
        for(int i = 0; i < tiles.size(); i++){
            CubeVector vector = tiles.get(i).getVector();

            if(red_counter < red){
                grid.setTile(vector, Color.RED);
                red_counter++;
            }
            red_group_counter ++;

        }
        return grid;
    }

    public Grid specificMockup(int size, int[] indices){
        Grid grid = new GridArrayImp(size);
        Color[] colors = {Color.RED, Color.BLUE, Color.white};
        List<Tile> tiles = grid.getAllTiles();
        for(int i = 0; i < tiles.size(); i++){
            CubeVector vector = tiles.get(i).getVector();
            grid.setTile(vector, colors[indices[i]]);
        }
        return grid;
    }
}
