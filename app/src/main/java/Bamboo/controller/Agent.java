package Bamboo.controller;

import Bamboo.model.Game;

import java.awt.Color;

public interface Agent
{
    String getName();
    String getType();
    boolean isHuman();
    Vector getNextMove(Game game);
    Color getColor();
}
