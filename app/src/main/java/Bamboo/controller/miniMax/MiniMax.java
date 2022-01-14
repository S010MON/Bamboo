package Bamboo.controller.miniMax;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.model.*;

import java.awt.Color;
import java.util.ArrayList;

public class MiniMax implements Agent
{
    protected String name;
    protected Color color;
    protected ArrayList<Vector> uncolored_vectors = new ArrayList<>();
    protected int totalEvaluations = 0;
    public Mutable<Integer> depth = new Mutable<>(1);
    public boolean testing = false;

    public MiniMax(Color color)
    {
        this.color = color;
        this.name = "Max";
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getType()
    {
        return "minimax";
    }

    @Override
    public boolean isHuman()
    {
        return false;
    }

    @Override
    public Vector getNextMove(Game game)
    {
        if(uncolored_vectors.size() == 0)
            uncolored_vectors = new ArrayList<>(game.getGrid().getAllVectors());
        else
            updateUncoloredVectors(game.getGrid());

        int d;
        if(!testing){
            depth.set((int)Math.round(7.1*Math.exp(-0.07*uncolored_vectors.size()) + 1.55));
            d = (int)Math.round(7.1*Math.exp(-0.07*uncolored_vectors.size()) + 1.55);
        }
        else{
            d = Math.round((float)(Number)(depth.get()));
        }
        NodeMM start = new NodeMM(game.getGrid());
        return minimaxMove(start, d, this.color);
    }

    @Override
    public Color getColor()
    {
        return color;
    }


    @Override
    public Mutable<Integer> getDepth() {
        testing = true;
        uncolored_vectors = new ArrayList<>();
        return this.depth;
    }

    @Override
    public Mutable<Integer> getIterations() {
        return null;
    }

    @Override
    public Mutable<Float> getC() {
        return null;
    }

    @Override
    public Mutable<Heuristic> getHeuristic() {return null;}
    
    public Mutable<Integer> getSwitchThreshold() {
        return null;
    }

    public void makeMove(Grid grid, Vector move, Color player_color){
        grid.setTile(move,player_color);
    }

    public void updateUncoloredVectors(Grid grid){
        uncolored_vectors.removeIf(vec -> grid.getTile(vec).getColour() != Color.WHITE);
    }

    public void setGame(ArrayList<Vector> vectors){
        this.uncolored_vectors = vectors;
    }

    public Vector minimaxMove(NodeMM node, int depth, Color agent_color){
        boolean maximizingPlayer = (agent_color == Color.RED);
        int evaluation = minimax(node, depth, maximizingPlayer);//This must stay in for now
        ArrayList<NodeMM> options = node.getChildren();
        for (NodeMM option : options) {
            if (option.getValue() == node.getValue()) {
                return option.getMove();
            }
        }
        return new Vector(0,0,0);
    }

    public int minimax(NodeMM node, int depth, boolean maximizingPlayer){
        totalEvaluations += 1;
        Color current_color;
        if(maximizingPlayer)
            current_color = Color.RED;
        else
            current_color = Color.BLUE;
        Grid grid = node.getGrid();
        if(depth == 0 || grid.isFinished(current_color)){
            node.setValue(grid.evaluateGame(current_color));
            return grid.evaluateGame(current_color);
        }
        addLegalChildren(node,current_color);
        return switch_minimax(node, depth, maximizingPlayer);
    }

    public int switch_minimax(NodeMM node, int depth,boolean maximizingPlayer){
        int eval;
        if(maximizingPlayer){
            int maxEval = -1000000;
            for(NodeMM child : node.getChildren())
            {
                eval = minimax(child,depth - 1,false);
                maxEval = Math.max(eval,maxEval);
            }
            node.setValue(maxEval);
            return maxEval;
        }
        else{
            int minEval = 1000000;
            for(NodeMM child : node.getChildren())
            {
                eval =  minimax(child,depth - 1,true);
                minEval = Math.min(eval,minEval);
            }
            node.setValue(minEval);
            return minEval;
        }
    }

    public void addLegalChildren(NodeMM node, Color current_color) {
        Grid grid = node.getGrid();
        for (Vector v : uncolored_vectors) {
            if (grid.isLegalMove(v, current_color)) {
                Grid copy = grid.copy();
                makeMove(copy, v, current_color);
                node.addChild(copy,v);
            }
        }
    }

    public int getCalls(){
        return this.totalEvaluations;
    }
}