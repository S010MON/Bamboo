package Bamboo.model;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    int levels;
    ArrayList<ArrayList<Node>> nodes;

    public Tree(Node node){
        nodes = new ArrayList<>();
        node.setTree(this);
        this.levels = 1;
        nodes.add(new ArrayList<>(List.of(node)));
    }

    public void addNode(Node node){
        int lvl = node.getLevel();
        if(nodes.size()-1 < lvl){
            this.levels += 1;
            nodes.add(new ArrayList<>(List.of(node)));
        }
        else
            nodes.get(lvl).add(node);
    }

    public int getLevels(){
        return levels;
    }

    public ArrayList<Node> getNodesFromLevel(int lvl){
        return nodes.get(lvl);
    }

}
