package Bamboo.ExperimentationWinRates;

import Bamboo.controller.Agent;
import Bamboo.controller.AgentFactory;
import Bamboo.controller.AgentType;
import Bamboo.controller.MCTS.MCTS;
import Bamboo.controller.Settings;
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
    private Agent player1, player2;
    private Color startingColor = Color.WHITE;
    private int boardSize = 5;
    private Iterator variable1;
    private Iterator variable2;
    private int replications;

    public WinRateTester(AgentType agent, int size) throws IOException {
        player1 = AgentFactory.makeAgent(agent,Color.RED);
        assignIterators(agent);
        System.out.println(variable1.getReference() + " is reference on construction");
        player2 = AgentFactory.makeAgent(RANDOM, Color.BLUE);
        boardSize = size;
    }

    //set the amount of replications to do
    public void setReplications(int rep){this.replications = rep;}

    //use these if you want to change starting player
    public Color getStartingColor(){return startingColor;}
    public void setStartingColor(Color color){startingColor = color;}
    public void resetStartingColor(){startingColor = Color.WHITE;}

    public float[][] runExperiment(){
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
            float[][] ret = new float[1][1];
            ret[0][0] = getWinPercentage();
            return ret;
        }
        return array;
    }

    //Gets winner from one game
    private Agent getWinner(){
        Settings settings = new Settings(player1, player2, boardSize);
        GameWithoutGUI game;
        if(startingColor == Color.WHITE)
            game = new GameWithoutGUI(settings);
        else
            game = new GameWithoutGUI(settings,startingColor);
        return game.turnLogic();
    }

    //returns player 1s win rate over <replications> games
    private float getWinPercentage(){
        int sum = 0;
        for(int i = 0; i < replications; i++){
            if(getWinner() == player1)
                sum++;
        }
        return sum/(float)replications;
    }

    private void assignIterators(AgentType t){
        switch (t){
            case RANDOM -> {
                variable1 = new Iterator("empty");
                variable2 = new Iterator("empty");
            }
            case MINIMAX -> {
                variable1 = new Iterator(MiniMax.depth,1,5,1);
                variable2 = new Iterator("empty");
            }
            case MINIMAX_AB -> {
                variable1 = new Iterator(MiniMaxAB.depth,1,5,1);
                variable2 = new Iterator("empty");
            }
            case MINIMAX_SORTED -> {
                variable1 = new Iterator(MiniMaxSortedAB.depth,1,5,1);
                variable2 = new Iterator("empty");
            }
            case MCTS -> {
                variable1 = new Iterator(MCTS.c,0.0f,1.0f,0.2f);
                variable2 = new Iterator(MCTS.iterations, 1000,30000,10000);
            }
        }
    }

    public void setVariable1(Iterator i){this.variable1 = i;}
    public void setVariable2(Iterator i){this.variable1 = i;}
}
