package Bamboo.controller.MiniMax;

import java.util.ArrayList;
import Bamboo.controller.*;
import Bamboo.model.Grid;

public class NodeMM {
    private NodeMM parent;
    private ArrayList<NodeMM> children;
    private Grid grid;
    private int level;
    private int value;
    private int guess;
    private Vector move;

    public NodeMM(Grid new_grid){
        this.children = new ArrayList<>();
        this.grid = new_grid;
        this.level = 0;
    }

    public void addChild(Grid new_grid){
        NodeMM temp = new NodeMM(new_grid);
        temp.setLevel(this.level + 1);
        temp.setParent(this);
        children.add(temp);
    }

    public void addChild(NodeMM node){
        children.add(node);
    }

    public void addChild(Grid new_grid, Vector move){
        NodeMM temp = new NodeMM(new_grid);
        temp.setLevel(this.level + 1);
        temp.setParent(this);
        temp.setMove(move);
        temp.setGuess(move.getX()*move.getX() + move.getY()*move.getY() + move.getZ()*move.getZ());
        children.add(temp);
    }

    public void removeChild(NodeMM child){
        this.children.remove(child);
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

    public int getGuess(){return this.guess;}

    public void setGuess(int guess){this.guess = guess;}

    public Vector getMove(){return this.move;}

    public void setMove(Vector move){this.move = move;}

    public Grid getGrid(){
        return grid;
    }

    public void setParent(NodeMM prnt){
        this.parent = prnt;
    }

    public NodeMM getParent(){
        return parent;
    }

    public ArrayList<NodeMM> getChildren(){
        return children;
    }

    public String toString(){
        return "node at lv " + level;
    }

    public void sortChildren(){
        for(int i = 0; i < children.size(); i++){
            for(int j = children.size() - 1; j >= 0; j--){
                if(i >= j){
                    break;
                }
                if(children.get(i).getGuess() < children.get(j).getGuess()){
                    NodeMM temp = children.get(i);
                    children.set(i,children.get(j));
                    children.set(j,temp);
                }
            }
        }
    }
}
