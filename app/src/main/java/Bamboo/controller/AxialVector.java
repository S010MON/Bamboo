package Bamboo.controller;

public class AxialVector
{
    private int q, r;

    public AxialVector(int q, int r) {
        this.q = q;
        this.r = r;
    }

    public int getQ() {
        return q;
    }

    public int getR() {
        return r;
    }

    public AxialVector multiply(int scalar)
    {
        return new AxialVector(this.getQ() * scalar, this.getR() * scalar);
    }

    public AxialVector divide(int scalar)
    {
        return new AxialVector(this.getQ() / scalar, this.getR() / scalar);
    }

    public AxialVector add(AxialVector other)
    {
        return new AxialVector(this.getQ() + other.getQ(), this.getR() + other.getR());
    }

    @Override
    public boolean equals(Object other)
    {
        if(this.getClass() == other.getClass())
        {
            AxialVector v = (AxialVector) other;
            return this.q == v.getQ() &&
                    this.r == v.getR();
        }
        else return false;
    }

    @Override
    public String toString(){
        return "[" + q + "," + r + "]";
    }
}
