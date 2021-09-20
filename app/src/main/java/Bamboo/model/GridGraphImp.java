package Bamboo.model;

import Bamboo.controller.CubeVector;

import java.awt.Color;
import java.util.*;

public class GridGraphImp implements Grid
{
    private HashMap<CubeVector, Tile> tiles;
    private ArrayList<CubeVector> neighbours;
    private int radius;

    public GridGraphImp(int radius)
    {
        this.radius = radius;
        this.neighbours = buildNeighbourList();

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
        for(Tile neighbour: getAllNeighbours(v))
        {
            if(neighbour.getColour() == c)
            {
                tiles.get(v).addNeighbour(neighbour);
                neighbour.addNeighbour(tiles.get(v));
            }
        }
        tiles.get(v).setColour(c);
    }

    @Override
    public List<Tile> getAllNeighbours(CubeVector v)
    {
        List<Tile> list = new ArrayList<>();
        for(CubeVector n: neighbours)
        {
            CubeVector neighbour = v.add(n);
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

    public ArrayList<ArrayList<CubeVector>> getAllGroupsOfColour(Color color)
    {
        HashMap<CubeVector, Boolean> visited = new HashMap<>();
        ArrayList<ArrayList<CubeVector>> groups = new ArrayList<>();
        for(CubeVector vector: getAllVectors())
        {
            if(tiles.get(vector).getColour() == color && !visited.containsKey(vector)) // If it is the colour we are looking for and unvisited
            {
                ArrayList<CubeVector> currentGroup = getGroup(vector);
                for(CubeVector v: currentGroup)
                {
                    visited.put(v, Boolean.TRUE);
                }
                groups.add(currentGroup);
            }
        }
        return groups;
    }

    public ArrayList<CubeVector> getGroup(CubeVector vector)
    {
        ArrayList<CubeVector> group = new ArrayList<>();
        HashMap<CubeVector, Boolean> visited = new HashMap<>();

        Stack<CubeVector> stack = new Stack<>();
        stack.push(tiles.get(vector).getVector());
        while (!stack.isEmpty())
        {
            CubeVector currentNode = stack.pop();

            if(!visited.containsKey(currentNode))  // Test if we have previously visited the vertex in the group
            {
                visited.put(currentNode, Boolean.TRUE);
                group.add(currentNode);
            }
            for (Tile t : tiles.get(currentNode).getGroupNeighbours())
            {
                if(!visited.containsKey(t.getVector()))
                {
                    stack.push(t.getVector());
                    group.add(t.getVector());
                    visited.put(t.getVector(), Boolean.TRUE);
                }
            }
        }
        return group;
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

