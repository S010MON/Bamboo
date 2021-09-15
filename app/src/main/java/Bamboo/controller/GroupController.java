package Bamboo.controller;

import Bamboo.model.Grid;
import Bamboo.model.Group;
import Bamboo.model.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GroupController {
    public static Grid grid;

    static void setGrid(Grid grid_){
        grid = grid_;
    }

    static boolean is_in(Tile query, Group list){
        for (Tile tile : list.getTiles()) {
            if (query.getVector().getX() == tile.getVector().getX() && query.getVector().getY() == tile.getVector().getY() && query.getVector().getZ() == tile.getVector().getZ()) {
                return true;
            }
        }
        return false;
    }

    public static boolean notin(Tile query, Group list){
        return !is_in(query, list);
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

    public static List<Group> collect_groups(Color color){
        List<Tile> tiles = grid.getAllTiles();
        List<Group> groups = new ArrayList<Group>();
        Group collected_tiles = new Group();
        int i = 0;
        for(Tile tile : tiles){
            if(tile.getColour() == color){
                if(notin(tile, collected_tiles)){
                    Group current_group = collect_group(tile, color);
                    collected_tiles.addNew(current_group.getTiles());
                    groups.add(current_group);
                    i++;

                }
            }
        }
        return groups;
    }
    
    static Group collect_group(Tile tile, Color color)
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
                    if(notin(nb, group)){
                        group.add(nb);
                    }
                }
            }
            visited_tiles.add(current_tile);
        }
        return group;
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

    public static List<Tile> empty_tiles_with_empty_neighbours(Grid grid, Color player_color){
        setGrid(grid);
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

    public static List<Tile> group_extension_tiles(Grid grid, Color player_color){
        setGrid(grid);
        Group return_tiles = new Group();
        List<Group> groups = collect_groups(player_color);
        for(Group group : groups){
            Group extension_tiles = group.getAllNeighbours();
            for(Tile extension : extension_tiles.getTiles()){
                if(extension.getColour() == Color.WHITE && is_legal_extension(grid, extension, group, player_color)){
                    return_tiles.add(extension);
                }
            }
        }
        return return_tiles.getTiles();
    }

    static boolean is_legal_extension(Grid grid, Tile extension, Group original, Color player_color){
        Group friendly_nonGroupTiles = new Group();
        int friendly_group_sum = 0;
        int max_size = countGroups(player_color, grid);
        int group_size = original.size();
        Group neighbours = new Group(grid.getAllNeighbours(extension.getVector()));
        for(Tile nb : neighbours.getTiles()){
            if(nb.getColour() == player_color && notin(nb, original)){
                Group nb_group = collect_group(nb, player_color);
                if(!contains_any_of(nb_group, friendly_nonGroupTiles)){
                    friendly_nonGroupTiles.add(nb);
                    friendly_group_sum += nb_group.size();
                }
            }
        }
        if(!has_nonGroup_sameColor_neighbours(extension, original, player_color) && group_size < max_size){
            return true;
        }
        for(Tile nb : neighbours.getTiles()){
            int neighbouring_group_size = 0;
            Group neighbour_members = new Group();
            if(nb.getColour() == player_color && notin(nb, original)) {
                Group nb_group = collect_group(nb, player_color);
                if(!contains_any_of(neighbour_members, nb_group)){
                    neighbouring_group_size += nb_group.size();
                    neighbour_members.add(nb);
                }

                if (group_size + neighbouring_group_size < max_size - 1) {
                    return true;
                }
            }
            else if (friendly_nonGroupTiles.size() == 0){
                if(nb.getColour() == Color.WHITE){
                    if(group_size < max_size){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean contains_any_of(Group query, Group group){
        for(Tile tile : query.getTiles()){
            if(is_in(tile, group)){
                return true;
            }
        }
        return false;
    }
}
