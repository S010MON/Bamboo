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
        if(Group.group_extension_tiles(grid, player_color).size() == 0) return true;
        List<Group> groups = Group.collect_groups(player_color);
        return !Group.extendable(groups);
    }

    public static List<Tile> getLegalMoves(Grid grid, Color player_color){
        Group.setGrid(grid);
        Group legal_moves = new Group();
        legal_moves.addNew(Group.empty_tiles_with_empty_neighbours(grid, player_color));
        legal_moves.addNew(Group.group_extension_tiles(grid, player_color));
        return legal_moves.getTiles();
    }




}