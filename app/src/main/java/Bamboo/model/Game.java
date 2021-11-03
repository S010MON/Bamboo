package Bamboo.model;

import Bamboo.controller.*;
import Bamboo.view.MainFrame;

import javax.xml.crypto.Data;
import java.util.List;

public class Game
{
    private boolean LOG_MOVES = true;

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

        if(grid.getSize() == 5)
            LOG_MOVES = true;

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
            {
                int[] X = DataManager.flatten(grid, currentPlayer.getColor());
                int[] Y = DataManager.oneHotEncode(grid.getSize(), v);
                String data = DataManager.concatToCSV(X, Y);
                Logger.logCSV("data.csv", data);
            }
            
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

    public boolean isAgentVsAgent()
    {
        return (!player1.isHuman() && !player2.isHuman());
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

