package Bamboo;


import Bamboo.model.Colour;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.Tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GroupTest {
    @Test void testNoGroups(){
        Grid
    }

    public Grid makeMockup(int size, int red, int blue){
        Grid grid = new GridArrayImp(size);
        int red_counter = 0;
        int blue_counter = 0;
        for(Tile tile : grid.getAllTiles()){
            if(red_counter < red){
                tile.setColour(Colour.RED);
            }
            else{

            }
        }

    }
}
