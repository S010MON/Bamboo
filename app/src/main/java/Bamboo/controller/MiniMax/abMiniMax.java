package Bamboo.controller.MiniMax;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.*;

import java.awt.*;
import java.util.ArrayList;

public class abMiniMax implements Agent {
    String name = "Anton";
    private Color color;
    private ArrayList<Vector> uncolored_vectors = new ArrayList<>();
    int totalEvaluations;

    public abMiniMax(Color color){
        this.color = color;
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
    public Vector getNextMove(Game game)
    {
        if(uncolored_vectors.size() == 0){
            uncolored_vectors = new ArrayList<>(game.getGrid().getAllVectors());
        }
        else{
            updateUncoloredVectors(game);
        }
        int depth = (int)Math.round(7.1*Math.exp(-0.07*uncolored_vectors.size()) + 1.55);
        NodeMM start = new NodeMM(game.getGrid());
        return minimaxMove(start, depth, this.color);
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    Grid makeMove(Grid grid, Vector move, Color player_color){
        grid.setTile(move,player_color);
        return grid;
    }

    void updateUncoloredVectors(Game game){
        uncolored_vectors.removeIf(vec -> game.getGrid().getTile(vec).getColour() != Color.WHITE);
    }

    public void setGame(ArrayList<Vector> vectors){
        this.uncolored_vectors = vectors;
    }

    public Vector minimaxMove(NodeMM node, int depth, Color agent_color){
        boolean maximizingPlayer;
        if(agent_color == Color.RED)
            maximizingPlayer = true;
        else
            maximizingPlayer = false;
        int evaluation = minimax(node, depth, -1000000,1000000, maximizingPlayer);//This must stay in for now
        ArrayList<NodeMM> options = node.getChildren();
        for (NodeMM option : options) {
            if (option.getValue() == node.getValue()) {
                return option.getMove();
            }
        }
        return new Vector(0,0,0);
    }

    public int minimax(NodeMM node, int depth, int alpha, int beta, boolean maximizingPlayer){
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
        return switch_minimax(node, depth,alpha,beta, maximizingPlayer);
    }

    public int switch_minimax(NodeMM node, int depth, int alpha, int beta, boolean maximizingPlayer){
        int eval;
        if(maximizingPlayer){
            int maxEval = -1000000;
            for(NodeMM child : node.getChildren()){
                eval = minimax(child,depth - 1,alpha, beta,false);
                maxEval = Math.max(eval,maxEval);
                if(maxEval >= beta)
                    break;
                alpha = Math.max(alpha,maxEval);
            }
            node.setValue(maxEval);
            return maxEval;
        }
        else{
            int minEval = 1000000;
            for(NodeMM child : node.getChildren()){
                eval =  minimax(child,depth - 1,alpha, beta,true);
                minEval = Math.min(eval,minEval);
                if(minEval <= alpha)
                    break;
                beta = Math.min(beta,minEval);
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