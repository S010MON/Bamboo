package Bamboo;

import Bamboo.controller.AxialVector;
import Bamboo.controller.Vector;
import Bamboo.controller.VectorConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VectorConverterTest
{
    /* Testing Schema - Cube To Axial
     *        | 1 | 2 | 3 | 4 | 5
     *      X   0   2   2   0  -2
     *      Y   0  -2   0   1   1
     *      Z   0   0  -2  -1   1
     *      ---------------------
     *      Q   0   2   2   0  -2
     *      R   0   0  -2  -1   1
     */

    @Test void testCubeToAxial_1()
    {
        int x = 0, y = 0, z = 0;
        Vector cube = new Vector(x, y, z);
        int q = 0, r = 0;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_2()
    {
        int x = 2, y = -2, z = 0;
        Vector cube = new Vector(x, y, z);
        int q = 2, r = 0;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_3()
    {
        int x = 2, y = 0, z = -2;
        Vector cube = new Vector(x, y, z);
        int q = 1, r = -2;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_4()
    {
        int x = 0, y = 1, z = -1;
        Vector cube = new Vector(x, y, z);
        int q = -1, r = -1;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    @Test void testCubeToAxial_5()
    {
        int x = -2, y = 1, z = 1;
        Vector cube = new Vector(x, y, z);
        int q = -2, r = 1;
        AxialVector exp = new AxialVector(q, r);
        AxialVector act = VectorConverter.convertToAxial(cube);

        assertEquals(exp.getQ(), act.getQ());
        assertEquals(exp.getR(), act.getR());
    }

    /* Testing Schema - Axial To Cube
     *        | 1 | 2 | 3 | 4 | 5
     *      Q   0   2   2   0  -2
     *      R   0   0  -2  -1   1
     *      ---------------------
     *      X   0   2   2   0  -2
     *      Y   0  -2   0   1   1
     *      Z   0   0  -2  -1   1
     */
    @Test void testAxialToCube_1()
    {
        int q = 0, r = 0;
        AxialVector axial = new AxialVector(q, r);
        int x = 0, y = 0, z = 0;
        Vector exp = new Vector(x, y, z);
        Vector act = VectorConverter.convertToCube(axial);

        assertEquals(exp.getX(), act.getX());
        assertEquals(exp.getY(), act.getY());
        assertEquals(exp.getZ(), act.getZ());
    }

    @Test void testAxialToCube_2()
    {
        int q = 2, r = 0;
        AxialVector axial = new AxialVector(q, r);
        int x = 2, y = -2, z = 0;
        Vector exp = new Vector(x, y, z);
        Vector act = VectorConverter.convertToCube(axial);

        assertEquals(exp.getX(), act.getX());
        assertEquals(exp.getY(), act.getY());
        assertEquals(exp.getZ(), act.getZ());
    }

    @Test void testAxialToCube_3()
    {
        int q = 2, r = -2;
        AxialVector axial = new AxialVector(q, r);
        int x = 2, y = 0, z = -2;
        Vector exp = new Vector(x, y, z);
        Vector act = VectorConverter.convertToCube(axial);

        assertEquals(exp.getX(), act.getX());
        assertEquals(exp.getY(), act.getY());
        assertEquals(exp.getZ(), act.getZ());
    }

    @Test void testAxialToCube_4()
    {
        int q = 0, r = -1;
        AxialVector axial = new AxialVector(q, r);
        int x = 0, y = 1, z = -1;
        Vector exp = new Vector(x, y, z);
        Vector act = VectorConverter.convertToCube(axial);

        assertEquals(exp.getX(), act.getX());
        assertEquals(exp.getY(), act.getY());
        assertEquals(exp.getZ(), act.getZ());
    }

    @Test void testAxialToCube_5()
    {
        int q = -2, r = 1;
        AxialVector axial = new AxialVector(q, r);
        int x = -2, y = 1, z = 1;
        Vector exp = new Vector(x, y, z);
        Vector act = VectorConverter.convertToCube(axial);

        assertEquals(exp.getX(), act.getX());
        assertEquals(exp.getY(), act.getY());
        assertEquals(exp.getZ(), act.getZ());
    }

    /* Testing Schema - doubleAndOffsetOddRows
     * Variables: q, r
     *                  q
     *         -10  | -1  |  0  |  1  |  10
     *      10   a  |  b  |  c  |  d  |  e
     *      ----------------------------------
     *  r   1    f  |  g  |  h  |  i  |  j
     *      ----------------------------------
     *      0    k  |  l  |  m  |  n  |  o
     */
    @Test void testDoubleAndOffsetOddRows_a()
    {
        AxialVector v = new AxialVector(-10,10);
        AxialVector exp = new AxialVector(-20,20);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_b()
    {
        AxialVector v = new AxialVector(10,-1);
        AxialVector exp = new AxialVector(21,-2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_c()
    {
        AxialVector v = new AxialVector(0,10);
        AxialVector exp = new AxialVector(0,20);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_d()
    {
        AxialVector v = new AxialVector(1,10);
        AxialVector exp = new AxialVector(2,20);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_e()
    {
        AxialVector v = new AxialVector(10,10);
        AxialVector exp = new AxialVector(20,20);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_f()
    {
        AxialVector v = new AxialVector(-10,1);
        AxialVector exp = new AxialVector(-19,2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_g()
    {
        AxialVector v = new AxialVector(-1,1);
        AxialVector exp = new AxialVector(-1,2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_h()
    {
        AxialVector v = new AxialVector(0,1);
        AxialVector exp = new AxialVector(1,2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_i()
    {
        AxialVector v = new AxialVector(1,1);
        AxialVector exp = new AxialVector(3,2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_j()
    {
        AxialVector v = new AxialVector(10,1);
        AxialVector exp = new AxialVector(21,2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_k()
    {
        AxialVector v = new AxialVector(-10,0);
        AxialVector exp = new AxialVector(-20,0);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_l()
    {
        AxialVector v = new AxialVector(-1,0);
        AxialVector exp = new AxialVector(-2,0);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_m()
    {
        AxialVector v = new AxialVector(0,0);
        AxialVector exp = new AxialVector(0,0);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_n()
    {
        AxialVector v = new AxialVector(1,0);
        AxialVector exp = new AxialVector(2,0);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_o()
    {
        AxialVector v = new AxialVector(10,0);
        AxialVector exp = new AxialVector(20,0);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_p()
    {
        AxialVector v = new AxialVector(-10,-1);
        AxialVector exp = new AxialVector(-19,-2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testDoubleAndOffsetOddRows_q()
    {
        AxialVector v = new AxialVector(-1,-1);
        AxialVector exp = new AxialVector(-1,-2);
        AxialVector act = VectorConverter.doubleAndOffsetOddRows(v);
        assertEquals(exp, act);
    }

    /* Testing Schema - halveAndAlignRows
     * Variables: q, r
     *                  q
     *         -10  | -1  |  0  |  1  |  10
     *      10   a  |  b  |  c  |  d  |  e
     *      ----------------------------------
     *  r   1    f  |  g  |  h  |  i  |  j
     *      ----------------------------------
     *      0    k  |  l  |  m  |  n  |  o
     */
    @Test void testHalveAndAlignRows_a()
    {
        AxialVector v = new AxialVector(-10,20);
        AxialVector exp = new AxialVector(-5,10);
        AxialVector act = VectorConverter.halveAndAlignOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testHalveAndAlignRows_b()
    {
        AxialVector v = new AxialVector(-1,10);
        AxialVector exp = new AxialVector(0,5);
        AxialVector act = VectorConverter.halveAndAlignOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testHalveAndAlignRows_c()
    {
        AxialVector v = new AxialVector(0,10);
        AxialVector exp = new AxialVector(0,5);
        AxialVector act = VectorConverter.halveAndAlignOddRows(v);
        assertEquals(exp, act);
    }

    @Test void testHalveAndAlignRows_d()
    {
        AxialVector v = new AxialVector(2,20);
        AxialVector exp = new AxialVector(1,10);
        AxialVector act = VectorConverter.halveAndAlignOddRows(v);
        assertEquals(exp, act);
    }
}
