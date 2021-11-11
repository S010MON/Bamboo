import Bamboo.controller.Agent;
import Bamboo.controller.AgentFactory;
import Bamboo.controller.AgentType;
import Bamboo.controller.MCTS.UCB;
import Bamboo.controller.Settings;
import Bamboo.model.GameWithoutGUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MCTS_experiment
{
    public static void main(String[] args) {
        // I think these are the good values you just have to run it, the n*n matrix will contains average of the wins
        // Maybe check the code before to see if there is no error left :)
        double startC = 0.05;
        double stepSize = 0.05;
        double maxC = 1;
        int replications = 100;
        int iterationStep = 50;
        int num_game = 0 ;

        double[][] wins = new double[20][20];

        int boardSize = 5;
        ArrayList<Double> winRates = new ArrayList<>();

        //I think:
        // MCTS iterations <= 1000
        // iterations here >= 100
        // C in (0,1)

        //The loops are bases on the indexes of the array and not on the values of C and iteration
        // steps are increased manually in the loop

        for (int k = 1; k <= 20; k++ ) {                       //Loop to change C value
            //int[][] wins = new int[replications];

            UCB.C = startC+stepSize;
            stepSize=stepSize+stepSize ;
            //System.out.println(C);
            for (int j = 1; j <= 20; j++) {                  //inner loop to change num itterartion
                for (int i = 0; i < replications; i++) {    //inner-inner loop to play n games (n=replications)

                    GameWithoutGUI.MCTSiterations = j +iterationStep;
                    iterationStep=iterationStep+iterationStep;

                    Agent agent1 = AgentFactory.makeAgent(AgentType.MCTS, Color.RED);
                    Agent agent2 = AgentFactory.makeAgent(AgentType.RANDOM, Color.BLUE);
                    Settings settings = new Settings(agent1, agent2, boardSize);
                    GameWithoutGUI game = new GameWithoutGUI(settings);
                    Agent winner = game.turnLogic();//make this return winning agent in the end
                    if (winner == agent1)
                        wins[k-1][j-1] = wins[k-1][j-1] + 1;
                }
                num_game++ ;  //just to see at which number of game you are while running
                System.out.println(num_game);
            }

            // winRates.add(mean(wins));
        }

       // for(double p : winRates)
           // System.out.println(p);
        //todo complete setup and write to file
        mean(wins);
        System.out.println(Arrays.deepToString(wins));
    }

    private static void mean(double[][] wins)
    {
        for (int i = 0; i<wins.length; i++)
        {
            for(int j = 0; j< wins[i].length; j++)
            {
                wins[i][j] = wins[i][j] / 100;
            }
        }
    }//return mean of array
}
