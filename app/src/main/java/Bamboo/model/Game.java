package Bamboo.model;

import Bamboo.controller.*;

import java.awt.*;
import java.util.List;

public class Game
{
    private Grid grid;
    private Agent player1;
    private Agent player2;
    private Agent currentPlayer;

    public Game(Settings settings)
    {
        this.grid = new GridArrayImp(settings.boardSize);
        this.player1 = settings.player1;
        this.player2 = settings.player2;
        currentPlayer = player1;
    }

    public Agent getCurrentPlayer()
    {
        return currentPlayer;
    }

    public boolean currentPlayerHuman()
    {
       return currentPlayer instanceof Human;
    }

    public List<Tile> getAllTiles()
    {
        return grid.getAllTiles();
    }

    public void placeNextAt(CubeVector v)
    {
        grid.setTile(v, currentPlayer.getColor());
        toggleTurn();
    }

    private void toggleTurn()
    {
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }

    public void checkAlreadyCoulouredTiles (Tile tile) throws Exception {

        if(tile.isCouloured())
            throw new Exception("Tile already coloured") ;
        }
    }

