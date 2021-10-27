package Bamboo.model;

import java.awt.*;
import java.util.ArrayList;

public class Node {
    Node parent;
    ArrayList<Node> children;
    Grid grid;
    int level;
    int value;

    public Node(Grid new_grid){
        this.children = new ArrayList<>();
        this.grid = new_grid;
        this.level = 0;
        this.value = new_grid.evaluateGame();
    }

    public void addChild(Grid new_grid){
        Node temp = new Node(new_grid);
        temp.setLevel(this.level + 1);
        temp.setParent(this);
        children.add(temp);
    }

    public void setLevel(int lvl){
        this.level = lvl;
    }

    public int getLevel(){
        return level;
    }

    public int getValue(){
        return grid.evaluateGame();
    }

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
