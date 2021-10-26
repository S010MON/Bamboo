package Bamboo.model;

import java.awt.*;
import java.util.ArrayList;

public class Node {
    Tree tree;
    Node parent;
    ArrayList<Node> children;
    GridGraphImp grid;
    int level;
    int value;

    public Node(GridGraphImp new_grid){
        this.children = new ArrayList<>();
        this.grid = new_grid;
        this.level = 0;
        this.value = new_grid.evaluateGame(Color.RED);
    }

    public void setTree(Tree tr){
        this.tree = tr;
    }

    public void addChild(GridGraphImp new_grid){
        Node temp = new Node(new_grid);
        temp.setLevel(this.level + 1);
        temp.setParent(this);
        temp.setTree(this.tree);
        this.tree.addNode(temp);
        children.add(temp);
    }

    public void setLevel(int lvl){
        this.level = lvl;
    }

    public int getLevel(){
        return level;
    }

    public int getValue(){
        return grid.evaluateGame(Color.RED);
    }

    public GridGraphImp getGrid(){
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
}
