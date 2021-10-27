package Bamboo.model;

import java.awt.*;
import java.util.ArrayList;
import Bamboo.controller.*;

public class Node {
    private Node parent;
    private ArrayList<Node> children;
    private Grid grid;
    private int level;
    private int value;
    private Vector move;

    public Node(Grid new_grid){
        this.children = new ArrayList<>();
        this.grid = new_grid;
        this.level = 0;
    }

    public void addChild(Grid new_grid){
        Node temp = new Node(new_grid);
        temp.setLevel(this.level + 1);
        temp.setParent(this);
        children.add(temp);
    }

    public void addChild(Grid new_grid, Vector move){
        Node temp = new Node(new_grid);
        temp.setLevel(this.level + 1);
        temp.setParent(this);
        temp.setMove(move);
        children.add(temp);
    }

    public void setLevel(int lvl){
        this.level = lvl;
    }

    public int getLevel(){
        return level;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int val){this.value = val;}

    public Vector getMove(){return this.move;}

    public void setMove(Vector move){this.move = move;}

    public Grid getGrid(){
        return grid;
    }

    public void setParent(Node pnt){
        this.parent = pnt;
    }

    public Node getParent(){
        return parent;
    }

    public ArrayList<Node> getChildren(){
        return children;
    }

    public String toString(){
        return "node at lv " + level;
    }
}
