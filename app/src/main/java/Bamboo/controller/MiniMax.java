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
    private int maxEval, minEval;
    int legals = 0;
    int whites = 0;
    int eval = 0;
    Vector bestMove;

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

        Node start = new Node(game.getGrid());
        return minimaxMove(start, 3, this.color);
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
        int evaluation = minimax(node, depth, maximizingPlayer);
        Grid temp = node.getGrid().copy();
        ArrayList<Node> options = node.getChildren();
        for(int i = 0; i < options.size(); i++){
            System.out.println("Is " + options.get(i).getValue() + " = " + evaluation + "?");
            if(options.get(i).getValue() == evaluation && temp.isLegalMove(options.get(i).getMove(),agent_color)){
                System.out.println("Decision: " + options.get(i).getMove());
                return options.get(i).getMove();
            }
        }
        return new Vector(0,0,0);
    }

    public int minimax(Node node, int depth, boolean maximizingPlayer){
        Color current_color;
        if(maximizingPlayer)
            current_color = Color.RED;
        else
            current_color = Color.BLUE;
        Grid grid = node.getGrid();
        if(depth == 0){
            return grid.evaluateGame();
        }
        addLegalChildren(node,grid,current_color);
        if(maximizingPlayer){
            maxEval = -1000000;
            for(Node child : node.getChildren()){
                eval = minimax(child,depth - 1,false);
                child.setValue(eval);
                maxEval = Math.max(eval,maxEval);
            }
            node.setValue(maxEval);
            return maxEval;
        }
        else{
            minEval = 10000000;
            for(Node child : node.getChildren()){
                eval =  minimax(child,depth - 1,true);
                minEval = Math.min(eval,minEval);
            }
            node.setValue(minEval);
            return minEval;
        }
    }

    void addLegalChildren(Node node, Grid grid, Color current_color) {
        for (Vector v : uncolored_vectors) {
            if (grid.isLegalMove(v, current_color)) {
                Grid copy = grid.copy();
                makeMove(copy, v, current_color);
                node.addChild(copy,v);
                legals += 1;
                //System.out.println("Legals: " + legals);
                //System.out.println(grid.getTile(v).getColour());
                if (grid.getTile(v).getColour() == Color.WHITE) {
                    whites += 1;
                    //System.out.println("Whites: " + whites);
                }
            }
        }
    }

    Color swappedColor(Color current_color){
        if(current_color == Color.RED)
            return Color.BLUE;
        else
            return Color.RED;
    }
}