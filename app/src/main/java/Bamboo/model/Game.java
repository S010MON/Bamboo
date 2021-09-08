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
    }

    public Agent getCurrentPlayer()
    {
        return currentPlayer;
    }

    public boolean currentPlayerHuman()
    {
       //TODO (add this in once implemented) -> return currentPlayer instanceof Human;
        return true;
    }

    public List<Tile> getAllTiles()
    {
        return grid.getAllTiles();
    }

    public void placeNextAt(CubeVector v)
    {
        System.out.println("Placing tile at: " + v.toString());
        grid.setTile(v, Color.RED);
    }
}
