import Bamboo.controller.*;
import Bamboo.controller.MCTS.UCB;
import Bamboo.model.GameWithoutGUI;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MCTS_experiment
{
    public static void main(String[] args) throws IOException {
        // I think these are the good values you just have to run it, the n*n matrix will contains average of the wins
        // Maybe check the code before to see if there is no error left :)
        double startC = 0.2;
        double stepSize = 0.05;
        double maxC = 0.8;
        int replications = 1;
        int startIterations = 1;
        int iterationStep = 250;
        int maxIterations = 1001;
        int num_game = 0 ;


        int rows = 1 + (int)Math.round((maxC-startC)/stepSize), cols = (int) (1 + Math.ceil((double)(maxIterations - startIterations)/iterationStep));
        double[][] wins = new double[rows][cols];

        int boardSize = 5;
        int total = rows*cols*replications;

        //I think:
        // MCTS iterations <= 1000
        // iterations here >= 100
        // C in (0,1)

        //The loops are bases on the indexes of the array and not on the values of C and iteration
        // steps are increased manually in the loop
        int index_c = 0;
        int index_i = 0;

        for (double C = startC; C <= maxC; C+=stepSize) {                       //Loop to change C value
            //int[][] wins = new int[replications];

            UCB.C = C;
            //System.out.println(C);
            for (int iter = startIterations; iter <= maxIterations; iter += iterationStep) {//inner loop to change num itterartion
                int[] records = new int[replications];
                for (int i = 0; i < replications; i++) {    //inner-inner loop to play n games (n=replications)

                    GameWithoutGUI.MCTSiterations = iter;

                    Agent agent1 = AgentFactory.makeAgent(AgentType.MCTS, Color.RED);
                    Agent agent2 = AgentFactory.makeAgent(AgentType.RANDOM, Color.BLUE);
                    Settings settings = new Settings(agent1, agent2, boardSize);
                    GameWithoutGUI game = new GameWithoutGUI(settings);
                    Agent winner = game.turnLogic();//make this return winning agent in the end;  ---  I think this is weirdly named?
                    if (winner == agent1)
                        records[i] = 1;
                    else
                        records[i] = 0;
                    num_game++ ;  //just to see at which number of game you are while running
                    System.out.println("Game " + num_game + "out of " + total+ " processed, " + 100*(float)num_game/total + "%.");
                }
                //System.out.println("C:"+C+", I:"+iter+", array["+index_c+"]["+index_i+"] = " + mean(records));
                wins[index_c][index_i] = mean(records);
                index_i++;
            }
            index_c ++;
            index_i = 0;

            // winRates.add(mean(wins));
        }

        String writeString = "";

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                writeString += wins[i][j] + ",";
            }
             writeString += "\n";
        }
        Logger.logCSV("MCTS_data",writeString);

        System.out.println(Arrays.deepToString(wins));
    }

    private static double mean(int[] x){
        int sum = 0;
        for(int i : x){
            sum += i;
        }
        return sum/(double)x.length;
    }
}
