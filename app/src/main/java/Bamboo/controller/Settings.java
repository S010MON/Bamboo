package Bamboo.controller;

import java.awt.*;

public class Settings
{
    public Agent player1;
    public Agent player2;
    public int boardSize;

    public Settings(Agent player1, Agent player2, int boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardSize = boardSize;
    }

    public static Settings getDefaultSetting()
    {
        return new Settings(
                new Human("Player 1", Color.BLUE),
                new Human("Player 2", Color.RED),
                5);
    }

    public static Settings getTestSetting()
    {
        return new Settings(
                new Human("Player 1", Color.BLUE),
                new Human("Player 2", Color.RED),
                2);
    }
}
