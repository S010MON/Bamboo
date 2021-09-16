package Bamboo.model;

import Bamboo.controller.GroupControllerImp;

import java.util.ArrayList;
import java.util.List;

public class Group {
    ArrayList<Tile> tiles = new ArrayList<>();

    public List<Tile> getTiles(){
        return tiles;
    }

    public int size(){
        return tiles.size();
    }

    public void add(Tile tile){
        tiles.add(tile);
    }

    public Tile get(int index){
        return tiles.get(index);
    }

    public void addAll(List<Tile> addition){
        tiles.addAll(addition);
    }

    public void addNew(List<Tile> addition){
        for(Tile tile : addition){
            if(GroupControllerImp.notin(tile, new Group(tiles))){
                tiles.add(tile);
            }
        }
    }

    public Group getAllNeighbours(){
        Group neighbours = new Group();
        Group final_neighbours = new Group();
        for(Tile tile : tiles){
            List<Tile> nbs = GroupControllerImp.grid.getAllNeighbours(tile.getVector());
            neighbours.addNew(nbs);
        }
        for(Tile tile : neighbours.getTiles()){
            if(GroupControllerImp.notin(tile, new Group(tiles))){
                final_neighbours.add(tile);
            }
        }
        return final_neighbours;
    }

    public Group(List<Tile> list){
        tiles = new ArrayList<Tile>(list);;
    }

    public Group(){
        tiles = new ArrayList<Tile>();;
    }

}
