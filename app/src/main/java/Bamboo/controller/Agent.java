package Bamboo.controller;

import Bamboo.model.Game;

import java.awt.Color;

public interface Agent
{
    String getName();
    String getType();
    Vector getNextMove(Game game);
    Color getColor();
}
