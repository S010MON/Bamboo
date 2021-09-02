package Bamboo.controller;

public class Vector
{
    public int x,y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector other) {
        return other.x == this.x && other.y == this.y;
    }
}