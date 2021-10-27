package Bamboo.controller;

import Bamboo.model.*;
import org.checkerframework.checker.units.qual.A;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MiniMax implements Agent{
    String name = "Tim";
    private Color color;
    private ArrayList<Vector> uncolored_vectors = new ArrayList<>();

    public MiniMax(Color color){
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
            System.out.println("Updating uncolored vectors...");
            updateUncoloredVectors(game);
        }
        int depth = (int)Math.round(2.2*Math.exp(-0.03*uncolored_vectors.size()) + 1.65);
        //depth = 3;
        System.out.println("Depth: " + depth);
        System.out.println("Remaining: " + uncolored_vectors.size());
        Node start = new Node(game.getGrid());
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
        System.out.println("Remaining size: " + uncolored_vectors.size());
    }

    public void setGame(ArrayList<Vector> vectors){
        this.uncolored_vectors = vectors;
    }

    public Vector minimaxMove(Node node, int depth, Color agent_color){
        boolean maximizingPlayer;
        if(agent_color == Color.RED)
            maximizingPlayer = true;
        else
            maximizingPlayer = false;
        System.out.println("Agent color: " + agent_color + " maximizing: " + maximizingPlayer);
        int evaluation = minimax(node, depth, maximizingPlayer);
        ArrayList<Node> options = node.getChildren();
        for (Node option : options) {
            System.out.println("Is " + option.getValue() + " = " + evaluation + "? -- " + option + "; Eval:" + option.getGrid().evaluateGame());
            if (option.getValue() == node.getValue()) {
                System.out.println("Decision: " + option.getMove());
                return option.getMove();
            }
        }
        System.out.println("DID NOT FIND BEST MOVE");
        return new Vector(0,0,0);
    }

    public int minimax(Node node, int depth, boolean maximizingPlayer){
        Color current_color;
        if(maximizingPlayer)
            current_color = Color.RED;
        else
            current_color = Color.BLUE;
        Grid grid = node.getGrid();
        if(depth == 0 || grid.isFinished(current_color)){
            node.setValue(grid.evaluateGame());
            return grid.evaluateGame();
        }
        addLegalChildren(node,current_color);
        if(maximizingPlayer){
            int maxEval = -1000000;
            int eval;
            for(Node child : node.getChildren()){
                eval = minimax(child,depth - 1,false);
                maxEval = Math.max(eval,maxEval);
            }
            node.setValue(maxEval);
            return maxEval;
        }
        else{
            int eval;
            int minEval = 10000000;
            for(Node child : node.getChildren()){
                eval =  minimax(child,depth - 1,true);
                minEval = Math.min(eval,minEval);
            }
            node.setValue(minEval);
            return minEval;
        }
    }

    void addLegalChildren(Node node, Color current_color) {
        Grid grid = node.getGrid();
        for (Vector v : uncolored_vectors) {
            if (grid.isLegalMove(v, current_color)) {
                Grid copy = grid.copy();
                makeMove(copy, v, current_color);
                node.addChild(copy,v);
            }
        }
    }
}