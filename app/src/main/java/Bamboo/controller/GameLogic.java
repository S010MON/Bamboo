package Bamboo.controller;

import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.Group;
import Bamboo.model.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GameLogic {
    public static boolean checkFinish(Game game, int current_player){
        Grid grid = game.getGrid();
        Color player_color;
        Group all_tiles = new Group(grid.getAllTiles());
        if(current_player == 0) player_color = Color.RED;
        else player_color = Color.BLUE;
        if(GroupControllerImp.does_not_contain_empty(all_tiles))
            return true;
        if(GroupControllerImp.empty_tile_with_empty_neighbours(grid, all_tiles, player_color))
            return false;
        if(GroupControllerImp.group_extension_tiles(game, player_color).size() == 0)
            return true;
        return false;
    }

    public static List<Tile> getLegalMoves(Game game, Color player_color){
        Grid grid = game.getGrid();
        Group legal_moves = new Group();
        legal_moves.addNew(GroupControllerImp.empty_tiles_with_empty_neighbours(grid, player_color));
        legal_moves.addNew(GroupControllerImp.group_extension_tiles(game, player_color));
        return legal_moves.getTiles();
    }




}