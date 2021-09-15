package Bamboo.controller;

import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Group {
    ArrayList<Tile> tiles = new ArrayList<>();
    public static Grid grid;
    List<Tile> grid_tiles = new ArrayList<>();

    public static void setGrid(Grid grid_){
        grid = grid_;
    }

    List<Tile> getTiles(){
        return tiles;
    }

    int size(){
        return tiles.size();
    }

    void add(Tile tile){
        tiles.add(tile);
    }

    Tile get(int index){
        return tiles.get(index);
    }

    void addAll(List<Tile> addition){
        tiles.addAll(addition);
    }

    void addNew(List<Tile> addition){
        for(Tile tile : addition){
            if(notin(tile, new Group(tiles))){
                tiles.add(tile);
            }
        }
    }

    Group getAllNeighbours(){
        Group neighbours = new Group();
        Group final_neighbours = new Group();
        for(Tile tile : tiles){
            List<Tile> nbs = grid.getAllNeighbours(tile.getVector());
            neighbours.addNew(nbs);
        }
        for(Tile tile : neighbours.getTiles()){
            if(notin(tile, new Group(tiles))){
                final_neighbours.add(tile);
            }
        }
        return final_neighbours;
    }

    public Group(List<Tile> list){
        tiles = new ArrayList<Tile>(list);
        grid_tiles = grid.getAllTiles();
    }

    public Group(){
        tiles = new ArrayList<Tile>();
        grid_tiles = grid.getAllTiles();
    }

    static boolean isin(Tile query, Group list){
        for (Tile tile : list.getTiles()) {
            if (query == tile) {
                return true;
            }
        }
        return false;
    }

    static boolean notin(Tile query, Group list){
        return !isin(query, list);
    }

    static boolean contains_empty(Group tiles){
        for(Tile tile : tiles.getTiles()){
            if(tile.getColour() == Color.WHITE){
                return true;
            }
        }
        return false;
    }

    static boolean does_not_contain_empty(Group tiles){
        return !contains_empty(tiles);
    }

    static boolean empty_tile_with_empty_neighbours(Group tiles, Color color){
        for(Tile tile: tiles.getTiles()){
            if(tile.getColour() == Color.WHITE){
                CubeVector vector = tile.getVector();
                List<Tile> nb = grid.getAllNeighbours(vector);
                if(!contains_color(new Group(nb), color)){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean contains_color(Group tiles, Color color){
        for(Tile tile : tiles.getTiles()){
            if(tile.getColour() == color){
                return true;
            }
        }
        return false;
    }

    static Group collect_group(Tile tile, Color color)
    {
        Group group = new Group();
        group.add(tile);
        int current_member_id = 0;
        ArrayList<Tile> visited_tiles = new ArrayList<>();
        List<Tile> neighbours;
        Tile current_tile = tile;
        while(visited_tiles.size() < group.size()){
            neighbours = grid.getAllNeighbours(current_tile.getVector());
            for(Tile nb : neighbours){
                if(nb.getColour() == color){
                    if(notin(nb, group)){
                        group.add(nb);
                    }
                }
            }
            visited_tiles.add(current_tile);
            current_tile = group.get(current_member_id);
            current_member_id ++;
        }
        return group;
    }

    public static List<Group> collect_groups(Color color){
        List<Tile> tiles = grid.getAllTiles();
        List<Group> groups = new ArrayList<Group>();
        Group collected_tiles = new Group();
        for(Tile tile : tiles){
            if(notin(tile, collected_tiles)){
                if(tile.getColour() == color){
                    Group current_group = collect_group(tile, color);
                    collected_tiles.addAll(current_group.getTiles());
                    groups.add(current_group);
                }
            }
        }
        return groups;
    }

    public static int countGroups(Color player_color, Grid grid_){
        setGrid(grid_);
        return collect_groups(player_color).size();
    }

    public static boolean extendable(List<Group> groups){
        int max_group_size = groups.size();
        if(!groups.isEmpty()){
            for(Group current_group : groups){
                if(current_group.size() < max_group_size){
                    return has_placeable_neighbours(current_group);
                }
            }
        }
        else{
            return true;
        }
        return false;
    }

    static boolean has_placeable_neighbours(Group current_group){
        for(Tile tile : current_group.getTiles()){
            Group nb = new Group(grid.getAllNeighbours(tile.getVector()));
            for(Tile current_nb : nb.getTiles()){
                if(current_nb.getColour() == Color.WHITE){
                    return !has_nonGroup_sameColor_neighbours(current_nb, current_group, tile.getColour());
                }
            }
        }
        return false;
    }

    static boolean has_nonGroup_sameColor_neighbours(Tile tile, Group group, Color color){
        Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
        for(Tile nb : neighbours.getTiles()){
            if(notin(nb, group) && nb.getColour() == color){
                return true;
            }
        }
        return false;
    }

}
