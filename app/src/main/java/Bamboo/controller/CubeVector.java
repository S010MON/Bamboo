package Bamboo.controller;

public class CubeVector
{
    private int x, y, z;

    public CubeVector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public CubeVector add(CubeVector other)
    {
        return new CubeVector((x + other.getX()), (y + other.getY()), (z + other.getZ()));
    }

    @Override
    public boolean equals(Object other)
    {
        if(this.getClass() == other.getClass())
        {
            CubeVector v3d = (CubeVector) other;
            return this.x == v3d.getX() &&
                    this.y == v3d.getY() &&
                    this.z == v3d.getZ();
        }
        else return false;
    }

    public CubeVector copy()
    {
        return new CubeVector(x, y, z);
    }

    /**
     * Encodes a vector as a 12 digit number with two digits representing each axis component
     *  +x|-x|+y|-y|+z|-z
     *  01|00|00|02|00|03   =  [ 1,-2, 3]
     */
    @Override
    public int hashCode()
    {
        int x_pos = 0, x_neg = 0, y_pos = 0, y_neg = 0, z_pos = 0, z_neg = 0;
        if(x > 0)
            x_pos = Math.abs(x) * (int) 1E10;
        else
            x_neg = Math.abs(x) * (int) 1E8;

        if(y > 0)
            y_pos = Math.abs(y) * (int) 1E6;
        else
            y_neg = Math.abs(y) * (int) 1E4;

        if(z > 0)
            z_pos = Math.abs(z) * (int) 1E2;
        else
            z_neg = Math.abs(z);
        return x_pos + x_neg + y_pos + y_neg + z_pos + z_neg;
    }

    @Override
    public String toString(){
        return "[" + x + "," + y + "," + z +"]";
    }
}
