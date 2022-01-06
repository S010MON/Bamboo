package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.controller.Vector;

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

        // This loads a new game from the tiles in the settings
        if(settings.tiles != null)
        {
            for(Vector v: settings.tiles.keySet())
            {
                grid.setTile(v, settings.tiles.get(v));
            }
        }
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

    @Override
    public boolean isFinished()
    {
        return grid.isFinished(player1.getColor()) || grid.isFinished(player2.getColor());
    }

    protected void toggleTurn()
    {
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }

    public Game copy()
    {
        Settings copySettings = new Settings(player1, player2, settings.boardSize);
        copySettings.setCurrentPlayer(currentPlayer);
        copySettings.addTiles(getAllTiles());
        return new GameImp(copySettings);
    }

}