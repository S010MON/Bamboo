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

    //VERY INEFFICIENT
    public Group(List<Tile> list){
        tiles = new ArrayList<Tile>(list);
        grid_tiles = grid.getAllTiles();
    }

    public Group(){
        tiles = new ArrayList<Tile>();
        grid_tiles = grid.getAllTiles();
    }





    //----lookup function------
    static boolean isin(Tile query, Group list){
        for (Tile tile : list.getTiles()) {
            if (query == tile) {
                return true;
            }
        }
        return false;
    }
    //collect group a tile belongs to
    static Group collect_group(Tile tile, Color color)
    {
        Group group = new Group();
        group.add(tile);
        int current_member_id = 0;
        ArrayList<Tile> visited_tiles = new ArrayList<>();
        List<Tile> neighbours;
        Tile current_tile = tile;
        while(visited_tiles.size() < group.size()){//continue as long as not all group members have been visited
            neighbours = grid.getAllNeighbours(current_tile.getVector());
            for(Tile nb : neighbours){
                if(nb.getColour() == color){
                    if(!isin(nb, group)){
                        group.add(nb);
                    }
                }
            }
            visited_tiles.add(current_tile);

            //choose next tile to pivot off of
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
            if(!isin(tile, collected_tiles)){
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

}
