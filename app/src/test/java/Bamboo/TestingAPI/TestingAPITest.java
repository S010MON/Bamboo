package Bamboo.TestingAPI;

import Bamboo.controller.*;

import java.awt.Color;
import java.io.IOException;
import java.util.Objects;

import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.model.GameWithoutGUI;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestingAPITest {
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

    @Disabled
    @Test void newAPI() throws IOException{
        Tester tester = new Tester(AgentType.MINIMAX_SORTED,5);
        tester.setAgent2(AgentType.RANDOM);
        tester.setMoveLogging(true);
        tester.setRedStartingPercentage(0.5f);
        tester.setLoggedColor(Color.RED);
        tester.setLogFileName("MinimaxvRandom.csv");
        tester.setReplications(1);
        tester.setProgressPrinting(true);
        for(int i = 0; i < 100; i++){
            tester.run();
            System.out.println(i);
        }
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
