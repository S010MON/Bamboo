package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.controller.Vector;

import java.util.ArrayList;

public class GameWithoutGUI extends GameImp implements Game
{
    public static int MCTSiterations = 1000;

    private ArrayList<Tile> remainingTiles;

    public GameWithoutGUI(Settings settings)
    {
        super(settings);
        remainingTiles = new ArrayList<>(grid.getAllTiles());
    }

    public Agent turnLogic(){
        while(!remainingTiles.isEmpty() && !grid.isFinished(currentPlayer.getColor())){
            makeTurn();
        }
        return otherPlayer();
    }

    private void makeTurn(){
        Vector move = currentPlayer.getNextMove(this);
        remainingTiles.remove(grid.getTile(move));
        this.grid.setTile(move,currentPlayer.getColor());
        //System.out.println("Agent " + currentPlayer.getName() + " placed color " + currentPlayer.getColor() + " at " + move.toString());
        toggleTurn();
    }

    private Agent otherPlayer(){
        if(currentPlayer == player1)
            return player2;
        else
            return player1;
    }
}