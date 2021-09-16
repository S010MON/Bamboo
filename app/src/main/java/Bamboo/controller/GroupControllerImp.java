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
        return collect_groups(grid, player_color).size();
    }

    public static boolean not_in(Tile query, Group list){
        return !is_in(query, list);
    }

    public static List<Group> collect_groups(Grid grid, Color color){
        List<Tile> tiles = grid.getAllTiles();
        List<Group> groups = new ArrayList<Group>();
        Group collected_tiles = new Group();
        for(Tile tile : tiles){
            if(tile.getColour() == color){
                if(not_in(tile, collected_tiles)){
                    Group current_group = collect_group(grid, tile, color);
                    collected_tiles.addNew(current_group.getTiles());
                    groups.add(current_group);
                }
            }
        }
        return groups;
    }

    static Group collect_group(Grid grid, Tile tile, Color color)
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

    public static List<Tile> empty_tiles_with_empty_neighbours(Grid grid, Color player_color){
        List<Tile> empty_tiles = new ArrayList<>();
        List<Tile> all_tiles = grid.getAllTiles();
        for(Tile tile : all_tiles){
            if(tile.getColour() == Color.WHITE){
                Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
                if(!contains_color(neighbours, player_color)){
                    empty_tiles.add(tile);
                }
            }
        }
        return empty_tiles;
    }

    public static List<Tile> group_extension_tiles(Game game, Color player_color){
        Grid grid = game.getGrid();
        Group return_tiles = new Group();
        List<Group> groups = collect_groups(grid, player_color);
        int max_current_group_size = 0;
        Group visited_extensions = new Group();
        for(Group group : groups){
            if(group.size() > max_current_group_size)
                max_current_group_size = group.size();
        }
        for(Group group : groups){
            Group extension_tiles = group.getAllNeighbours(grid);
            for(Tile extension : extension_tiles.getTiles()){
                if(not_in(extension,visited_extensions)){
                    if(extension.getColour() == Color.WHITE && is_legal_move(game, extension, player_color)){
                        return_tiles.add(extension);
                    }
                    visited_extensions.add(extension);
                }
            }
        }
        return return_tiles.getTiles();
    }

    static boolean is_legal_move(Game game, Tile move, Color player_color){
        Grid grid = game.getGrid();
        Group original = get_adjacent_group(grid, move, player_color);
        List<Group> groups = collect_groups(grid, player_color);
        int max_current_group_size = 0;
        for(Group group : groups){
            if(group.size() > max_current_group_size)
                max_current_group_size = group.size();
        }
        int max_size = count_groups_for_player(game, player_color);
        int group_size = original.size();
        int other_group_size = 0;
        int other_group_count = 0;
        if(!has_nonGroup_sameColor_neighbours(grid, move, original, player_color)){
            if(group_size < max_size){
                return true;
            }
        }
        else{
            return is_valid_group_merger(grid, move, player_color, original, other_group_size, max_size, other_group_count, max_current_group_size);
        }
        return false;
    }

    static boolean is_valid_group_merger(Grid grid, Tile move, Color player_color, Group original, int other_group_size, int max_size, int other_group_count, int max_current_group_size){
        int group_size = original.size();
        Group neighbours = new Group(grid.getAllNeighbours(move.getVector()));
        Group found_nonGroup_tiles = new Group();
        List<Group> sameColor_groups = new ArrayList<>();
        for(Tile tile : neighbours.getTiles()){
            if(tile.getColour() == player_color && not_in(tile, original)){
                found_nonGroup_tiles.add(tile);
                Group tile_group = collect_group(grid, tile, player_color);
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

    static Group get_adjacent_group(Grid grid, Tile tile, Color player_color){
        Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
        for(Tile nb : neighbours.getTiles()){
            if(nb.getColour() == player_color){
                return collect_group(grid, nb, player_color);
            }
        }
        return new Group();
    }

    static boolean contains_group(List<Group> groupList, Group query){
        for(Group group : groupList){
            if(is_in(query.get(0), group)){
                return true;
            }
        }
        return false;
    }

    static boolean has_nonGroup_sameColor_neighbours(Grid grid, Tile tile, Group group, Color color){
        Group neighbours = new Group(grid.getAllNeighbours(tile.getVector()));
        for(Tile nb : neighbours.getTiles()){
            if(not_in(nb, group) && nb.getColour() == color){
                return true;
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

    static boolean is_in(Tile query, Group list){
        for (Tile tile : list.getTiles()) {
            if (query.getVector().getX() == tile.getVector().getX() && query.getVector().getY() == tile.getVector().getY() && query.getVector().getZ() == tile.getVector().getZ()) {
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

    static boolean empty_tile_with_empty_neighbours(Grid grid,Group tiles, Color color){
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
}
