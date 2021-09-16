package Bamboo;

import Bamboo.controller.CubeVector;
import Bamboo.controller.GameLogic;
import Bamboo.controller.GroupControllerImp;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.Tile;
import Bamboo.model.TileAlreadyColouredException;
import org.junit.jupiter.api.Test;
import java.util.List;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
public class LegalMovesTest {
    @Test void testAll_legal_moves(){
        Grid grid = makeMockup(2,0,0,1,0);
        assertEquals(GameLogic.getLegalMoves(grid, Color.RED).size(),grid.getAllTiles().size());
    }

    @Test void test_one_nonextendable_group(){
        Grid grid = makeMockup(2,3,0,1,0);
        assertEquals(GameLogic.getLegalMoves(grid, Color.RED).size(), grid.getAllTiles().size() - 7);
    }

    @Test void testOneTile(){
        Grid grid = makeMockup(2,1,0,1,0);
        assertEquals(GameLogic.getLegalMoves(grid, Color.RED).size(),15);
    }

    @Test void testRealWorldScenario_red_fourOptions(){
        int[] indices = {0,1,0,0,0,1,0,2,2,1,2,2,2,1,2,0,1,2,2};
        Grid grid = specificMockup(2,indices);
        for(Tile tile : GameLogic.getLegalMoves(grid, Color.RED)){
            System.out.println(tile.getVector());
        }
        assertEquals(GameLogic.getLegalMoves(grid, Color.RED).size(), 4);
    }

    @Test void testOnlyExtensionViolatesMaxMembers(){
        int[] indices = {0,0,2,0,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1};
        Grid grid = specificMockup(2,indices);
        assertEquals(GameLogic.getLegalMoves(grid, Color.RED).size(), 0);
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
        assertEquals(GroupControllerImp.empty_tiles_with_empty_neighbours(grid, Color.RED).size(), 12);
    }

    @Test void testRealGrid_red(){
        int[] indices = {0,0,2,2,1,1,2,2,2,2,0,0,1,2,1,2,2,0,0};
        Grid grid = specificMockup(2, indices);
        assertEquals(GameLogic.getLegalMoves(grid, Color.RED).size(), 8);
    }

    @Test void testRealGrid_blue(){
        int[] indices = {0,0,2,2,1,1,2,2,2,2,0,0,1,2,1,2,2,0,0};
        Grid grid = specificMockup(2, indices);
        assertEquals(GameLogic.getLegalMoves(grid, Color.BLUE).size(), 6);
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
        Color[] colors = {Color.RED, Color.BLUE, Color.WHITE};
        List<Tile> tiles = grid.getAllTiles();
        for(int i = 0; i < tiles.size(); i++){
            CubeVector vector = tiles.get(i).getVector();
            try {
                grid.setTile(vector, colors[indices[i]]);
            } catch (TileAlreadyColouredException e) {e.printStackTrace();}
        }
        return grid;
    }
}

