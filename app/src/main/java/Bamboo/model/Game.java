package Bamboo.model;

import Bamboo.controller.*;
import Bamboo.view.MainFrame;

import java.util.List;

public class Game
{
    private static final boolean LOG_MOVES = false;

    private Grid grid;
    private Agent player1;
    private Agent player2;
    private Agent currentPlayer;
    private MainFrame view;
    private Settings settings;

    public Game(Settings settings, MainFrame view)
    {
        this.grid = new GridGraphImp(settings.boardSize);
        this.player1 = settings.player1;
        this.player2 = settings.player2;
        this.view = view;
        this.currentPlayer = settings.getCurrentPlayer();
        this.settings = settings;

        if(settings.tiles != null)
        {
            for(Vector v: settings.tiles.keySet())
            {
                grid.setTile(v, settings.tiles.get(v));
            }
        }
    }

    public void placeNextAt(Vector v)
    {
        if(grid.isLegalMove(v, currentPlayer.getColor()))
        {
            grid.setTile(v, currentPlayer.getColor());

            if(LOG_MOVES) 
              Logger.logMove(v, currentPlayer.getColor());
            
            toggleTurn();
        }
        if(grid.isFinished(currentPlayer.getColor()))
        {
            try {Thread.sleep(2000); } catch (Exception exception){}
            view.endGame(getNonCurrentPlayer());
        }
    }

    public int getNumberOfGroupsForPlayer(Agent player)
    {
            return grid.getAllGroupsOfColour(player.getColor()).size();
    }

    public int getSizeOfMaxOfGroups(Agent player)
    {
        return grid.getMaxGroupSize(player.getColor());
    }

    public Agent getCurrentPlayer()
    {
        return currentPlayer;
    }

    private Agent getNonCurrentPlayer()
    {
        if(currentPlayer == player1)
            return player2;
        return player1;
    }

    public boolean currentPlayerHuman()
    {
       return currentPlayer instanceof Human;
    }

    public boolean nextPlayerAI()
    {
        if(currentPlayer == player1)
            return !(player1 instanceof Human);
        else
            return !(player2 instanceof  Human);
    }

    public List<Tile> getAllTiles()
    {
        return grid.getAllTiles();
    }

    public Grid getGrid(){return grid;}

    public Settings getSettings()
    {
        return settings;
    }

    public int getBoardSize()
    {
        return settings.boardSize;
    }

    private void toggleTurn()
    {
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
        view.nextTurn();
    }
}

