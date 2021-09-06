package Bamboo.controller;

import Bamboo.model.Game;

public class Human implements Agent
{
    public String name;

    public Human(String name)
    {
        this.name = name;
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
}
