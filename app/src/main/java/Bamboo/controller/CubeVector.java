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

    @Override
    public String toString(){
        return "[" + x + "," + y + "," + z +"]";
    }
}
