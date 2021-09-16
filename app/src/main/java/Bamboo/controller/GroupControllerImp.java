package Bamboo.controller;

import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.Group;
import Bamboo.model.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupControllerImp implements GroupController{

    //collects group for each tile of that color that it hasnt collected a group for
    public static int count_groups_for_player(Game game, Color player_color){
        Grid grid = game.getGrid();
        return collect_groups_by_color(grid, player_color).size();
    }

    public static boolean not_in(Tile query, Group list){
        return !is_in(query, list);
    }

    public static List<Group> collect_groups_by_color(Grid grid, Color color){
        List<Tile> tiles = grid.getAllTiles();
        List<Group> groups = new ArrayList<Group>();
        Group collected_tiles = new Group();
        for(Tile tile : tiles){
            if(tile.getColour() == color){
                if(not_in(tile, collected_tiles)){
                    Group current_group = collect_group_from_tile(grid, tile, color);
                    collected_tiles.addNew(current_group.getTiles());
                    groups.add(current_group);
                }
            }
        }
        return groups;
    }

    static Group collect_group_from_tile(Grid grid, Tile tile, Color color)
    {
        Group group = new Group();
        group.add(tile);
        int current_member_id = -1;
        Group visited_tiles = new Group();
        List<Tile> neighbours;
        Tile current_tile = tile;
        while(visited_tiles.size() < group.size()){
            current_member_id ++;
            current_tile = group.get(current_member_id);
            neighbours = grid.getAllNeighbours(current_tile.getVector());
            for(Tile nb : neighbours){
                if(nb.getColour() == color){
                    if(not_in(nb, group)){
                        group.add(nb);
                    }
                }
            }
            visited_tiles.add(current_tile);
        }
        return group;
    }

    public static Group get_adjacent_group(Grid grid, Tile tile, Color player_color){
        Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
        for(Tile nb : neighbours.getTiles()){
            if(nb.getColour() == player_color){
                return collect_group_from_tile(grid, nb, player_color);
            }
        }
        return new Group();
    }


    static boolean contains_color(Group tiles, Color color){
        for(Tile tile : tiles.getTiles()){
            if(tile.getColour() == color){
                return true;
            }
        }
        return false;
    }

    static boolean is_in(Tile query, Group list){
        for (Tile tile : list.getTiles()) {
            if (query.getVector().getX() == tile.getVector().getX() &&
                query.getVector().getY() == tile.getVector().getY() &&
                query.getVector().getZ() == tile.getVector().getZ())
            {
                return true;
            }
        }
        return false;
    }

    static boolean does_not_contain_empty(Group tiles){
        return !contains_empty(tiles);
    }

    static boolean contains_empty(Group tiles){
        for(Tile tile : tiles.getTiles()){
            if(tile.getColour() == Color.WHITE){
                return true;
            }
        }
        return false;
    }
}
