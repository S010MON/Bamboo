package Bamboo.TestingAPI;

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
    private String fileName = "experiment.csv";
    private ArrayList<Iterator> variables = new ArrayList<>();
    private float redPercentage = 0.5f;
    private int gamesPlayed = 0;
    private int localGameCounter = 0;
    private String loggingFile = "log.csv";
    private Color loggedColor = Color.WHITE;
    private boolean LOG_MOVES = false;
    private boolean TRACK_TIME = false;
    private float elapsed = 0f;
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
        agent1 = AgentFactory.makeAgent(AgentType.RANDOM,Color.RED);
        agent2 = AgentFactory.makeAgent(AgentType.RANDOM,Color.BLUE);
        boardSize.set(5);
    }

    public void setReplications(int rep){this.replications = rep;}

    public Color getStartingColor(){return startingColor;}
    public void setStartingColor(Color color){startingColor = color;}
    public void resetStartingColor(){startingColor = Color.WHITE;}

    public float[][] run(){
        colnames.add("WinRate");
        if(TRACK_TIME)colnames.add("ms");
        int count = 0;
        float[][] plan = makePlan();
        int cols = plan[0].length + 1;
        float[] results = new float[plan.length];
        if(TRACK_TIME)cols += 1;
        float[][] table = new float[plan.length][cols];
        writeHeader();
        for(int i = 0; i < plan.length; i++){
            for(int var = 0; var < plan[0].length; var++){
                if(!this.variables.get(var).isEmpty()){
                    this.variables.get(var).getReference().set(plan[i][var]);
                }
                table[i][var] = plan[i][var];
            }
            results[i] = getWinPercentage();
            table[i][plan[0].length] = results[i];
            if(TRACK_TIME)table[i][cols-1] = elapsed;
            count++;
            writeRow(table[i]);
        }
        if(writeResult)writeToCSV(table);
        if(printResult)printToConsole(table);
        return table;
    }

    //Gets winner from one game
    private Agent getWinner(){
        this.gamesPlayed ++;
        Settings settings = new Settings(agent1, agent2, ((Number)boardSize.get()).intValue());
        GameWithoutGUI game;
        if(startingColor == Color.WHITE)
            if(localGameCounter /(float)this.replications < this.redPercentage)
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

    private float getWinPercentage(){
        int sum = 0;
        long before = System.nanoTime();
        for(int i = 0; i < replications; i++){
            if(getWinner() == agent1)
                sum++;
        }
        long after = System.nanoTime();
        this.elapsed = nanoToMilli((after-before)/(float)replications);
        localGameCounter = 0;
        return sum/(float)replications;
    }

    private float nanoToMilli(float nano){return nano * 0.001f;}

    private void writeHeader(){
        Logger.logCSV(fileName,headerString());
    }

    private void writeRow(float[] row){
        String dstr = "";
        for(float i : row){
            dstr += i + ",";
        }
        Logger.logCSV(fileName,dstr);
    }

    private void writeToCSV(float[][] data){
        if(writeResult){
            String names = headerString();
            Logger.logCSV(fileName,names);
            for(int i = 0; i < data.length; i++){
                String row = "";
                for(int j = 0; j < data[0].length; j++){
                    row += data[i][j] + ",";
                }
                Logger.logCSV(fileName,row);
            }
        }
    }

    private String headerString(){
        String names = "";
        for (String colname : colnames) {
            names += colname + ", ";
        }
        return names;
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

    public void addMetric(Metrics m){
        switch(m){
            case ELAPSED_TIME -> this.TRACK_TIME = true;
        }
    }
    public void setWriting(boolean argument){this.writeResult = argument;}
    public void setProgressPrinting(boolean argument){this.writeProgress = argument;}
    public void setPrinting(boolean argument){this.printResult = argument;}
    public void setRedStartingPercentage(float percentage){this.redPercentage = percentage;}
    public void setMoveLogging(boolean arg){this.LOG_MOVES = arg;}
    public void setLogFileName(String name){this.loggingFile = name;}
    public void setLoggedColor(Color color){this.loggedColor = color;}

    private void pushVariable(Iterator i){
        this.variables.add(i);
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
