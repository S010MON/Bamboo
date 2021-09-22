package Bamboo.controller;

import java.awt.*;
import java.util.HashMap;

public class Settings
{
    public Agent player1;
    public Agent player2;
    public int boardSize;
    public HashMap<Vector, Color> tiles;
    private Agent currentPlayer = player1;

    public Settings(Agent player1, Agent player2, int boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardSize = boardSize;
    }

    public void addTiles(HashMap<Vector, Color> tiles)
    {
        this.tiles = tiles;
    }

    public void setCurrentPlayer(Agent currentPlayer)
    {
        this.currentPlayer = currentPlayer;
    }

    public Agent getCurrentPlayer()
    {
        return currentPlayer;
    }

    public static Settings getDefaultSetting()
    {
        return new Settings(
                new Human("Player 1", Color.BLUE),
                new Human("Player 2", Color.RED),
                5);
    }

    public static Settings getTestSettings()
    {
        return new Settings(
                new Human("Player 1", Color.BLUE),
                new Human("Player 2", Color.RED),
                1);
    }
}
