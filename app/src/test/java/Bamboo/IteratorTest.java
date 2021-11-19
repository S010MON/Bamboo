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
        WinRateTester tester = new WinRateTester(AgentType.MCTS,2);
        tester.setReplications(15);
        tester.setVariable1(new Iterator<>(MCTS.c,0,1.01f,0.5f));
        tester.setVariable2(new Iterator<>(MCTS.iterations,1,1001,450));
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
