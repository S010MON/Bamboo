package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Human;
import Bamboo.controller.Settings;
import Bamboo.controller.CubeVector;

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

