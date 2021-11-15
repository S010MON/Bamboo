package Bamboo.controller;

import Bamboo.model.Game;
import Bamboo.model.GameWithoutGUI;

import java.awt.Color;

public interface Agent
{
    String getName();
    String getType();
    boolean isHuman();
    Vector getNextMove(Game game);
    Vector getNextMove(GameWithoutGUI game) ;
    Color getColor();
}
