package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;

import java.util.List;

public class GameImp implements Game
{
    protected Grid grid;
    protected Agent player1;
    protected Agent player2;
    protected Agent currentPlayer;
    protected Settings settings;

    public GameImp(Settings settings)
    {
        this.grid = new GridGraphImp(settings.boardSize);
        this.player1 = settings.player1;
        this.player2 = settings.player2;
        this.currentPlayer = settings.getCurrentPlayer();
        this.settings = settings;
    }

    @Override
    public Grid getGrid()
    {
        return grid;
    }

    @Override
    public Agent getCurrentPlayer()
    {
        return currentPlayer;
    }

    @Override
    public List<Tile> getAllTiles()
    {
        return grid.getAllTiles();
    }

    protected void toggleTurn()
    {
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }
}
