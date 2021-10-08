package Bamboo;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.GridGraphImp;

import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.HashMap;

public class TimeTest {
    private long testTimeGraph() {
        HashMap<Vector, Color> moves = MovesLists.getMoves();
        long startTime = System.currentTimeMillis();
        Grid grid = new GridGraphImp(5);
        for(Vector v: moves.keySet()){
            grid.setTile(v,moves.get(v));
        }
        long finishTime = System.currentTimeMillis();
        return  finishTime - startTime;
    }

    private long testTimeArray() {
        HashMap<Vector, Color> moves = MovesLists.getMoves();
        long startTime = System.currentTimeMillis();
        Grid grid = new GridArrayImp(5);
        for(Vector v: moves.keySet()){
            grid.setTile(v,moves.get(v));
        }
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }
    @Test void testGraph1000() {
        long sum=0;
        for(int i=0; i<1000; i++){
            sum += testTimeGraph();
        }
        System.out.println("For 1000 times, time taken was: "+sum+" millisecons");

    }
    @Test void testArray1000(){
        long sum=0;
        for(int i=0; i<1000; i++){
            sum += testTimeArray();
        }
        System.out.println("For 1000 times, time taken was: "+sum+" millisecons");
    }



}
