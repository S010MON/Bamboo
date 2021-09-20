package Bamboo.model;

import Bamboo.controller.Vector;

public class Edge
{
    public Vector v;
    public Vector u;
    public boolean test = false;

    public Edge(Vector v, Vector u)
    {
        this.v = v;
        this.u = u;
    }

    @Override
    public String toString()
    {
        return v.toString() + " -> " + u.toString();
    }
}
