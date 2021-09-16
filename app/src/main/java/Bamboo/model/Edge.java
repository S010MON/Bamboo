package Bamboo.model;

import Bamboo.controller.CubeVector;

public class Edge
{
    public CubeVector v;
    public CubeVector u;

    public Edge(CubeVector v, CubeVector u)
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
