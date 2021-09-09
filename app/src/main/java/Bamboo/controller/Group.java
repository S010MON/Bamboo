package Bamboo.controller;

import Bamboo.model.Colour;
import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.awt.*;
import java.lang.reflect.Array;
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



    public static boolean checkFinish(Grid grid,int current_player){
        Group.setGrid(grid);
        Color player_color;
        List<Tile> all_tiles = grid.getAllTiles();
        //get player color
        if(current_player == 0){
            player_color = Color.RED;
        }
        else{
            player_color = Color.BLUE;
        }

        //2 ending conditions:
        //(1) no empty tile left
        //(2) no possibility to extend any group -> cannot be if there is an empty tile with only empty neighbours

        //(1) iterate through tiles and check colour
        //if there is no colour there, break loop and set is_finished to false
        //-> grid cannot be full
        if(!contains_empty(new Group(all_tiles))){
            //if grid does not contain empty tile, return true
            return true;
        }


        //(2) do:
        //- list all groups and find max allowed size
        //- check if there is a group below that size
        //- for all groups that are extendable, check if there are free neighbours
        List<Group> groups = collect_groups(player_color);
        boolean can_place = placeable_with_groups(groups);
        return !can_place;
        //list all groups
        //run through tiles and save all adjacent same-colour tiles
        //save them and check whether it has been the center before
        //start at top left tile (0,0,0)


        //iterate through tiles, collect individual groups

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
    //----Search empty tile function---
    static boolean contains_empty(Group tiles){
        for(Tile tile : tiles.getTiles()){
            if(tile.getColor() == Color.WHITE){
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
                if(nb.getColor() == color){
                    if(!isin(nb, group)){
                        group.add(nb);
                    }
                }
            }
            visited_tiles.add(current_tile);
            current_member_id ++;
            //choose next tile to pivot off of
            current_tile = group.get(current_member_id);
        }
        return group;
    }

    static List<Group> collect_groups(Color color){
        List<Tile> tiles = grid.getAllTiles();
        List<Group> groups = new ArrayList<Group>();
        Group collected_tiles = new Group();
        int max_group_size = 0;
        for(Tile tile : tiles){
            if(!isin(tile, collected_tiles)){
                if(tile.getColor() == color){
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

    static boolean placeable_with_groups(List<Group> groups){
        int max_group_size = groups.size();
        if(!groups.isEmpty()){
            for(Group current_group : groups){//for each group:
                if(current_group.size() < max_group_size){//check if current group can be extended
                    //there exists a group that is less than max size
                    //check whether it has adjacent free tiles
                    for(Tile tile : current_group.getTiles()){
                        Group nb = new Group(grid.getAllNeighbours(tile.getVector()));
                        for(Tile current_nb : nb.getTiles()){
                            if(current_nb.getColor() == Color.WHITE){
                                //if there exists an empty tile adjacent to a group member, game cannot be over
                                return true;
                            }

                        }
                    }
                }

            }

        }
        else{
            return true;
        }
        return false;
    }
}
