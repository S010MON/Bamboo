package Bamboo.model;

import Bamboo.controller.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWithoutGUI {
    private int size;
    public static int MCTSiterations = 1000;
    private boolean LOG_MOVES = false;
    private String logFileName = "log.csv";
    private Color logColor;
    private Grid grid;
    private ArrayList<Vector> remainingTiles = new ArrayList<>();
    Agent a1, a2, currentPlayer;

    public GameWithoutGUI(Settings settings){
        a1 = settings.player1;
        a2 = settings.player2;
        currentPlayer = settings.getCurrentPlayer();
        size = settings.boardSize;
        grid = new GridGraphImp(settings.boardSize);
        remainingTiles = new ArrayList<>(grid.getAllVectors());
    }

    public GameWithoutGUI(Settings settings, Color startingPlayer){
        a1 = settings.player1;
        a2 = settings.player2;
        if(a1.getColor() == startingPlayer)
            currentPlayer = a1;
        else
            currentPlayer = a2;
        size = settings.boardSize;
        grid = new GridGraphImp(settings.boardSize);
        remainingTiles = new ArrayList<>(grid.getAllVectors());
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
        logMove(move);
        remainingTiles.remove(move);
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

    public List<Vector> getRemainingVectors(){return this.remainingTiles;}

    public void setLogFileName(String fileName){this.logFileName = fileName;}

    public void setLOG_MOVES(boolean argument){this.LOG_MOVES = argument;}

    public void setLogColor(Color color){this.logColor = color;}

    private void logMove(Vector move){
        int[] igrid = DataManager.flatten(this.grid,currentPlayer.getColor());
        int[] imove = DataManager.oneHotEncode(this.size,move);
        String data = DataManager.concatToCSV(igrid,imove);
        if(LOG_MOVES){
            if(logColor == null || logColor == currentPlayer.getColor())
                Logger.logCSV(logFileName,data);
        }
    }
}