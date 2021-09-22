package Bamboo.model;

import Bamboo.controller.*;
import Bamboo.view.Canvas.*;
import Bamboo.view.RollOverListener;

import java.awt.*;
import java.util.List;

public class Game
{
    private int turn_count_player1 = 0;
    private int turn_count_player2 = 0;
    private Grid grid;
    private Agent player1;
    private Agent player2;
    private Agent currentPlayer;

    public Game(Settings settings)
    {
        this.grid = new GridHashImp(settings.boardSize);
        this.player1 = settings.player1;
        this.player2 = settings.player2;
        currentPlayer = player1;
    }

    public Game(Grid grid){
        this.grid = grid;
    }

    public Game(int size){
        this.grid = new GridArrayImp(size);
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

    public void placeNextAt(CubeVector v) throws TileAlreadyColouredException
    {
        if(GameLogic.is_legal_move(this, grid.getTile(v), currentPlayer.getColor()))
        {
            grid.setTile(v, currentPlayer.getColor());
            toggleTurn();
        }
        else
            System.out.println("Illegal Move");
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

