package Bamboo.controller.miniMax;

import java.util.*;

import Bamboo.controller.*;
import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import com.google.common.collect.Lists;

public class NodeMM {
    private final int[] POWERS_OF_10 = {1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000};
    private NodeMM parent;
    private ArrayList<NodeMM> children;
    private Grid grid;
    private int level;
    private int value;
    private int guess;
    private Vector move;
    private int comparisons = 0;
    private int maxGuess = 0;

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
        if(temp.getGuess() > maxGuess){maxGuess = temp.getGuess();}
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

    public int sortChildren(){
        for(int i = 0; i < children.size(); i++){
            for(int j = children.size() - 1; j >= 0; j--){
                comparisons ++;
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
        return comparisons;
    }

    public int sortChildrenRadix(){
        int maxDigits = getDigits(maxGuess);
        ArrayList<Deque<NodeMM>> buckets = new ArrayList<>();
        for(int ii = 0; ii < 10; ii++){
            buckets.add(new ArrayDeque<>());
        }
        for(int i = 0; i < maxDigits; i++){
            for(NodeMM node : children){
                comparisons ++;
                int dig = getDigit(node.getGuess(), i);
                buckets.get(9-dig).addLast(node);
            }
            children.clear();
            for(int ii = 0; ii < 10; ii++){
                while(!buckets.get(ii).isEmpty()){
                    comparisons++;
                    children.add(buckets.get(ii).removeFirst());
                }
            }
        }
        return comparisons;
    }

    private int getDigits(int x){
        int digits = 1;
        int tmp = 10;
        while(x >= tmp){
            digits ++;
            tmp*=10;
        }
        return digits;
    }

    private int getDigit(int x, int pos){
        int divisor = POWERS_OF_10[pos];
        return x/divisor % 10;
    }
}
