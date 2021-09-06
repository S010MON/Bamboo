package Bamboo.model;

import Bamboo.controller.Vector;

import java.util.ArrayList;
import java.util.List;

public class HexGridArrayImp implements HexGrid
{
    private Tile[][][] tiles;
    private int width;
    private int offset;

    public HexGridArrayImp(int radius)
    {
        width = (radius * 2) + 1;
        offset = radius;
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
    public void setTile(Vector v, Colour c)
    {
        v = addOffset(v);
        tiles[v.getX()][v.getY()][v.getZ()].setColour(c);
    }

    @Override
    public List<Tile> getAllNeighbours(Vector v)
    {
        v = addOffset(v);
        int x = v.getX();
        int y = v.getY();
        int z = v.getZ();

        List<Tile> list = new ArrayList<>();
        list.add(tiles[x+1][y][z]);
        list.add(tiles[x-1][y][z]);
        list.add(tiles[x][y+1][z]);
        list.add(tiles[x][y-1][z]);
        list.add(tiles[x][y][z+1]);
        list.add(tiles[x][y][z-1]);

        return list;
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
                        grid[x][y][z] = new Tile(Colour.NONE, removeOffset(new Vector(x, y, z)));
                }
            }
        }
        return grid;
    }
}