package Bamboo;

import Bamboo.controller.Logger;
import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.GridArrayImp;
import Bamboo.model.GridGraphImp;

import java.awt.*;
import java.util.HashMap;

public class TimeTest
{
    public static void main(String[] args)
    {
        int iterations = 100;

        Data graphData = new Data();
        Logger.logCSV("graphData.csv", Data.getCSVHeader());
        for(int i = 0; i < iterations; i++)
        {
            graphData = graphData.add(testGraphImp());
            Logger.logCSV("graphData.csv", graphData.toCSV());
        }
        System.out.println(iterations + " iteration Graph implementation: \n" + graphData.toString());

        Data arrayData = new Data();
        Logger.logCSV("arrayData.csv", Data.getCSVHeader());
        for(int i = 0; i < iterations; i++)
        {
            arrayData = arrayData.add(testArrayImp());
            Logger.logCSV("arrayData.csv", arrayData.toCSV());
        }
        System.out.println(iterations + " iterations of Array implementation: \n" + arrayData.toString());
    }

    private static Data testGraphImp()
    {
        HashMap<Vector, Color> moves = MovesLists.getMoves();
        Grid grid = new GridGraphImp(5);
        return testOneGame(grid, moves);
    }

    private static Data testArrayImp()
    {
        HashMap<Vector, Color> moves = MovesLists.getMoves();
        Grid grid = new GridArrayImp(5);
        return testOneGame(grid, moves);
    }

    private static Data testOneGame(Grid grid, HashMap<Vector, Color> moves)
    {
        long sumInsertions = 0;
        long sumChecks = 0;
        long insertionMax = 0;
        long insertionMin = Long.MAX_VALUE;
        long legalMax = 0;
        long legalMin = Long.MAX_VALUE;
        for(Vector v: moves.keySet())
        {
            long time = testIsLegal(grid, v, moves.get(v));
            sumChecks += time;
            legalMax = Long.max(legalMax, time);
            legalMin = Long.min(legalMin, time);

            time = testInsert(grid, v, moves.get(v));
            sumInsertions += time;
            insertionMax = Long.max(insertionMax, time);
            insertionMin = Long.min(insertionMin, time);
        }
        return new Data(sumInsertions,
                        sumChecks,
                        insertionMax,
                        insertionMin,
                        legalMax,
                        legalMin);
    }

    /* Time a single insertion */
    private static long testInsert(Grid grid, Vector v, Color c)
    {
        long startTime = System.currentTimeMillis();
        grid.setTile(v, c);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }

    /* Time a single legalCheck */
    private static long testIsLegal(Grid grid, Vector v, Color c)
    {
        long startTime = System.currentTimeMillis();
        grid.isLegalMove(v, c);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }

    // ---------------------------------------------------------------------------------------------------------------

    private long testTimeGraph()
    {
        HashMap<Vector, Color> moves = MovesLists.getMoves();
        long startTime = System.currentTimeMillis();
        Grid grid = new GridGraphImp(5);
        for(Vector v: moves.keySet())
        {
            if(grid.isLegalMove(v, moves.get(v)))
                grid.setTile(v,moves.get(v));
        }
        long finishTime = System.currentTimeMillis();
        return  finishTime - startTime;
    }

    void testGraph1000()
    {
        long sum=0;
        for(int i=0; i<10000; i++)
        {
            sum += testTimeGraph();
        }
        System.out.println("Sum of 1000 runs of graph imp: "+sum+" milliseconds");
    }

    private long testTimeArray()
    {
        HashMap<Vector, Color> moves = MovesLists.getMoves();
        long startTime = System.currentTimeMillis();
        Grid grid = new GridArrayImp(5);
        for(Vector v: moves.keySet())
        {
            if(grid.isLegalMove(v, moves.get(v)))
                grid.setTile(v,moves.get(v));
        }
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }

    void testArray1000()
    {
        long sum=0;
        for(int i=0; i<10000; i++)
        {
            sum += testTimeArray();
        }
        System.out.println("Sum of 1000 runs of array imp: "+sum+" milliseconds");
    }
}

class Data
{
    public long totalTime;
    public long sumInsertions;
    public long sumChecks;
    public long insertionMax;
    public long insertionMin;
    public long legalMax;
    public long legalMin;

    public Data()
    {
        this.totalTime = 0;
        this.sumInsertions = 0;
        this.sumChecks = 0;
        this.insertionMax = 0;
        this.insertionMin = Long.MAX_VALUE;
        this.legalMax = 0;
        this.legalMin = Long.MAX_VALUE;
    }

    public Data(long sumInsertions,
                long sumChecks,
                long insertionMax,
                long insertionMin,
                long legalMax,
                long legalMin)
    {
        this.totalTime = sumChecks + sumInsertions;
        this.sumInsertions = sumInsertions;
        this.sumChecks = sumChecks;
        this.insertionMax = insertionMax;
        this.insertionMin = insertionMin;
        this.legalMax = legalMax;
        this.legalMin = legalMin;
    }

    public Data add(Data other)
    {
        return new Data(this.sumInsertions + other.sumInsertions,
                        this.sumChecks + other.sumChecks,
                        Long.max(this.insertionMax, other.insertionMax),
                        Long.min(this.insertionMin, other.insertionMin),
                        Long.max(this.legalMax, other.legalMax),
                        Long.min(this.legalMin, other.legalMin));
    }

    @Override
    public String toString()
    {
        return  "Total time: " + this.totalTime + "ms\n" +
                "Total time for insertions: " + this.sumInsertions + "ms\n" +
                "Max time for a single insertion: " + this.insertionMax + "ms\n" +
                "Min time for a single insertion: " + this.insertionMin + "ms\n" +
                "Total time for checks: " + this.sumChecks + "ms\n" +
                "Max time for a single check: " + this.legalMax + "ms\n" +
                "Min time for a single check: " + this.legalMin + "ms\n";
    }

    public String toCSV()
    {
        return  totalTime + "," +
                sumInsertions + "," +
                insertionMax + "," +
                insertionMin + "," +
                sumChecks + "," +
                insertionMax + "," +
                insertionMin;
    }

    public static String getCSVHeader()
    {
        return "totalTime,"+
                "sumInsertions,"+
                "insertionMax,"+
                "insertionMin,"+
                "sumChecks,"+
                "insertionMax,"+
                "insertionMin";
    }
}
