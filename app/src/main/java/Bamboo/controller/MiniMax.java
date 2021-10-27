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
    private int maxEval;
    int legals = 0;
    int whites = 0;

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
            updateUncoloredVectors(game);
        }

        Node start = new Node(game.getGrid());
        addAllChildren(start, 2, color);
        return new Vector(2,-2,0);
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

    public void addAllChildren(Node node, int depth, Color current_color){
        Grid grid = node.getGrid();
        if(depth > 0){
            addLegalChildren(node,grid,current_color);
            Color other_color;
            if(current_color == Color.RED)
                other_color = Color.BLUE;
            else
                other_color = Color.RED;
            System.out.println(uncolored_vectors.size());
            System.out.println(node.getChildren().size());
            for(Node child : node.getChildren()){
                addAllChildren(child,depth - 1,other_color);
            }
        }
    }

    void addLegalChildren(Node node, Grid grid, Color current_color) {
        for (Vector v : uncolored_vectors) {
            if (grid.isLegalMove(v, current_color)) {
                Grid copy = new GridGraphImp(4);
                for(Tile tile : grid.getAllTiles()){
                    copy.setTile(tile.getVector(),tile.getColour());
                }
                makeMove(copy, v, current_color);
                node.addChild(copy);
                legals += 1;
                System.out.println("Legals: " + legals);
                System.out.println(grid.getTile(v).getColour());
                if (grid.getTile(v).getColour() == Color.WHITE) {
                    whites += 1;
                    System.out.println("Whites: " + whites);
                }
            }
        }
    }
}