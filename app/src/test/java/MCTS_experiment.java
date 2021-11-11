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
        int replications = 1;
        int iterrationStep = 50;
        int num_game = 0 ;

        int[][] wins = new int[20][20];

        int boardSize = 5;
        ArrayList<Double> winRates = new ArrayList<>();

        //I think:
        // MCTS iterations <= 1000
        // iterations here >= 100
        // C in (0,1)

        for (int k = 1; k <= 20; k++ ) {                       //Loop to change C value
            //int[][] wins = new int[replications];

            UCB.C = startC+stepSize;
            stepSize=stepSize+stepSize ;
            //System.out.println(C);
            for (int j = 1; j <= 20; j++) {                  //inner loop to change num itterartion
                for (int i = 0; i < replications; i++) {    //inner-inner loop to play n games (n=replications)

                    GameWithoutGUI.MCTSiterations = j +iterrationStep;
                    iterrationStep=iterrationStep+iterrationStep;

                    Agent agent1 = AgentFactory.makeAgent(AgentType.MCTS, Color.RED);
                    Agent agent2 = AgentFactory.makeAgent(AgentType.RANDOM, Color.BLUE);
                    Settings settings = new Settings(agent1, agent2, boardSize);
                    GameWithoutGUI game = new GameWithoutGUI(settings);
                    Agent winner = game.turnLogic();//make this return winning agent in the end
                    if (winner == agent1)
                        wins[k][j] = wins[k][j] + 1;
                }
                num_game++ ;
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

    private static void mean(int[][] wins) {

        for (int i = 0; i<wins.length; i++) {
            for(int j = 0; j< wins.length; j++) {
                wins[i][j] = wins[i][j] / 100;
            }
        }
    }//return mean of array
    int[] runLoop(){return new int[]{1};}
}
