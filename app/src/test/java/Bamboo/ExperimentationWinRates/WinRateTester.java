package Bamboo.ExperimentationWinRates;

import Bamboo.controller.*;
import Bamboo.controller.MCTS.MCTS;
import Bamboo.controller.miniMax.MiniMax;
import Bamboo.controller.miniMax.MiniMaxAB;
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.model.GameWithoutGUI;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import static Bamboo.controller.AgentType.MINIMAX;
import static Bamboo.controller.AgentType.RANDOM;

public class WinRateTester {
    private AgentType player1, player2;
    private Agent agent1, agent2;
    private Color startingColor = Color.WHITE;
    private int boardSize = 5;
    private String fileName;
    private Iterator variable1;
    private Iterator variable2;
    private int replications;

    public WinRateTester(AgentType agent, int size) throws IOException {
        fileName = agent.toString() + ".csv";
        System.out.println(fileName);
        player1 = agent;
        assignIterators(agent);
        System.out.println(variable1.getReference() + " is reference on construction");
        player2 = AgentType.RANDOM;
        boardSize = size;
    }

    //set the amount of replications to do
    public void setReplications(int rep){this.replications = rep;}

    //use these if you want to change starting player
    public Color getStartingColor(){return startingColor;}
    public void setStartingColor(Color color){startingColor = color;}
    public void resetStartingColor(){startingColor = Color.WHITE;}

    public float[][] runExperiment() throws IOException {
        float[][] array = new float[variable1.getArrayBounds()][variable2.getArrayBounds()];
        System.out.println("Array Rows: " + variable1.getArrayBounds());
        System.out.println("Array cols: " + variable2.getArrayBounds());
        if(!variable1.isEmpty()){
            int rowID = 0;
            System.out.println("Reference of v1: " + variable1.getReference());
            for(float i = variable1.getStart(); i < variable1.getEnd(); i += variable1.getStep()){
                variable1.set(i);
                if(!variable2.isEmpty()){
                    int colID = 0;
                    for(float j = variable1.getStart(); i < variable2.getEnd(); i += variable2.getEnd()){
                        variable2.set(j);
                        array[rowID][colID] = getWinPercentage();
                        colID ++;
                    }
                }
                else{
                    array[rowID][0] = getWinPercentage();
                }
                rowID++;
            }
        }
        else{
            array[0][0] = getWinPercentage();
        }
        writeToCSV(array);
        return array;
    }

    //Gets winner from one game
    private Agent getWinner() throws IOException {
        agent1 = AgentFactory.makeAgent(player1,Color.RED);
        agent2 = AgentFactory.makeAgent(player2,Color.BLUE);
        Settings settings = new Settings(agent1, agent2, boardSize);
        System.out.println("Making game " + agent1.getName() + " vs. " + agent2.getName());
        GameWithoutGUI game;
        if(startingColor == Color.WHITE)
            game = new GameWithoutGUI(settings);
        else
            game = new GameWithoutGUI(settings,startingColor);
        Agent winner = game.turnLogic();
        System.out.println(winner.getName());
        return winner;
    }

    //returns player 1s win rate over <replications> games
    private float getWinPercentage() throws IOException {
        int sum = 0;
        for(int i = 0; i < replications; i++){
            if(getWinner() == agent1)
                sum++;
        }
        return sum/(float)replications;
    }

    private void assignIterators(AgentType t){
        switch (t){
            case RANDOM -> {
                variable1 = new Iterator<>("empty");
                variable2 = new Iterator<>("empty");
            }
            case MINIMAX -> {
                variable1 = new Iterator<>(MiniMax.depth,1,5,1);
                variable2 = new Iterator<>("empty");
            }
            case MINIMAX_AB -> {
                variable1 = new Iterator<>(MiniMaxAB.depth,1,5,1);
                variable2 = new Iterator<>("empty");
            }
            case MINIMAX_SORTED -> {
                variable1 = new Iterator<>(MiniMaxSortedAB.depth,1,5,1);
                variable2 = new Iterator<>("empty");
            }
            case MCTS -> {
                variable1 = new Iterator<>(MCTS.c,0.0f,1.0f,0.2f);
                variable2 = new Iterator<>(MCTS.iterations, 1000,30000,10000);
            }
        }
    }

    private void writeToCSV(float[][] data){
        for(int i = 0; i < variable1.getArrayBounds(); i++){
            String row = "";
            for(int j = 0; j < variable2.getArrayBounds(); j++){
                row += data[i][j];
            }
            System.out.println(FilePath.getNNetPath(fileName));
            Logger.logCSV(FilePath.getFilePath(fileName),row);
        }
    }

    public void setFileName(String fileName){this.fileName = fileName;}
    public String getFileName(){return this.fileName;}

    public void setVariable1(Iterator i){this.variable1 = i;}
    public void setVariable2(Iterator i){this.variable1 = i;}
}
