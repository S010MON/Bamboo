package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GridHashImp implements Grid
{
    private HashMap<CubeVector, Tile> tiles;
    private ArrayList<CubeVector> neighbours;
    private int radius;

    public GridHashImp(int radius)
    {
        this.radius = radius;
        neighbours = buildNeighbourList();

        tiles = new HashMap<>();
        for (int x = -radius; x <= radius; x++)
        {
            for (int y = -radius; y <= radius; y++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    if((x + y + z) == 0)
                    {
                        CubeVector v = new CubeVector(x, y, z);
                        tiles.put(v, new Tile(v));
                    }
                }
            }
        }
    }

    @Override
    public Tile getTile(CubeVector v)
    {
        return tiles.get(v);
    }

    @Override
    public void setTile(CubeVector v, Color c) throws TileAlreadyColouredException
    {
        tiles.get(v).setColour(c);
    }

    @Override
    public List<Tile> getAllNeighbours(CubeVector vector)
    {
        List<Tile> list = new ArrayList<>();
        for(CubeVector n: neighbours)
        {
            CubeVector neighbour = vector.add(n);
            if(isInBounds(neighbour))
                list.add(tiles.get(neighbour));
        }
        return list;
    }

    @Override
    public List<Tile> getAllTiles()
    {
        return Collections.list(Collections.enumeration(tiles.values()));
    }

    @Override
    public List<CubeVector> getAllVectors()
    {
        return tiles.keySet().stream().toList();
    }

    private boolean isInBounds(CubeVector v)
    {
        boolean inBounds = true;
        if(v.getX() < -radius || radius < v.getX())
            inBounds = false;
        if(v.getY() < -radius || radius < v.getY())
            inBounds = false;
        if(v.getZ() < -radius || radius < v.getZ())
            inBounds = false;
        return inBounds;
    }

    private ArrayList<CubeVector> buildNeighbourList()
    {
        ArrayList<CubeVector> list = new ArrayList<>();
        list.add(new CubeVector( 0,-1, 1));
        list.add(new CubeVector( 0, 1,-1));
        list.add(new CubeVector( 1, 0,-1));
        list.add(new CubeVector(-1, 0, 1));
        list.add(new CubeVector( 1,-1, 0));
        list.add(new CubeVector(-1, 1, 0));
        return list;
    }
}
