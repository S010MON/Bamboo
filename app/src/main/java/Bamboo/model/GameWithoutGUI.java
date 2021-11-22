package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.controller.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWithoutGUI {
    public static int MCTSiterations = 1000;

    private Grid grid;
    private ArrayList<Tile> remainingTiles = new ArrayList<>();
    Agent a1, a2, currentPlayer;

    public GameWithoutGUI(Settings settings){
        a1 = settings.player1;
        a2 = settings.player2;
        currentPlayer = settings.getCurrentPlayer();
        grid = new GridGraphImp(settings.boardSize);
        remainingTiles = new ArrayList<>(grid.getAllTiles());
    }

    public GameWithoutGUI(Settings settings, Color startingPlayer){
        a1 = settings.player1;
        a2 = settings.player2;
        if(a1.getColor() == startingPlayer)
            currentPlayer = a1;
        else
            currentPlayer = a2;
        grid = new GridGraphImp(settings.boardSize);
        remainingTiles = new ArrayList<>(grid.getAllTiles());
    }

    private void toggleTurn()
    {
        if(currentPlayer == a1){
            currentPlayer = a2;
        }
        else{
            currentPlayer = a1;
        }
    }

    public Agent turnLogic(){
        while(!grid.isFinished(currentPlayer.getColor())){
            makeTurn();
        }
        return otherPlayer();
    }

    private void makeTurn(){
        Vector move = currentPlayer.getNextMove(this);
        //remainingTiles.remove(grid.getTile(move));
        this.grid.setTile(move,currentPlayer.getColor());
        //System.out.println("Agent " + currentPlayer.getName() + " placed color " + currentPlayer.getColor() + " at " + move.toString());
        toggleTurn();
    }

    public Grid getGrid() {
        return this.grid;
    }

    public Agent getCurrentPlayer() {
        return currentPlayer;
    }

    private Agent otherPlayer(){
        if(currentPlayer == a1)
            return a2;
        else
            return a1;
    }

    public List<Tile> getAllTiles() {
        return grid.getAllTiles();
    }
}