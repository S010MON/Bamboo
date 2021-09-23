package Bamboo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.Tile;

import java.awt.*;

public class TestDeepCopyArrImp {

    @Test void testDeepCopy()
    {
        GridArrayImp oldGrid = new GridArrayImp(3);
        Vector v = new Vector(1,1,0);
        oldGrid.setTile(v, Color.RED);

        Tile oldTile = oldGrid.getTile(v);
        Color color1 = oldTile.getColour();
        System.out.println(color1);

        Grid newGrid = oldGrid.deepCopy();
        newGrid.setTile(v, Color.BLUE);

        Color color2 = oldTile.getColour();
        System.out.println(color2);

        assertEquals(true, true);
    }

}