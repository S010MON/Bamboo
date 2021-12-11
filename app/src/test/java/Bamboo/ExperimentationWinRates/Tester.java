package Bamboo.ExperimentationWinRates;

import Bamboo.controller.*;
import Bamboo.model.GameWithoutGUI;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

public class Tester {
    private AgentType player1, player2;
    private Agent agent1, agent2;
    private Color startingColor = Color.WHITE;
    private boolean printResult = true;
    private boolean writeResult = true;
    private boolean writeProgress = true;
    public Mutable<Integer> boardSize = new Mutable<>(3);
    private int replications;
    private String fileName;
    private ArrayList<Iterator> variables = new ArrayList<>();
    private Iterator variable1;
    private Iterator variable2;
    private float redPercentage = 0.5f;
    private int gamesPlayed = 0;
    private String loggingFile = "log.csv";
    private Color loggedColor = Color.WHITE;
    private boolean LOG_MOVES = false;
    private int total;
    private ArrayList<String> colnames = new ArrayList<>();

    public Tester(AgentType agent, int size) throws IOException {
        fileName = agent.toString() + ".csv";
        player1 = agent;
        player2 = AgentType.RANDOM;
        agent1 = AgentFactory.makeAgent(player1,Color.RED);
        agent2 = AgentFactory.makeAgent(player2,Color.BLUE);
        boardSize.set(size);
    }

    public Tester() throws IOException{
        boardSize.set(5);
    }

    //set the amount of replications to do
    public void setReplications(int rep){this.replications = rep;}

    public Color getStartingColor(){return startingColor;}
    public void setStartingColor(Color color){startingColor = color;}
    public void resetStartingColor(){startingColor = Color.WHITE;}

    public float[][] run() throws IOException{
        int count = 0;
        float[][] plan = makePlan();
        float[] results = new float[plan.length];
        float[][] table = new float[plan.length][plan[0].length + 1];
        for(int i = 0; i < plan.length; i++){
            for(int var = 0; var < plan[0].length; var++){
                if(!this.variables.get(var).isEmpty()){
                    this.variables.get(var).getReference().set(plan[i][var]);
                }
                table[i][var] = plan[i][var];
            }
            results[i] = getWinPercentage();
            table[i][plan[0].length] = results[i];
            count++;
        }
        colnames.add("Result");
        if(writeResult)writeToCSV(table);
        if(printResult)printToConsole(table);
        return table;
    }

    //Gets winner from one game
    private Agent getWinner() throws IOException {
        this.gamesPlayed ++;
        Settings settings = new Settings(agent1, agent2, ((Number)boardSize.get()).intValue());
        GameWithoutGUI game;
        if(startingColor == Color.WHITE)
            if(gamesPlayed /(float)this.replications < this.redPercentage)
                game = new GameWithoutGUI(settings, Color.RED);
            else
                game = new GameWithoutGUI(settings, Color.BLUE);
        else
            game = new GameWithoutGUI(settings,startingColor);
        setLoggingSettings(game);
        Agent winner = game.turnLogic();
        if(writeProgress)
            System.out.println("Game " + gamesPlayed + "out of " + total+ " processed, " + 100*(float)gamesPlayed/total + "%.");
        return winner;
    }

    //returns player 1s win rate over <replications> games
    private float getWinPercentage() throws IOException {
        int sum = 0;
        for(int i = 0; i < replications; i++){
            if(getWinner() == agent1)
                sum++;
        }
        return sum/(float)replications;
    }

    private void writeToCSV(float[][] data){
        if(writeResult){
            for(int i = 0; i < data[0].length; i++) {
                Logger.logCSV(fileName, colnames.get(i));
            }
            for(int i = 0; i < data.length; i++){
                String row = "";
                for(int j = 0; j < data[0].length; j++){
                    row += data[i][j] + ",";
                }
                Logger.logCSV(fileName,row);
            }
        }
    }

    public void setFileName(String fileName){this.fileName = fileName;}
    public String getFileName(){return this.fileName;}

