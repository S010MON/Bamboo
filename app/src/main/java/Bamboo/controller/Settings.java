package Bamboo.controller;

import Bamboo.model.Tile;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;

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

    public void addTiles(List<Tile> tiles)
    {
        this.tiles = new HashMap<>();
        for(Tile t: tiles)
        {
            this.tiles.put(t.getVector(), t.getColour());
        }
    }

    public Agent getCurrentPlayer()
    {
        if(currentPlayer == null)
        {
            if (Math.random() > 0.5)
                currentPlayer = player1;
            else
                currentPlayer = player2;
        }
        return currentPlayer;
    }

    public void setCurrentPlayer(Agent agent)
    {
        currentPlayer = agent;
    }

    public static Settings getDefaultSetting()
    {
        return new Settings(
                new Human("Player 1", Color.BLUE),
                new Human("Player 2", Color.RED),
                5);
    }
}
