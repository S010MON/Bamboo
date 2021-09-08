package Bamboo.controller;

import Bamboo.model.Game;

import java.awt.*;

public class Human implements Agent
{
    private String name;
    private Color color;

    public Human(String name, Color color)
    {
        this.name = name;
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isHuman() {
        return true;
    }

    @Override
    public CubeVector getNextMove(Game game) {
        return null;
    }

    @Override
    public Color getColor()
    {
        return color;
    }
}
