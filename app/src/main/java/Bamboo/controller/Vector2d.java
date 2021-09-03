package Bamboo.controller;

public class Vector2d
{
    private int q, r;

    public Vector2d(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    @Override
    public boolean equals(Object other)
    {
        if(this.getClass() == other.getClass())
        {
            Vector2d v2d = (Vector2d) other;
            return v2d.q == this.q && v2d.r == this.r;
        }
        return false;
    }

    @Override
    public String toString(){
        return "[" + q + "," + r + "]";
    }
}