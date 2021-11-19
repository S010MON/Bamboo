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
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.model.GameWithoutGUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IteratorTest {
    Mutable<Integer> mutable = new Mutable<>(0);
    @Test void testMutable(){
        MiniMax.depth = new Mutable<>(3f);
        Iterator<Integer> iter = new Iterator(MiniMax.depth,0,5,1);
        for(int i = 0; i < 10; i++){
            iter.set(i);
            System.out.println(MiniMax.depth.get());
        }
    }

    @Test void tutorialTester() throws IOException {
        //instantiate Tester which runs experiments for you and returns a matrix with win rates by variable values
        //Requires agent type and grid size
        WinRateTester tester = new WinRateTester(AgentType.MCTS,2);
        //Set the number of games you want the agent to play against random (for each unique combination of variable values)
        tester.setReplications(15);
        //If you want to let the agent play against something else than random, pass an agent type to
        tester.setOpponent(AgentType.RANDOM);//Random is default, so if you dont want to change it, you dont need to call this method
        //----------This stuff you only have to set if you want to set custom iteration bounds.
        //The correct references are already set. If you want to remove a variable, do:
        tester.setVariable1(new Iterator<>("empty"));
        //On the variable you want to remove.
        //----------If you want to set your own iteration bounds, use the following:
        //Specify references to the variables you want to iterate over. These must be of the Mutable class
        Mutable<Float> reference_to_variable_1 = MCTS.c;
        Mutable<Integer> reference_to_variable_2 = MCTS.iterations;
        //For Minimax: Mutable<Float> ... = MiniMax.depth; Replace "MiniMax" with the actual class name of the one youre using
        //Instantiate an iterator over those references. Pass it the reference itself, as well as min, max and step size
        Iterator<Float> iterator_for_variable_1 = new Iterator<>(reference_to_variable_1,0,1.01f,0.5f);
        Iterator<Integer> iterator_for_variable_2 = new Iterator<>(reference_to_variable_2,1,1001,450);
        //Set the (up to) two variables the tester should iterate over (pass it the iterator you just created)
        //Available are:
        //Random: None
        //Minimax(and versions of it): .depth
        //MCTS: .c, .iterations
        tester.setVariable1(iterator_for_variable_1);
        tester.setVariable2(iterator_for_variable_2);
        //----------You can also set an iterator from a float array.
        Iterator<Integer> iterator_from_array = new Iterator<>(MCTS.iterations,new float[]{1,1000});
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
        tester.setWriting(false);
        //run the experiment and (if flag was set) print results to console
        float[][] result = tester.runExperiment();//returns a float array, should you want to call this multiple times in a loop or something?
    }

    @Test void gameWithOutGUITest() throws IOException {
        int wins = 0;
        for(int i = 0; i < 100; i++){
            MiniMaxSortedAB.depth.set(3f);
            GameWithoutGUI game = new GameWithoutGUI(new Settings(AgentFactory.makeAgent(AgentType.MINIMAX_SORTED,Color.RED),AgentFactory.makeAgent(AgentType.RANDOM,Color.BLUE),2),Color.RED);
            Agent winner = game.turnLogic();
            if(Objects.equals(winner.getName(), "MM Sorted"))
                wins ++;
            System.out.println(game.turnLogic().getName());

        }
        System.out.println(wins);
    }
}
