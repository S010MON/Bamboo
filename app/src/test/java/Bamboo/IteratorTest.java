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

import Bamboo.controller.miniMax.MiniMax;
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.model.GameWithoutGUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IteratorTest {
    Mutable<Integer> mutable = new Mutable<>(0);
    @Test void testMutable(){
        MiniMax.depth = new Mutable<>(3);
        Iterator<Integer> iter = new Iterator(MiniMax.depth,0,5,1);
        for(int i = 0; i < 10; i++){
            iter.set(i);
            System.out.println(MiniMax.depth.get());
        }
    }

    @Test void testTester() throws IOException {
        //instantiate Tester which runs experiments for you
        //Requires agent type and grid size
        WinRateTester tester = new WinRateTester(AgentType.MCTS,2);
        //Set the number of games you want the agent to play against random (for each unique combination of variable values)
        tester.setReplications(5);
        //Specify references to the variables you want to iterate over. These must be of the Mutable class
        Mutable<Float> reference_to_variable_1 = MCTS.c;
        Mutable<Integer> reference_to_variable_2 = MCTS.iterations;
        //Instantiate an iterator over those references. Pass it the reference itself, as well as min, max and step size
        Iterator<Float> iterator_for_variable_1 = new Iterator<>(reference_to_variable_1,0,1.01f,0.5f);
        Iterator<Integer> iterator_for_variable_2 = new Iterator<>(reference_to_variable_2,1,1001,450);
        //Set the (up to) two variables the tester should iterate over (pass it the iterator you just created)
        tester.setVariable1(iterator_for_variable_1);
        tester.setVariable2(iterator_for_variable_2);
        //Set file name if you want to, else it will be named after the agent type you chose
        tester.setFileName("mytest.csv");
        //run the experiment and print results to console
        float[][] result = tester.runExperiment();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(result[i][j]+ ", ");
            }
            System.out.println();
        }
    }

    @Test void gameWithOutGUITest() throws IOException {
        int wins = 0;
        for(int i = 0; i < 100; i++){
            MiniMaxSortedAB.depth.set(3f);
            GameWithoutGUI game = new GameWithoutGUI(new Settings(AgentFactory.makeAgent(AgentType.MINIMAX_SORTED,Color.RED),AgentFactory.makeAgent(AgentType.RANDOM,Color.BLUE),2),Color.RED);
            Agent winner = game.turnLogic();
            if(winner.getName() == "MM Sorted")
                wins ++;
            System.out.println(game.turnLogic().getName());

        }
        System.out.println(wins);
    }
}
