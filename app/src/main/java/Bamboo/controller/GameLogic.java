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
        if(current_player == 0)
            player_color = Color.RED;
        else
            player_color = Color.BLUE;
        if(GroupController.does_not_contain_empty(all_tiles))
            return true;
        if(exists_empty_tile_with_empty_neighbours(grid, all_tiles, player_color))
            return false;
        if(legal_group_extension_tiles(game, player_color).size() == 0)
            return true;
        return false;
    }

    public static List<Tile> getLegalMoves(Game game, Color player_color){
        Grid grid = game.getGrid();
        Group legal_moves = new Group();
        legal_moves.addNew(empty_tiles_with_empty_neighbours(grid, player_color));
        legal_moves.addNew(legal_group_extension_tiles(game, player_color));
        return legal_moves.getTiles();
    }

    public static boolean is_legal_move(Game game, Tile move, Color player_color){
        Grid grid = game.getGrid();
        Group neighbours = new Group(grid.getAllNeighbours(move.getVector()));
        if(!GroupController.contains_color(neighbours, player_color))
            return true;
        Group original = GroupController.get_adjacent_group(grid, move, player_color);
        List<Group> groups = GroupController.collect_groups_by_color(grid, player_color);
        int max_current_group_size = 0;
        for(Group group : groups){
            if(group.size() > max_current_group_size)
                max_current_group_size = group.size();
        }
        int max_size = GroupController.count_groups_for_player(game, player_color);
        int group_size = original.size();
        if(!has_nonGroup_sameColor_neighbours(grid, move, original, player_color)){
            if(group_size < max_size || max_size == 0){
                return true;
            }
        }
        else{
                return is_valid_group_merger(grid, move, player_color, original, max_size, max_current_group_size);
            }
        return false;
    }

    static List<Tile> legal_group_extension_tiles(Game game, Color player_color){
        Grid grid = game.getGrid();
        Group return_tiles = new Group();
        List<Group> groups = GroupController.collect_groups_by_color(grid, player_color);
        int max_current_group_size = 0;
        Group visited_extensions = new Group();
        for(Group group : groups){
            if(group.size() > max_current_group_size)
                max_current_group_size = group.size();
        }
        for(Group group : groups){
            Group extension_tiles = group.getAllNeighbours(grid);
            for(Tile extension : extension_tiles.getTiles()){
                if(GroupController.not_in(extension,visited_extensions)){
                    if(extension.getColour() == Color.WHITE){
                        if(is_legal_move(game, extension, player_color))
                            return_tiles.add(extension);
                    }
                    visited_extensions.add(extension);
                }
            }
        }
        return return_tiles.getTiles();
    }

    static boolean is_valid_group_merger(Grid grid, Tile move, Color player_color, Group original, int max_size, int max_current_group_size){
        int group_size = original.size();
        int other_group_size = 0;
        int other_group_count = 0;
        Group neighbours = new Group(grid.getAllNeighbours(move.getVector()));
        Group found_nonGroup_tiles = new Group();
        List<Group> sameColor_groups = new ArrayList<>();
        for(Tile tile : neighbours.getTiles()){
            if(tile.getColour() == player_color && GroupController.not_in(tile, original)){
                found_nonGroup_tiles.add(tile);
                Group tile_group = GroupController.collect_group_from_tile(grid, tile, player_color);
                if(!contains_group(sameColor_groups, tile_group)){
                    sameColor_groups.add(tile_group);
                    other_group_size += tile_group.size();
                    other_group_count ++;
                }
            }
        }
        if(group_size + other_group_size < max_size - other_group_count && max_current_group_size < max_size - other_group_count){
            return true;
        }
        return false;
    }

    static List<Tile> empty_tiles_with_empty_neighbours(Grid grid, Color player_color){
        List<Tile> empty_tiles = new ArrayList<>();
        List<Tile> all_tiles = grid.getAllTiles();
        for(Tile tile : all_tiles){
            if(tile.getColour() == Color.WHITE){
                Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
                if(!GroupController.contains_color(neighbours, player_color)){
                    empty_tiles.add(tile);
                }
            }
        }
        return empty_tiles;
    }

    static boolean contains_group(List<Group> groupList, Group query){
        for(Group group : groupList){
            if(GroupController.is_in(query.get(0), group)){
                return true;
            }
        }
        return false;
    }

    static boolean has_nonGroup_sameColor_neighbours(Grid grid, Tile tile, Group group, Color color){
        Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
        for(Tile nb : neighbours.getTiles()){
            if(GroupController.not_in(nb, group) && nb.getColour() == color){
                return true;
            }
        }
        return false;
    }

    static boolean exists_empty_tile_with_empty_neighbours(Grid grid,Group tiles, Color color){
        for(Tile tile: tiles.getTiles()){
            if(tile.getColour() == Color.WHITE){
                CubeVector vector = tile.getVector();
                List<Tile> nb = grid.getAllNeighbours(vector);
                if(!GroupController.contains_color(new Group(nb), color)){
                    return true;
                }
            }
        }
        return false;
    }
}