package Bamboo.controller;

import Bamboo.model.Game;

import java.awt.Color;

public interface Agent
{
    String getName();
    boolean isHuman();
    CubeVector getNextMove(Game game);
    Color getColor();
}
