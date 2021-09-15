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

public class checkFinishTest {
    @Test void testNoGroups(){
        Grid grid = makeMockup(3,0,0,1,1);
        assertEquals(Group.checkFinish(grid,0), false);
    }

    @Test void testNoEmptyTiles(){
        Grid grid = makeMockup(3,100,0,1,1);
        assertEquals(Group.checkFinish(grid, 0), true);
    }

    @Test void testTwoRedGroups_extendable(){
        int[] indices = {0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(3,indices);
        assertEquals(Group.checkFinish(grid, 0), false);
    }

    @Test void testTwoMaxedRedGroups(){
        int[] indices = {0,0,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(3,indices);
        assertEquals(Group.checkFinish(grid, 0), false);
    }

    @Test void testTwoMaxedRedGroups_NoLegalMove(){
        int[] indices = {0,0,2,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        Grid grid = specificMockup(3,indices);
        assertEquals(Group.checkFinish(grid, 0), true);
    }

    @Test void testTwoMaxedRedGroups_closeLegalMove(){//1st Row: RED,RED,BLUE,EMPTY
        int[] indices = {0,0,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        Grid grid = specificMockup(3,indices);
        assertEquals(Group.checkFinish(grid, 0), false);
    }

    @Test void testRealWorldScenario_bothPlayers_extendable(){
        int[] indices = {0,1,0,0,0,1,0,2,2,1,2,2,2,1,2,0,1,2,2};
        Grid grid = specificMockup(2,indices);
        assertEquals((Group.checkFinish(grid, 0) || Group.checkFinish(grid, 1)), false);
    }

    @Test void testOnlyExtensionViolatesMaxMembers(){
        int[] indices = {0,0,2,0,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1};
        Grid grid = specificMockup(2,indices);
        assertEquals(Group.checkFinish(grid, 0), true);
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
