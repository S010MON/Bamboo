package Bamboo.controller;

public class Vector
{
    private int x, y, z;

    public Vector(int x, int y, int z) {
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

    public Vector add(Vector other)
    {
        return new Vector((x + other.getX()), (y + other.getY()), (z + other.getZ()));
    }

    public int distance(Vector other)
    {
        int dx = Math.abs(this.x - other.getX());
        int dy = Math.abs(this.y - other.getY());
        int dz = Math.abs(this.z - other.getZ());
        return Math.max(dz, Math.max(dx, dy));
    }

    public int distFromZero()
    {
        return distance(new Vector(0,0,0));
    }

    @Override
    public boolean equals(Object other)
    {
        if(this.getClass() == other.getClass())
        {
            Vector v3d = (Vector) other;
            return this.x == v3d.getX() &&
                    this.y == v3d.getY() &&
                    this.z == v3d.getZ();
        }
        else return false;
    }

    public Vector copy()
    {
        return new Vector(x, y, z);
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

    public String toCSV()
    {
        return x + "," + y + "," + z;
    }
}
