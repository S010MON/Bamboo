package Bamboo.controller;

import Bamboo.model.Game;

import java.awt.Color;

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
    public String getName()
    {
        return name;
    }

    @Override
    public String getType() {
        return "HUMAN";
    }

    @Override
    public boolean isHuman()
    {
        return true;
    }

    @Override
    public Vector getNextMove(Game game) {
        return null;
    }

    @Override
    public Color getColor()
    {
        return color;
    }
}
