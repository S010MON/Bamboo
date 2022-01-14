package Bamboo.TestingAPI;

import java.awt.Color;
import java.io.IOException;

import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.controller.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class TestingAPIMain {

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
    @Test void LeonAnalyses() throws IOException{
        Tester tester = new Tester(AgentType.MINIMAX_SORTED,5);
        tester.addVariable(Variable.GRID_SIZE,1,5,1);
        tester.addVariable(TesterAgent.AGENT_1,Variable.SEARCH_DEPTH,1,5,1);
        tester.addVariable(TesterAgent.AGENT_2,Variable.HEURISTIC,new Heuristics[]{Heuristics.OUTER_WEIGHTED});
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.setReplications(100);
        tester.run();
        tester = new Tester(AgentType.RANDOM,5);
        tester.addVariable(TesterAgent.AGENT_1,Variable.HEURISTIC,new Heuristics[]{Heuristics.UNIFORM,Heuristics.OUTER_WEIGHTED,Heuristics.SPARSITY,Heuristics.NUM_GROUPS,Heuristics.SPARSITY_OUTER_WEIGHTED});
        tester.addVariable(TesterAgent.AGENT_2,Variable.HEURISTIC,new Heuristics[]{Heuristics.UNIFORM,Heuristics.OUTER_WEIGHTED,Heuristics.SPARSITY,Heuristics.NUM_GROUPS,Heuristics.SPARSITY_OUTER_WEIGHTED});
        tester.setReplications(100);
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.setFileName("RandomHeuristics.csv");
        tester.run();
    }

    @Disabled
    @Test void testHeuristics() throws IOException{
        Tester tester = new Tester(AgentType.RANDOM, 5);
        tester.setAgent2(AgentType.RANDOM);
        tester.addVariable(TesterAgent.AGENT_1,Variable.HEURISTIC,new Heuristics[]{Heuristics.MAXIMISE_MOVES});
        tester.addVariable(TesterAgent.AGENT_2,Variable.HEURISTIC,new Heuristics[]{Heuristics.OUTER_WEIGHTED});
        tester.setReplications(6);
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.run();
    }

    @Disabled
    @Test void newMCTSTest() throws IOException{
        Tester tester = new Tester(AgentType.MCTS,3);
        tester.addVariable(TesterAgent.AGENT_1,Variable.ITERATIONS,new float[]{1000,500,250,1});
        tester.addVariable(TesterAgent.AGENT_1,Variable.HEURISTIC,new Heuristics[]{Heuristics.OUTER_WEIGHTED,Heuristics.UNIFORM});
        tester.addVariable(TesterAgent.AGENT_1,Variable.C,0.1f,1f,0.1f);
        tester.setReplications(50);
        tester.setFileName("MCTSIter_C.csv");
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.run();
    }

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

    @Disabled
    @Test void gameWithOutGUITestMM() throws IOException {
        Tester tester = new Tester(AgentType.MINIMAX_SORTED,2);
        tester.setReplications(50);
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.run();

    }

    //Enabled for now, to check whether MCTS works correctly within the testing API
    @Disabled
    @Test void gameWithOutGUITestMCTS() throws IOException {
        Tester tester = new Tester(AgentType.MCTS,2);
        tester.setReplications(50);
        tester.addMetric(Metrics.ELAPSED_TIME);
        tester.run();
    }

    @Disabled
    @Test void logging() throws IOException{
        Tester tester = new Tester(AgentType.RANDOM, 5);
        tester.addVariable(TesterAgent.AGENT_1,Variable.HEURISTIC,new Heuristics[]{Heuristics.OUTER_WEIGHTED});
        tester.setMoveLogging(true);
        tester.setLoggedColor(Color.RED);
        tester.setReplications(10);
        tester.setWriting(false);
        tester.run();
    }
}
