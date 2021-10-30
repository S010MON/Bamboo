package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GridArrayImp implements Grid
{
    private Tile[][][] tiles;
    private List<Tile> tileList;
    private List<Tile> emptyList;
    private List<Vector> vectors;
    private int width;
    private int offset;

    public GridArrayImp(int radius)
    {
        width = (radius * 2) + 1;
        offset = radius;
        vectors = new ArrayList<>();
        tileList = new ArrayList<>();
        emptyList = new ArrayList<>();
        tiles = buildGrid();
    }

    @Override
    public Tile getTile(Vector v)
    {
        if(v.getX() + v.getY() + v.getZ() != 0)
            return null;
        v = addOffset(v);
        return tiles[v.getX()][v.getY()][v.getZ()];
    }

    @Override
    public void setTile(Vector v, Color c)
    {
        v = addOffset(v);
        tiles[v.getX()][v.getY()][v.getZ()].setColour(c);
        emptyList.remove(tiles[v.getX()][v.getY()][v.getZ()]);
    }

    private void unSetTile(Vector v)
    {
        v = addOffset(v);
        tiles[v.getX()][v.getY()][v.getZ()].setColour(Color.WHITE);
        emptyList.add(tiles[v.getX()][v.getY()][v.getZ()]);
    }

    @Override
    public List<Tile> getAllNeighbours(Vector v)
    {
        v = addOffset(v);
        int x = v.getX();
        int y = v.getY();
        int z = v.getZ();

        List<Tile> list = new ArrayList<>();
        if(isInBounds(x, y-1, z+1))
            list.add(tiles[x][y-1][z+1]);

        if(isInBounds(x, y+1, z-1))
            list.add(tiles[x][y+1][z-1]);

        if(isInBounds(x+1, y, z-1))
            list.add(tiles[x+1][y][z-1]);

        if(isInBounds(x-1, y, z+1))
            list.add(tiles[x-1][y][z+1]);

        if(isInBounds(x+1, y-1, z))
            list.add(tiles[x+1][y-1][z]);

        if(isInBounds(x-1, y+1, z))
            list.add(tiles[x-1][y+1][z]);

        return list;
    }

    @Override
    public List<Tile> getAllTiles()
    {
        return tileList;
    }

    @Override
    public List<Vector> getAllRemainingMoves() {
        List<Vector> list = new ArrayList<>();
        for(Tile t: emptyList)
        {
            list.add(t.getVector());
        }
        return list;
    }

    @Override
    public List<Vector> getAllVectors()
    {
        return vectors;
    }

    @Override
    public boolean isFinished(Color currentColour)
    {
        if(emptyList.size() == 0)
            return true;
        for(int i = 0; i < emptyList.size(); i++)
        {
            Tile tile = emptyList.get(i);
            if(isLegalMove(tile.getVector(), currentColour))
                return false;
        }
        return true;
    }

    @Override
    public boolean isLegalMove(Vector vector, Color color)
    {
        if(getTile(vector).getColour() != Color.WHITE)
            return false;
        setTile(vector, color);
        ArrayList<ArrayList<Vector>> groups = getAllGroupsOfColour(color);
        unSetTile(vector);
        int noOfGroups = Math.max(groups.size(), 1);
        int maxGroup = getLargestSize(groups);
        return maxGroup <= noOfGroups;
    }

    @Override
    public ArrayList<ArrayList<Vector>> getAllGroupsOfColour(Color color){
        List<Tile> tiles = this.getAllTiles();
        ArrayList<ArrayList<Vector>> groups = new ArrayList<>();
        ArrayList<Vector> collected_tiles = new ArrayList<>();
        for(Tile tile : tiles){
            if(tile.getColour() == color){
                if(!collected_tiles.contains(tile.getVector())){
                    ArrayList<Vector> current_group = this.getGroup(tile.getVector());
                    collected_tiles.addAll(current_group);
                    groups.add(current_group);
                }
            }
        }
        return groups;
    }

    @Override
    public ArrayList<Vector> getGroup(Vector vector)
    {
        ArrayList<Vector> group = new ArrayList<>();
        group.add(vector);
        int current_member_id = -1;
        ArrayList<Vector> visited_tiles = new ArrayList<>();
        List<Tile> neighbours;
        Vector current_tile = vector;
        while(visited_tiles.size() < group.size()){
            current_member_id ++;
            current_tile = group.get(current_member_id);
            neighbours = this.getAllNeighbours(current_tile);
            for(Tile nb : neighbours){
                if(nb.getColour() == this.getTile(vector).getColour()){
                    if(!group.contains(nb.getVector())){
                        group.add(nb.getVector());
                    }
                }
            }
            visited_tiles.add(current_tile);
        }
        return group;
    }

    @Override
    public int getMaxGroupSize(Color colour)
    {
        int maxSize = 0;
        for(ArrayList<Vector> group: getAllGroupsOfColour(colour))
        {
            if(group.size() > maxSize)
                maxSize = group.size();
        }
        return maxSize;
    }

    @Override
    public int evaluateGame(Color color){
        if(isFinished(Color.RED))
            return -1000000;
        if(isFinished(Color.BLUE))
            return 1000000;
        return evaluateGameForColor(Color.RED) - evaluateGameForColor(Color.BLUE);
    }

    @Override
    public Grid copy(){
        int radius = (this.width - 1)/2;
        Grid temp = new GridArrayImp(radius);
        for(Tile tile : this.getAllTiles()){
            temp.setTile(tile.getVector(), tile.getColour());
        }
        return temp;
    }

    int evaluateGameForColor(Color color){
        ArrayList<ArrayList<Vector>> groups = getAllGroupsOfColour(color);
        int group_count = groups.size();
        int value = group_count * group_count;
        for(ArrayList<Vector> group : groups){
            value -= group.size();
        }
        return value;
    }

    private Vector addOffset(Vector v)
    {
        int x = v.getX() + offset;
        int y = v.getY() + offset;
        int z = v.getZ() + offset;
        return new Vector(x, y, z);
    }

    private Vector removeOffset(Vector v)
    {
        int x = v.getX() - offset;
        int y = v.getY() - offset;
        int z = v.getZ() - offset;
        return new Vector(x, y, z);
    }

    private Tile[][][] buildGrid()
    {
        Tile[][][] grid = new Tile[width][width][width];

        for (int x = 0; x < width; x++)
        {
            for (int y = 0; y < width; y++)
            {
                for (int z = 0; z < width; z++)
                {
                    // Add a new tile without the offset
                    if(((x-offset) + (y-offset) + (z-offset)) == 0)
                    {
                        vectors.add(new Vector((x-offset), (y-offset), (z-offset)));
                        grid[x][y][z] = new Tile(removeOffset(new Vector(x, y, z)));
                        tileList.add(grid[x][y][z]);
                        emptyList.add(grid[x][y][z]);
                    }
                }
            }
        }
        return grid;
    }

    private boolean isInBounds(int x, int y, int z)
    {
        boolean inBounds = true;
        if(x < 0 || x >= width)
            inBounds = false;
        if(y < 0 || y >= width)
            inBounds = false;
        if(z < 0 || z >= width)
            inBounds = false;
        return inBounds;
    }

    private int getLargestSize(ArrayList<ArrayList<Vector>> groups)
    {
        int max = 1;
        for (ArrayList<Vector> group : groups)
        {
            if (group.size() > max)
                max = group.size();
        }
        return max;
    }
}