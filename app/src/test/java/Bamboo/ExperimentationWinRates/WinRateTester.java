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
        float[][] array;
        if(!variable1.isEmpty()){
            int rowID = 0;
            if(!variable2.isEmpty()){

            }
            else{
                for(float i = variable1.getStart(); i < variable1.getEnd(); i += variable1.getStep()){
                    variable1.setReference();
                    array[rowID][0] =
                    rowID++;
                }
            }
        }
        else{
            float[][] ret = new float[1][1];
            ret[0][0] = getWinPercentage();
            return ret;
        }
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

}
