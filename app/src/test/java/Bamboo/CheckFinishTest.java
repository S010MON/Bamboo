package Bamboo;

import Bamboo.controller.Vector;
import Bamboo.controller.GameLogic;
import Bamboo.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class CheckFinishTest {

    @Test void testNoGroups(){
        Grid grid = makeMockup(3,0,0,1,1);
        Game game = new Game(grid);
        assertEquals(GameLogic.checkFinish(game,0), false);
    }

    @Test void testNoEmptyTiles(){
        Grid grid = makeMockup(3,100,0,1,1);
        Game game = new Game(grid);
        assertEquals(GameLogic.checkFinish(game, 0), true);
    }

    @Test void testTwoRedGroups_extendable(){
        int[] indices = {0,2,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(3,indices);
        Game game = new Game(grid);
        assertEquals(GameLogic.checkFinish(game, 0), false);
    }

    @Test void testTwoMaxedRedGroups(){
        int[] indices = {0,0,2,2,2,2,0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
        Grid grid = specificMockup(3,indices);
        Game game = new Game(grid);
        assertEquals(GameLogic.checkFinish(game, 0), false);
    }

    @Test void testTwoMaxedRedGroups_NoLegalMove(){
        int[] indices = {0,0,2,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        Grid grid = specificMockup(3,indices);
        Game game = new Game(grid);
        assertEquals(GameLogic.checkFinish(game, 0), true);
    }

    @Test void testTwoMaxedRedGroups_closeLegalMove(){//1st Row: RED,RED,BLUE,EMPTY
        int[] indices = {0,0,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        Grid grid = specificMockup(3,indices);
        Game game = new Game(grid);
        assertEquals(GameLogic.checkFinish(game, 0), false);
    }

    @Test void testRealWorldScenario_bothPlayers_extendable(){
        int[] indices = {0,1,0,0,0,1,0,2,2,1,2,2,2,1,2,0,1,2,2};
        Grid grid = specificMockup(2,indices);
        Game game = new Game(grid);
        assertEquals((GameLogic.checkFinish(game, 0) || GameLogic.checkFinish(game, 1)), false);
    }

    @Test void testOnlyExtensionViolatesMaxMembers(){
        int[] indices = {0,0,2,0,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1};
        Grid grid = specificMockup(2,indices);
        Game game = new Game(grid);
        assertEquals(GameLogic.checkFinish(game, 0), true);
    }

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
        assertEquals(GameLogic.checkFinish(game, 1), false);
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
            Vector vector = tiles.get(i).getVector();

            if(red_counter < red){
                try {
                    grid.setTile(vector, Color.RED);
                } catch (TileAlreadyColouredException e) {e.printStackTrace();}
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
            try {
                grid.setTile(vector, colors[indices[i]]);;
            } catch (TileAlreadyColouredException e) {e.printStackTrace();}
        }
        return grid;
    }
}
