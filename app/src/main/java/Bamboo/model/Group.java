package Bamboo.model;

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
            if(new Group(tiles).contains(tile)){
                tiles.add(tile);
            }
        }
    }

    public boolean contains(Tile tile){
        for(Tile t : tiles){
            if(t == tile)
                return true;
        }
        return false;
    }

    public Group getAllNeighbours(Grid grid){
        Group neighbours = new Group();
        Group final_neighbours = new Group();
        for(Tile tile : tiles){
            List<Tile> nbs = grid.getAllNeighbours(tile.getVector());
            for(Tile nb : nbs){
                if(nb.getColour() != tiles.get(0).getColour()){
                    neighbours.add(nb);
                }
            }
        }
        final_neighbours.addNew(neighbours.getTiles());
        return final_neighbours;
    }

    public Group(List<Tile> list){
        tiles = new ArrayList<>(list);
    }

    public Group(){
        tiles = new ArrayList<>();
    }

}
