package Bamboo;

import Bamboo.controller.AxialVector;
import Bamboo.controller.CubeVector;
import Bamboo.controller.VectorConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestVectorConverter
{
    /* Testing Schema
     *        | 1 | 2 | 3 | 4 | 5
     *      X   0   2   2   0  -2
     *      Y   0  -2   0   1   1
     *      Z   0   0  -2  -1   1
     *      ---------------------
     *      Q   0   2   1  -1  -2
     *      R   0   0  -2  -1   1
     */

    @Test void testCubeToAxial_1()
    {
        int x = 0, y = 0, z = 0;
        CubeVector cube = new CubeVector(x, y, z);
        int q = 0, r = 0;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_2()
    {
        int x = 2, y = -2, z = 0;
        CubeVector cube = new CubeVector(x, y, z);
        int q = 2, r = 0;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_3()
    {
        int x = 2, y = 0, z = -2;
        CubeVector cube = new CubeVector(x, y, z);
        int q = 1, r = -2;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_4()
    {
        int x = 0, y = 1, z = -1;
        CubeVector cube = new CubeVector(x, y, z);
        int q = -1, r = -1;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_5()
    {
        int x = -2, y = 1, z = 1;
        CubeVector cube = new CubeVector(x, y, z);
        int q = -2, r = 1;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

}
