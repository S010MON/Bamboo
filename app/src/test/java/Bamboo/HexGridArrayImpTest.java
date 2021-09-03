package Bamboo;

import Bamboo.controller.Vector;
import Bamboo.model.Colour;
import Bamboo.model.HexGridArrayImp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HexGridArrayImpTest
{
    @Test void test_grid_with_radius_2()
    {
        int radius = 2;
        int diameter = (2 * radius) + 1;
        HexGridArrayImp hex = new HexGridArrayImp(radius);

        for (int x = 0; x < diameter; x++)
        {
            for (int y = 0; y < diameter; y++)
            {
                for (int z = 0; z < diameter; z++)
                {
                    if(x + y + z == 0)
                        assertEquals(Colour.EMPTY, hex.getTile(new Vector(x,y,z)).getColour());
                    else
                        assertNull(hex.getTile(new Vector(x,y,z)));
                }
            }
        }
    }

}
