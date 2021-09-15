package Bamboo.controller;

import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameLogic {
    public static boolean checkFinish(Grid grid, int current_player){
        Group.setGrid(grid);
        Color player_color;
        Group all_tiles = new Group(grid.getAllTiles());
        if(current_player == 0) player_color = Color.RED;
        else player_color = Color.BLUE;
        if(Group.does_not_contain_empty(all_tiles)) return true;
        if(Group.empty_tile_with_empty_neighbours(all_tiles, player_color)) return false;
        List<Group> groups = Group.collect_groups(player_color);
        return !Group.extendable(groups);
    }

    public static List<Tile> getLegalMoves(Grid grid, Color player_color){
        Group.setGrid(grid);
        Group legal_moves = new Group();
        legal_moves.addNew(empty_tiles_with_empty_neighbours(grid, player_color));
        legal_moves.addNew(group_extension_tiles(grid, player_color));
        return legal_moves.getTiles();
    }

    public static List<Tile> empty_tiles_with_empty_neighbours(Grid grid, Color player_color){
        Group.setGrid(grid);
        List<Tile> empty_tiles = new ArrayList<>();
        List<Tile> all_tiles = grid.getAllTiles();
        for(Tile tile : all_tiles){
            if(tile.getColour() == Color.WHITE){
                Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
                if(!Group.contains_color(neighbours, player_color) && Group.notin(tile, new Group(empty_tiles))){
                    empty_tiles.add(tile);
                }
            }
        }
        return empty_tiles;
    }

    public static List<Tile> group_extension_tiles(Grid grid, Color player_color){
        Group.setGrid(grid);
        List<Tile> return_tiles = new ArrayList<>();
        List<Group> groups = Group.collect_groups(player_color);
        if(Group.extendable(groups)){
            for(Group group : groups){
                Group extension_tiles = group.getAllNeighbours();
                for(Tile extension : extension_tiles.getTiles()){
                    Group nbs = new Group(grid.getAllNeighbours(extension.getVector()));
                    if(!Group.contains_color(nbs, player_color)){
                        return_tiles.add(extension);
                    }
                }
            }
        }
        return return_tiles;
    }
}