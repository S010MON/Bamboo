package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.*;

public class GridGraphImp implements Grid
{
    protected HashMap<Vector, Tile> tiles;
    protected HashMap<Vector, Boolean> remainingTiles;
    protected ArrayList<Vector> neighbours;
    protected int radius;

    public GridGraphImp(int radius)
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
        for(Tile neighbour: getAllNeighbours(v))
        {
            if(neighbour.getColour() == c)
            {
                tiles.get(v).addNeighbour(neighbour);
                neighbour.addNeighbour(tiles.get(v));
            }

            if(c.equals(Color.WHITE))
            {
                tiles.get(v).removeNeighbour(neighbour);
                neighbour.removeNeighbour(tiles.get(v));
            }
        }
        tiles.get(v).setColour(c);
        remainingTiles.remove(v);
    }

    @Override
    public boolean isLegalMove(Vector vector, Color color)
    {
        if(tiles.get(vector).getColour() != Color.WHITE)
            return false;
        setTile(vector, color);
        ArrayList<ArrayList<Vector>> groups = getAllGroupsOfColour(color);
        setTile(vector, Color.WHITE);
        int noOfGroups = Math.max(groups.size(), 1);
        int maxGroup = getLargestSize(groups);
        return maxGroup <= noOfGroups;
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
    public ArrayList<ArrayList<Vector>> getAllGroupsOfColour(Color color)
    {
        HashMap<Vector, Boolean> visited = new HashMap<>();
        ArrayList<ArrayList<Vector>> groups = new ArrayList<>();
        for(Vector vector: getAllVectors())
        {   // If it is the colour we are looking for and is unvisited
            if(tiles.get(vector).getColour() == color && !visited.containsKey(vector))
            {
                ArrayList<Vector> currentGroup = getGroup(vector);
                for(Vector v: currentGroup)
                {
                    visited.put(v, Boolean.TRUE);
                }
                groups.add(currentGroup);
            }
        }
        return groups;
    }

    @Override
    public ArrayList<Vector> getGroup(Vector vector)
    {
        ArrayList<Vector> group = new ArrayList<>();
        HashMap<Vector, Boolean> visited = new HashMap<>();

        Stack<Vector> stack = new Stack<>();
        stack.push(tiles.get(vector).getVector());
        while (!stack.isEmpty())
        {
            Vector currentNode = stack.pop();

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
        ArrayList<ArrayList<Vector>> groups = getAllGroupsOfColour(color);
        int group_count = groups.size();
        int value = group_count * group_count;
        for(ArrayList<Vector> group : groups){
            value -= group.size();
        }
        return value;
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
}

