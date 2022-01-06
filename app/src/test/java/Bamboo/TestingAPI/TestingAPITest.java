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
        Tester tester = new Tester(AgentType.MINIMAX_SORTED,2);
        tester.addVariable(TesterAgent.AGENT_1,Variable.SEARCH_DEPTH,1,3,1);
        tester.addVariable(TesterAgent.AGENT_1,Variable.HEURISTIC,new Heuristics[]{Heuristics.OUTER_WEIGHTED,Heuristics.UNIFORM});
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.setMoveLogging(true);
        tester.setLogFileName("loggingAfterRefactor.csv");
        tester.setReplications(2);
        tester.setFileName("testerTest.csv");
        tester.run();
    }

    @Disabled
    @Test void testHeuristics() throws IOException{
        Tester tester = new Tester(AgentType.RANDOM, 3);
        tester.setAgent2(AgentType.RANDOM);
        tester.addVariable(TesterAgent.AGENT_1,Variable.HEURISTIC,new Heuristics[]{Heuristics.UNIFORM,Heuristics.OUTER_WEIGHTED});
        tester.setReplications(50);
        tester.addMetric(Metrics.ELAPSED_TIME);

    @Disabled
    @Test void neuralNetTest() throws IOException{
        Tester tester = new Tester(AgentType.NEURAL_NET,5);
        tester.setReplications(50);
        tester.run();
    }

    @Disabled
    @Test void randomTimeBaseline() throws IOException{
        Tester tester = new Tester(AgentType.RANDOM,5);
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.setReplications(50);
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
