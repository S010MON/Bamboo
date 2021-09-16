package Bamboo;

import Bamboo.controller.CubeVector;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorTest
{
    @Test void testHashX_pos()
    {
        int exp = (int) 1E10;
        CubeVector v = new CubeVector(1,0,0);
        assertEquals(exp, v.hashCode());
    }

    @Test void testHashX_neg()
    {
        int exp = (int) 2E8;
        CubeVector v = new CubeVector(-2,0,0);
        assertEquals(exp, v.hashCode());
    }

    @Test void testHashY_pos()
    {
        int exp = (int) 3E6;
        CubeVector v = new CubeVector(0,3,0);
        assertEquals(exp, v.hashCode());
    }

    @Test void testHashY_neg()
    {
        int exp = (int) 4E4;
        CubeVector v = new CubeVector(0,-4,0);
        assertEquals(exp, v.hashCode());
    }

    @Test void testHashZ_pos()
    {
        int exp = (int) 5E2;
        CubeVector v = new CubeVector(0,0,5);
        assertEquals(exp, v.hashCode());
    }

    @Test void testHashZ_neg()
    {
        int exp = 6;
        CubeVector v = new CubeVector(0,0,-6);
        assertEquals(exp, v.hashCode());
    }
}
