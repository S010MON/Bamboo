package Bamboo.controller;

public class Vector3d
{
    private int x;
    private int y;
    private int z;

    public Vector3d(int x, int y, int z)
    {
        assert (x + y + z) == 0;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getZ()
    {
        return z;
    }

    @Override
    public boolean equals(Object other)
    {
        if(this.getClass() == other.getClass())
        {
            Vector3d v3d = (Vector3d) other;
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
