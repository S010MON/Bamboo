package Bamboo.controller;

import Bamboo.model.Game;

public interface Agent
{
    public String getName();
    public boolean isHuman();
    public Vector getNextMove(Game game);
}