    private void printToConsole(float[][] result){
        for(String s : this.colnames){
            System.out.print(s + ", ");
        }
        System.out.println();
        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < result[0].length; j++){
                System.out.print(result[i][j] + ", ");
            }
            System.out.println();
        }
    }

    private void setLoggingSettings(GameWithoutGUI game){
        game.setLogFileName(this.loggingFile);
        game.setLogColor(this.loggedColor);
        game.setLOG_MOVES(this.LOG_MOVES);
    }

    public Agent getAgent1() {return agent1;}
    public Agent getAgent2() {return agent2;}

    public void setAgent1(AgentType a) throws IOException{this.agent1 = AgentFactory.makeAgent(a,Color.RED);}
    public void setAgent2(AgentType a) throws IOException{this.agent2 = AgentFactory.makeAgent(a,Color.BLUE);}
    public void setOpponent(AgentType opponent) throws IOException {
        this.player2 = opponent;
        this.agent2 = AgentFactory.makeAgent(player2,Color.BLUE);
    }

    public void addVariable(Variable v, float value){
        Mutable ref = VariableFactory.getValueFromVariable(v,this.getAgent1(),this);
        Iterator variable = new Iterator<>(ref,value);
        colnames.add(v.toString());
        pushVariable(variable);
    }

    public void addVariable(Variable v, float[] value){
        Mutable ref = VariableFactory.getValueFromVariable(v,this.getAgent1(),this);
        Iterator variable = new Iterator<>(ref,value);
        colnames.add(v.toString());
        pushVariable(variable);
    }

    public void addVariable(Variable v, float min, float max, float step){
        Mutable ref = VariableFactory.getValueFromVariable(v,this.getAgent1(),this);
        Iterator variable = new Iterator<>(ref,min,max,step);
        colnames.add(v.toString());
        pushVariable(variable);
    }

    public void addVariable(TesterAgent agent,Variable v, float value){
        Agent a = TesterAgentFactory.getAgentReference(this,agent);
        Mutable ref = VariableFactory.getValueFromVariable(v,a,this);
        Iterator variable = new Iterator<>(ref,value);
        colnames.add(agent.toString() + "_" + v.toString());
        pushVariable(variable);
    }

    public void addVariable(TesterAgent agent, Variable v, float[] value){
        Agent a = TesterAgentFactory.getAgentReference(this,agent);
        Mutable ref = VariableFactory.getValueFromVariable(v,a,this);
        Iterator variable = new Iterator<>(ref,value);
        colnames.add(agent.toString() + "_" + v.toString());
        pushVariable(variable);
    }

    public void addVariable(TesterAgent agent ,Variable v, float min, float max, float step){
        Agent a = TesterAgentFactory.getAgentReference(this,agent);
        Mutable ref = VariableFactory.getValueFromVariable(v,a,this);
        Iterator variable = new Iterator<>(ref,min,max,step);
        colnames.add(agent.toString() + "_" + v.toString());
        pushVariable(variable);
    }


    public void setVariable1(Iterator i){this.variable1 = i;}
    public void setVariable2(Iterator i){this.variable2 = i;}
    public Iterator getVariable1(){return this.variable1;}
    public Iterator getVariable2(){return this.variable2;}
    public void setWriting(boolean argument){this.writeResult = argument;}
    public void setProgressPrinting(boolean argument){this.writeProgress = argument;}
    public void setPrinting(boolean argument){this.printResult = argument;}
    public void setRedStartingPercentage(float percentage){this.redPercentage = percentage;}
    public void setMoveLogging(boolean arg){this.LOG_MOVES = arg;}
    public void setLogFileName(String name){this.loggingFile = name;}
    public void setLoggedColor(Color color){this.loggedColor = color;}

    private void pushVariable(Iterator i){
        this.variables.add(i);
        if(this.getVariable1() == null)this.variable1 = i;
        else this.variable2 = i;
    }

    public float[][] makePlan(){
        int rows = 1;
        for(Iterator i : this.variables){
            rows *= i.getArrayBounds();
        }
        int cols = this.variables.size();
        int[] repetitions = new int[cols];
        for(int i = 0; i < cols; i++){
            repetitions[i] = 1;
            for(int j = 0; j < i; j++){
                repetitions[i] *= this.variables.get(j).getValues().length;
            }
        }
        float[][] plan = new float[rows][cols];
        for(int j = 0; j < cols; j++){
            int index = 0;
            int rep = repetitions[j];
            while(index < rows){
                for(int i = 0; i < this.variables.get(j).getValues().length; i++){
                    for(int ii = 0; ii < rep; ii++){
                        plan[index][j] = this.variables.get(j).getValues()[i];
                        index++;
                    }
                }
            }
        }
        System.out.println("Plan of length " + rows + " with " + cols + " variables made.");
        this.total = rows*this.replications;
        return plan;
    }

    private int[] fromFloat(float[] ary){
        int[] array = new int[ary.length];
        for(int i = 0; i < ary.length; i++){
            array[i] = (int)Math.round(ary[i]);
        }
        return array;
    }
}
