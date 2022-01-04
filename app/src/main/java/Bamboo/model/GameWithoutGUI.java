package Bamboo.model;

import Bamboo.controller.*;

import java.awt.*;
import java.util.ArrayList;

public class GameWithoutGUI extends GameImp implements Game
{
    private ArrayList<Tile> remainingTiles;
    private boolean LOG_MOVES = false;
    private String loggingFile = "log.csv";
    private Color loggingColor;
    private String loggingData = "";

    public GameWithoutGUI(Settings settings)
    {
        super(settings);
        remainingTiles = new ArrayList<>(grid.getAllTiles());
    }

    public GameWithoutGUI(Settings settings, Color startingPlayer){
        super(settings);
        if(player1.getColor() == startingPlayer)
            currentPlayer = player1;
        else
            currentPlayer = player2;
        remainingTiles = new ArrayList<>(grid.getAllTiles());
    }

    public Agent turnLogic(){
        while(!grid.isFinished(currentPlayer.getColor())){
            takeTurn();
        }
        Logger.logCSV(loggingFile, loggingData);
        loggingData = "";
        return otherPlayer();
    }

    private void takeTurn(){
        Vector move = currentPlayer.getNextMove(this);
        addMoveToLogString(move);
        remainingTiles.remove(move);
        this.grid.setTile(move,currentPlayer.getColor());
        //System.out.println("Agent " + currentPlayer.getName() + " placed color " + currentPlayer.getColor() + " at " + move.toString());
        toggleTurn();
    }

    private void addMoveToLogString(Vector move) {
        if(LOG_MOVES){
            if(loggingColor == null || loggingColor == Color.WHITE || loggingColor == currentPlayer.getColor()) {
                int[] igrid = DataManager.flatten(this.grid, currentPlayer.getColor());
                int[] imove = DataManager.oneHotEncode(grid.getSize(), move);
                String data = DataManager.concatToCSV(igrid, imove);
                loggingData += data;
            }
            if(!grid.isFinished(Color.RED) && !grid.isFinished(Color.BLUE))
                loggingData += "\n";
        }
    }

    private Agent otherPlayer(){
        if(currentPlayer == player1)
            return player2;
        else
            return player1;
    }

    public void setLogFileName(String loggingFile) {
        this.loggingFile = loggingFile;
    }

    public void setLogColor(Color loggedColor) {
        this.loggingColor = loggedColor;
    }

    public void setLOG_MOVES(boolean argument) {
        this.LOG_MOVES = argument;
    }
}