package Bamboo.controller;

import Bamboo.model.Grid;

import java.awt.*;
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
}