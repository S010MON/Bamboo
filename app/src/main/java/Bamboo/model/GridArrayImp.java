package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GridArrayImp implements Grid
{
    private Tile[][][] tiles;
    private List<Tile> tileList;
    private List<Vector> vectors;
    private int width;
    private int offset;

    public GridArrayImp(int radius)
    {
        width = (radius * 2) + 1;
        offset = radius;
        vectors = new ArrayList<>();
        tileList = new ArrayList<>();
        tiles = buildGrid(radius);
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
    }

    @Override
    public boolean isLegalMove(Vector vector, Color color)
    {
        return false;
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
    public List<Vector> getAllVectors()
    {
        return vectors;
    }

    public Vector addOffset(Vector v)
    {
        int x = v.getX() + offset;
        int y = v.getY() + offset;
        int z = v.getZ() + offset;
        return new Vector(x, y, z);
    }

    public Vector removeOffset(Vector v)
    {
        int x = v.getX() - offset;
        int y = v.getY() - offset;
        int z = v.getZ() - offset;
        return new Vector(x, y, z);
    }

    private Tile[][][] buildGrid(int radius)
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
}