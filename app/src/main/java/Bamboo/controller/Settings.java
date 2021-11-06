package Bamboo.controller;

import java.awt.Color;
import java.util.HashMap;

public class Settings
{
    public Agent player1;
    public Agent player2;
    public int boardSize;
    public HashMap<Vector, Color> tiles;
    private Agent currentPlayer;

    public Settings(Agent player1, Agent player2, int boardSize){

        this.player1 = player1;
        this.player2 = player2;
        this.boardSize = boardSize;
    }

    public void addTiles(HashMap<Vector, Color> tiles)
    {
        this.tiles = tiles;
    }

    public void setCurrentPlayer(int tossCoin)
    {
        tossCoin = (int) ( Math.random() * 2 + 1);
        if(tossCoin==1)
            this.currentPlayer = player1;
        if(tossCoin==2)
            this.currentPlayer = player2;
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
}
