package Bamboo;

import Bamboo.ExperimentationWinRates.Iterator;
import Bamboo.ExperimentationWinRates.WinRateTester;
import Bamboo.controller.AgentType;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.*;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Bamboo.controller.miniMax.MiniMax;
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class IteratorTest {
    Mutable<Integer> mutable = new Mutable<>(0);
    @Test void testMutable(){
        MiniMax.depth = new Mutable<>(3);
        Iterator<Integer> iter = new Iterator(MiniMax.depth,0,5,10);
        for(int i = 0; i < 10; i++){
            iter.set(i);
            System.out.println(MiniMax.depth.get());
        }
    }

    @Test void testTester() throws IOException {
        WinRateTester tester = new WinRateTester(AgentType.MINIMAX_SORTED,2);
        tester.setReplications(10);
        tester.setStartingColor(Color.BLUE);
        float[][] result = tester.runExperiment();
        for(int i = 0; i < 3; i++){
            System.out.println(result[i][0]);
        }
    }
}
