package Bamboo.ExperimentationWinRates;

import Bamboo.controller.*;
import Bamboo.controller.MCTS.MCTS;
import Bamboo.controller.miniMax.MiniMax;
import Bamboo.controller.miniMax.MiniMaxAB;
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.controller.nNet.TensorSaver;
import Bamboo.controller.random.Random;
import Bamboo.model.GameWithoutGUI;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import static Bamboo.controller.AgentType.MINIMAX;
import static Bamboo.controller.AgentType.RANDOM;

public class WinRateTester {
    private AgentType player1, player2;
    private Agent agent1, agent2;
    private Color startingColor = Color.WHITE;
    private boolean printResult = true;
    private boolean writeResult = true;
    private boolean writeProgress = true;
    public Mutable<Integer> boardSize = new Mutable<>(3);
    private int replications;
    private String fileName;
    private Iterator variable1;
    private Iterator variable2;
    private float redPercentage = 0.5f;
    private int gamesPlayed = 0;
    private String loggingFile = "log.csv";
    private Color loggedColor = Color.WHITE;
    private boolean LOG_MOVES = false;
    private int total;

    public WinRateTester(AgentType agent, int size) throws IOException {
        fileName = agent.toString() + ".csv";
        player1 = agent;
        player2 = AgentType.RANDOM;
        agent1 = AgentFactory.makeAgent(player1,Color.RED);
        System.out.println("Original: " + agent1.getDepth());
        agent2 = AgentFactory.makeAgent(player2,Color.BLUE);
        assignIterators(player1);
        System.out.println("Reference: " + agent1.getDepth());
        boardSize.set(size);
    }

    //set the amount of replications to do
    public void setReplications(int rep){this.replications = rep;}

    public Color getStartingColor(){return startingColor;}
    public void setStartingColor(Color color){startingColor = color;}
    public void resetStartingColor(){startingColor = Color.WHITE;}

    public float[][] runExperiment() throws IOException {
        float[][] array = new float[variable1.getArrayBounds()][variable2.getArrayBounds()];
        total = variable1.getArrayBounds() * variable2.getArrayBounds() * this.replications;
        if(!variable1.isEmpty()){
            int v1Progress = 0;
            int rowID = 0;
            for(float i : variable1.getValues()){
                int v2Progress = 0;
                variable1.set(i);
                if(!variable2.isEmpty()){
                    int colID = 0;
                    for(float j : variable2.getValues()){
                        variable2.set(j);
                        array[rowID][colID] = getWinPercentage();
                        colID ++;
                        if(writeProgress){
                            v2Progress ++;
                            System.out.println(100*v2Progress/ (float)variable2.getArrayBounds() + "% of v2, " + 100*v1Progress/ (float)variable1.getArrayBounds() + "% of v1.");
                        }
                    }
                    v1Progress++;
                }
                else{
                    array[rowID][0] = getWinPercentage();
                    v1Progress++;
                    if(writeProgress)
                        System.out.println(100*v1Progress/(float)variable1.getArrayBounds() + "% progress.");
                }
                rowID++;
            }
        }
        else{
            array[0][0] = getWinPercentage();
        }
        if(writeResult)
            writeToCSV(array);
        if(printResult)
            printToConsole(array);
        return array;
    }

    //Gets winner from one game
    private Agent getWinner() throws IOException {
        this.gamesPlayed ++;
        Settings settings = new Settings(agent1, agent2, ((Number)boardSize.get()).intValue());
        GameWithoutGUI game;
        if(startingColor == Color.WHITE)
            if(gamesPlayed /(float)this.replications < this.redPercentage)
                game = new GameWithoutGUI(settings, Color.RED);
            else
                game = new GameWithoutGUI(settings, Color.BLUE);
        else
            game = new GameWithoutGUI(settings,startingColor);
        setLoggingSettings(game);
        Agent winner = game.turnLogic();
        if(writeProgress)
            System.out.println("Game " + gamesPlayed + "out of " + total+ " processed, " + 100*(float)gamesPlayed/total + "%.");
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
            case MINIMAX, MINIMAX_SORTED, MINIMAX_AB -> {
                variable1 = new Iterator<>(agent1.getDepth(),1,5,1);
                variable2 = new Iterator<>("empty");
            }
            case MCTS -> {
                variable1 = new Iterator<>(agent1.getC(),0.0f,1.0f,0.2f);
                variable2 = new Iterator<>(agent1.getIterations(), 1000,30000,10000);
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
            Logger.logCSV(fileName,row);
        }
    }

    public void setFileName(String fileName){this.fileName = fileName;}
    public String getFileName(){return this.fileName;}

    private void printToConsole(float[][] result){
        for(int i = 0; i < variable1.getArrayBounds(); i++){
            for(int j = 0; j < variable2.getArrayBounds(); j++){
                System.out.print(result[i][j]+ ", ");
            }
            System.out.println();
        }
    }

    private void setLoggingSettings(GameWithoutGUI game){
        game.setLogFileName(this.loggingFile);
        game.setLogColor(this.loggedColor);
        game.setLOG_MOVES(this.LOG_MOVES);
    }

    public Agent getAgent1() {return agent1;}
    public Agent getAgent2() {return agent2;}
    public void setOpponent(AgentType opponent) throws IOException {
        this.player2 = opponent;
        this.agent2 = AgentFactory.makeAgent(player2,Color.BLUE);
    }
    public void setVariable1(Iterator i){this.variable1 = i;}
    public void setVariable2(Iterator i){this.variable2 = i;}
    public Iterator getVariable1(){return this.variable1;}
    public Iterator getVariable2(){return this.variable2;}
    public void setWriting(boolean argument){this.writeResult = argument;}
    public void setProgressPrinting(boolean argument){this.writeProgress = argument;}
    public void setPrinting(boolean argument){this.printResult = argument;}
    public void setRedStartingPercentage(float percentage){this.redPercentage = percentage;}
    public void setMoveLogging(boolean arg){this.LOG_MOVES = arg;}
    public void setLogFileName(String name){this.loggingFile = name;}
    public void setLoggedColor(Color color){this.loggedColor = color;}
}
