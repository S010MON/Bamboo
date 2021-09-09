package Bamboo;

import Bamboo.controller.CubeVector;
import Bamboo.model.Colour;
import Bamboo.model.GridArrayImp;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GridArrayImpTest
{
    @Test void test_grid_with_radius_2()
    {
        int radius = 2;
        int diameter = (2 * radius) + 1;
        GridArrayImp hex = new GridArrayImp(radius);

        for (int x = 0; x < diameter; x++)
        {
            for (int y = 0; y < diameter; y++)
            {
                for (int z = 0; z < diameter; z++)
                {
                    if(x + y + z == 0)
                        assertEquals(Color.WHITE, hex.getTile(new CubeVector(x,y,z)).getColor());
                    else
                        assertNull(hex.getTile(new CubeVector(x,y,z)));
                }
            }
        }
    }

}
