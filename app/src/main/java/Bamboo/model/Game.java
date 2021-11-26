package Bamboo.model;

import Bamboo.controller.*;
import Bamboo.view.MainFrame;

import java.awt.*;
import java.util.List;

public class Game
{
    private boolean LOG_MOVES_RED = false;
    private boolean LOG_MOVES_BLUE = false;

    private Grid grid;
    private Agent player1;
    private Agent player2;
    private Agent currentPlayer;
    private MainFrame view;
    private Settings settings;
    private boolean alreadypopup=false ;

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
            if(LOG_MOVES_RED && currentPlayer.getColor().equals(Color.RED))
            {
                int[] X = DataManager.flatten(grid, currentPlayer.getColor());
                int[] Y = DataManager.oneHotEncode(grid.getSize(), v);
                String data = DataManager.concatToCSV(X, Y);
                Logger.logCSV("data.csv", data);
            }

            if(LOG_MOVES_BLUE && currentPlayer.getColor().equals(Color.BLUE))
            {
                int[] X = DataManager.flatten(grid, currentPlayer.getColor());
                int[] Y = DataManager.oneHotEncode(grid.getSize(), v);
                String data = DataManager.concatToCSV(X, Y);
                Logger.logCSV("data.csv", data);
            }

            grid.setTile(v, currentPlayer.getColor());

            toggleTurn();
        }
        if(grid.isFinished(getCurrentPlayer().getColor())) {
            showGameOverDialog();
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

    public Agent getNonCurrentPlayer()
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

    private void showGameOverDialog(){
        try {
            Thread.sleep(1000);
        } catch (Exception ignored) {
        }
        if(!alreadypopup)
            view.gameOverOption(this);
        alreadypopup=true ;
    }

    private void toggleTurn()
    {
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
        view.nextTurn();
    }

    public boolean loggingEnabled(){
        return LOG_MOVES_RED || LOG_MOVES_BLUE;
    }

    public void setLoggingRed(boolean b){
        LOG_MOVES_RED = b;
    }

    public void setLoggingBlue(boolean b){
        LOG_MOVES_BLUE = b;
    }
}

