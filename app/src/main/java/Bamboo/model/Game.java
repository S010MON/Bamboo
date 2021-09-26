package Bamboo.model;

import Bamboo.controller.*;
import Bamboo.view.MainFrame;

import java.awt.Color;
import java.util.List;

public class Game
{
    private int turn_count_player1 = 0;
    private int turn_count_player2 = 0;
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
            toggleTurn();
        }
        else
            System.out.println("Illegal Move");
        // TODO Add user warning.

        if(grid.isFinished(currentPlayer.getColor())) {
            System.out.println("Game ended");
            view.endGame(currentPlayer);
        }
    }

    public Game(Grid grid){
        this.grid = grid;
    }

    public Game(int size){
        this.grid = new GridGraphImp(size);
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

    public Grid getGrid(){return grid;}

    public Settings getSettings()
    {
        return settings;
    }

    public int getTurn_count(Agent player){
        if(player.getColor() == Color.RED){
            return turn_count_player1;
        }
        else
            return turn_count_player2;
    }

    private void toggleTurn()
    {
        if(currentPlayer == player1){
            turn_count_player1 ++;
            currentPlayer = player2;
        }
        else{
            turn_count_player2 ++;
            currentPlayer = player1;
         }
    }
}

