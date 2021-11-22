package Bamboo;

import Bamboo.ExperimentationWinRates.Iterator;
import Bamboo.ExperimentationWinRates.WinRateTester;
import Bamboo.controller.*;
import Bamboo.controller.MCTS.MCTS;
import Bamboo.*;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Bamboo.controller.miniMax.MiniMax;
import Bamboo.controller.miniMax.MiniMaxAB;
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.model.GameWithoutGUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IteratorTest {
    Mutable<Integer> mutable = new Mutable<>(0);
    @Test void testMutable(){
        MiniMaxSortedAB agent = new MiniMaxSortedAB(Color.RED);
        agent.getDepth().set(3);
        Iterator<Integer> iter = new Iterator(agent.getDepth(), 0,5,1);
        for(float i : iter.getValues()){
            iter.set((int)i);
            System.out.println(agent.getDepth().get());
        }
    }

    @Test void tutorialTester() throws IOException {
        //instantiate Tester which runs experiments for you and returns a matrix with win rates by variable values
        //Requires agent type and grid size
        AgentType type = AgentType.MCTS;
        int boardSize = 2;
        WinRateTester tester = new WinRateTester(type, boardSize);
        //Set the number of games you want the agent to play against the other agent (random?) (for each unique combination of variable values)
        tester.setReplications(15);
        //If you want to let the agent play against something else than random, pass an agent type to
        tester.setOpponent(AgentType.RANDOM);//Random is default, so if you dont want to change it, you dont need to call this method
        //----------This stuff you only have to set if you want to set custom iteration bounds.
        //The correct references are already set. If you want to remove a variable, do:
        tester.setVariable1(new Iterator<>("empty"));
        //On the variable you want to remove.
        //----------If you want to set your own iteration bounds, use the following:
        //Specify references to the variables you want to iterate over. These must be of the Mutable class
        Mutable<Float> reference_to_variable_1 = tester.getAgent1().getC();
        Mutable<Integer> reference_to_variable_2 = tester.getAgent1().getIterations();
        //For Minimax: Mutable<Float> ... = agent.depth; Replace "agent" with the actual name of the instance youre using
        //Instantiate an iterator over those references. Pass it the reference itself, as well as min, max and step size
        Iterator<Float> iterator_for_variable_1 = new Iterator<>(reference_to_variable_1,0,1.01f,0.5f);
        Iterator<Integer> iterator_for_variable_2 = new Iterator<>(reference_to_variable_2,1,1001,450);
        //Set the (up to) two variables the tester should iterate over (pass it the iterator you just created)
        //Available are:
        //Random: None
        //Minimax(and versions of it): .getDepth
        //MCTS: .getC, .getIterations
        tester.setVariable1(iterator_for_variable_1);
        tester.setVariable2(iterator_for_variable_2);
        //----------You can also set an iterator from a float array (Must be float array, even if you iterate over integers).
        //For MCTS iterations:
        Mutable<Integer> reference = tester.getAgent1().getIterations();
        float[] values = new float[]{1,1000};
        Iterator<Integer> iterator_from_array = new Iterator<>(reference,values);
        //Then set that iterator for the tester
        tester.setVariable1(iterator_from_array);
        //----------You can also iterate over grid sizes, if you have an iterator to spare
        Mutable<Integer> reference_to_grid_size = tester.boardSize;
        Iterator<Integer> iterator_over_grid_size = new Iterator<>(reference_to_grid_size,1,6,1);
        tester.setVariable2(iterator_over_grid_size);
        //----------If you want to set an agent to always start or never start, do:
        tester.setStartingColor(Color.RED);//if the agent you chose should start
        tester.setStartingColor(Color.BLUE);//if the other (Random) one should start
        tester.resetStartingColor();//If you want to reset to coin toss
        //----------Set file name if you want to, else it will be named after the agent type you chose
        tester.setFileName("mytest.csv");
        //set printing flag if you dont want the result also output to console
        tester.setPrinting(false);
        //set writing flag if you dont want the results written to file
        //If youre just testing something and dont want to append to your file maybe?
        tester.setWriting(false);
        //run the experiment
        float[][] result = tester.runExperiment();//returns a float array, should you want to call this multiple times in a loop or something?
    }

    @Test void minimalExample() throws IOException {
        //Lets MCTS play random twice for each combination of C and iterations
        WinRateTester tester = new WinRateTester(AgentType.MCTS,3);
        tester.setVariable1(new Iterator<>(tester.getAgent1().getIterations(),1,1000,250));
        tester.setVariable2(new Iterator<>(tester.getAgent1().getC(), 0, 1.2f, 0.2f));
        tester.setReplications(2);
        tester.setProgressPrinting(true);
        tester.setFileName("MCTS_experiment_C_iterations.csv");
        tester.runExperiment();
    }

    @Test void miniMaxComparison() throws IOException{
        //Compares minimax win percentages against random over search depth and grid size
        WinRateTester tester = new WinRateTester(AgentType.MINIMAX_SORTED,2);
        tester.setOpponent(AgentType.RANDOM);
        tester.setVariable1(new Iterator<>(tester.getAgent1().getDepth(), 1,4,1));
        tester.setVariable2(new Iterator<>(tester.boardSize, 0, 3, 1));
        tester.setReplications(1);
        tester.setFileName("MiniMaxComparisons.csv");
        tester.setProgressPrinting(true);
        tester.runExperiment();
    }

    @Test void gameWithOutGUITest() throws IOException {
        int wins = 0;
        for(int i = 0; i < 100; i++){
            GameWithoutGUI game = new GameWithoutGUI(new Settings(AgentFactory.makeAgent(AgentType.MINIMAX_SORTED,Color.RED),AgentFactory.makeAgent(AgentType.RANDOM,Color.BLUE),2),Color.RED);
            Agent winner = game.turnLogic();
            if(Objects.equals(winner.getName(), "MM Sorted"))
                wins ++;
            System.out.println(game.turnLogic().getName());
        }
        System.out.println(wins);
    }
}
