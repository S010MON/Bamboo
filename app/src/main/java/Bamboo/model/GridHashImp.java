package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class GridHashImp implements Grid
{
    protected HashMap<Vector, Tile> tiles;
    protected HashMap<Vector, Boolean> remainingTiles;
    protected ArrayList<Vector> neighbours;
    protected int radius;

    public GridHashImp(int radius)
    {
        this.radius = radius;
        neighbours = buildNeighbourList();

        tiles = new HashMap<>();
        remainingTiles = new HashMap<>();
        for (int x = -radius; x <= radius; x++)
        {
            for (int y = -radius; y <= radius; y++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    if((x + y + z) == 0)
                    {
                        Vector v = new Vector(x, y, z);
                        tiles.put(v, new Tile(v));
                        remainingTiles.put(v, true);
                    }
                }
            }
        }
    }

    @Override
    public Tile getTile(Vector v)
    {
        return tiles.get(v);
    }

    @Override
    public void setTile(Vector v, Color c)
    {
        tiles.get(v).setColour(c);
        remainingTiles.replace(v, false);
    }

    @Override
    public boolean isLegalMove(Vector vector, Color color)
    {
        return false;
    }

    @Override
    public List<Tile> getAllNeighbours(Vector vector)
    {
        List<Tile> list = new ArrayList<>();
        for(Vector n: neighbours)
        {
            Vector neighbour = vector.add(n);
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
    public List<Vector> getAllVectors()
    {
        return tiles.keySet().stream().toList();
    }

    @Override
    public boolean isFinished(Color currentColour)
    {
        if(remainingTiles.isEmpty())
            return true;

        boolean finished = true;
        for(Vector v: tiles.keySet())
        {
            if(tiles.get(v).getColour() == Color.WHITE && isLegalMove(v, currentColour))
            {
                finished = false;
                break;
            }
        }
        return finished;
    }

    private boolean isInBounds(Vector v)
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

    private ArrayList<Vector> buildNeighbourList()
    {
        ArrayList<Vector> list = new ArrayList<>();
        list.add(new Vector( 0,-1, 1));
        list.add(new Vector( 0, 1,-1));
        list.add(new Vector( 1, 0,-1));
        list.add(new Vector(-1, 0, 1));
        list.add(new Vector( 1,-1, 0));
        list.add(new Vector(-1, 1, 0));
        return list;
    }
}
