package Bamboo.controller;

public abstract class VectorConverter
{
    /**
     * Take a vector[x,y,z] and convert to [q,r] using:
     *  q = x + (z - |z % 2|) / 2
     *  r = z
     */
    public static AxialVector convertToAxial(CubeVector cube)
    {
        int col = cube.getX() + (cube.getZ() - (Math.abs(cube.getZ()) % 2))/2;
        int row = cube.getZ();
        return new AxialVector(col, row);
    }

    /**
     * For even rows -> return vector * 2
     * For odd rows -> return vector:   q * 2
     *                                  r * 2 + 1
     */
    public static AxialVector doubleAndOffsetOddRows(AxialVector v)
    {
        if(v.getR() % 2 == 0)
            return v.multiply(2);
        else
            return new AxialVector((v.getQ() * 2)+1, v.getR() * 2);
    }

    /**
     * For even rows -> return vector / 2
     * For odd rows -> return vector:   q / 2
     *                                  r-1 / 2
     */
    public static AxialVector halveAndAlignOddRows(AxialVector v)
    {
        if(v.getR() % 2 == 0)
            return v.divide(2);
        else
            return new AxialVector((v.getQ()-1)/2, v.getR()/2);
    }

    /**
     * Take a vector[q,r] and convert to [x,y,z]
     */
    public static CubeVector convertToCube(AxialVector axial)
    {
        int x = axial.getQ();
        int z = axial.getR();
        int y = -x -z;
        return new CubeVector(x, y, z);
    }
}
