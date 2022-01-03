package Bamboo.controller;

import Bamboo.model.GameWithGUI;
import Bamboo.model.GameWithoutGUI;

import java.awt.Color;

public interface Agent
{
    String getName();
    String getType();
    boolean isHuman();
    Vector getNextMove(GameWithGUI game);
    Vector getNextMove(GameWithoutGUI game) ;
    Color getColor();
}
