package Bamboo.controller;

public abstract class VectorConverter
{
    public static AxialVector convertToAxial(CubeVector cube)
    {
        int rowOffset;
        if((Math.abs(cube.getZ()) % 2) == 0)
            rowOffset = 0;
        else
            rowOffset = 1;

        int col = cube.getX() + (cube.getZ() - rowOffset)/2;
        int row = cube.getZ();
        return new AxialVector(col, row);
    }

    public static CubeVector convertToCube(AxialVector axial)
    {
        int x = axial.getQ();
        int z = axial.getR();
        int y = -x -z;
        return new CubeVector(x, y, z);
    }
}
