package Bamboo.TestingAPI;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Plan {
    private int varCount;
    ArrayList<Iterator> vars;
    private int totalGames;
    private int[][] plan;

    public Plan(ArrayList<Iterator> variables){
        varCount = variables.size();
        vars = variables;
        int rows = 1;
        for(Iterator i : variables){
            rows *= i.getArrayBounds();
        }
        int[] repetitions = new int[varCount];
        for(int i = 0; i < varCount; i++){
            repetitions[i] = 1;
            for(int j = 0; j < i; j++){
                repetitions[i] *= variables.get(j).getArrayBounds();
            }
        }
        plan = new int[rows][varCount];
        for(int j = 0; j < varCount; j++){
            int index = 0;
            int rep = repetitions[j];
            while(index < rows){
                for(int i = 0; i < variables.get(j).getArrayBounds(); i++){
                    for(int ii = 0; ii < rep; ii++){
                        plan[index][j] = i;
                        index++;
                    }
                }
            }
        }
        System.out.println("Plan of length " + rows + " with " + varCount + " variables made.");
        totalGames = rows;
    }

    public int[] getRowIndices(int row){return plan[row];}
    public int getCols(){return this.varCount;}
    public int getRows(){return this.totalGames;}
}
