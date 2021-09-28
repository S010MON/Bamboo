package Bamboo;


import Bamboo.controller.Vector;
import Bamboo.controller.GroupController;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {
    @Test void testNoGroups(){
        Grid grid = makeMockup(3,0,0,1,1);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.RED), 0);
    }

    @Test void testOneGroup(){
        Grid grid = makeMockup(3,1,0,1,1);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.RED), 1);    }

    @Test void testRedGrid_red(){
        Grid grid = makeMockup(3,100,0,1,1);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.RED), 1);    }

    @Test void testRedGrid_blue(){
        Grid grid = makeMockup(3,100,0,1,1);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.BLUE), 0);    }

    @Test void testTwoGroups(){
        int[] indices = {0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(2, indices);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.RED), 2);    }

    @Test void testOpponentTwoGroups(){
        int[] indices = {0,2,2,2,2,2,2,2,2,2,2,2,2,2,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(2, indices);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.BLUE), 0);    }

    @Test void testRealGrid_red(){
        int[] indices = {0,0,2,2,1,1,2,2,2,2,0,0,1,2,1,2,2,0,0};
        Grid grid = specificMockup(2, indices);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.RED), 3);    }

    @Test void testRealGrid_blue(){
        int[] indices = {0,0,2,2,1,1,2,2,2,2,0,0,1,1,2,1,2,0,0};
        Grid grid = specificMockup(2, indices);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.BLUE), 3);    }

    @Test void testDifferentGroups(){
        int[] indices = {0,2,0,2,2,2,2,0,0,0,2,0,2,2,2,2,0,0,0};
        Grid grid = specificMockup(2, indices);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.RED), 5);    }

    @Test void testWebsiteExample(){
        int[] indices = {1,1,1,0,1
                ,0,0,0,2,2,0,
                1,0,2,1,0,1,0,
                1,0,1,1,0,1,1,1,
                0,2,2,2,1,0,2,1,2,
                0,1,0,2,2,1,0,1,
                0,2,2,1,0,0,0,
                2,0,2,1,2,1,
                1,0,0,1,0};
        Grid grid = specificMockup(4,indices);
        Game game = new Game(grid);
        assertEquals(GroupController.count_groups_for_player(game, Color.BLUE), 10);    }

    public Grid makeMockup(int size, int red, int blue, int red_groups, int blue_groups){
        Grid grid = new GridArrayImp(size);
        int max_red_group_size = red_groups;
        int red_counter = 0;
        int red_group_counter = 0;
        int blue_counter = 0;
        int blue_group_counter = 1;
        List<Tile> tiles = grid.getAllTiles();
        for(int i = 0; i < tiles.size(); i++){
            Vector vector = tiles.get(i).getVector();

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
            Vector vector = tiles.get(i).getVector();
            grid.setTile(vector, colors[indices[i]]);
        }
        return grid;
    }
}