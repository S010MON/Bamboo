package Bamboo;

import Bamboo.ExperimentationWinRates.*;
import Bamboo.controller.*;

import java.awt.Color;
import java.io.IOException;
import java.util.Objects;

import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.model.GameWithoutGUI;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;


public class TestingAPI {
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

    @Ignore void newAPI() throws IOException{
        Tester tester = new Tester();
        tester.setAgent1(AgentType.MINIMAX_SORTED);
        tester.setAgent2(AgentType.MINIMAX_AB);
        tester.addVariable(TesterAgent.AGENT_1,Variable.SEARCH_DEPTH,1,2,1);
        tester.addVariable(TesterAgent.AGENT_2,Variable.SEARCH_DEPTH,1,2,1);
        tester.addVariable(Variable.GRID_SIZE,1,5,1);
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.setReplications(2);
        tester.setFileName("testerTest.csv");
        tester.run();
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
