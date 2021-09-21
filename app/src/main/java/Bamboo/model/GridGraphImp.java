package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class GridGraphImp extends GridHashImp implements Grid
{
    public GridGraphImp(int radius)
    {
        super(radius);
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
    }

    @Override
    public boolean isLegalMove(Vector vector, Color color)
    {
        setTile(vector, color);
        ArrayList<ArrayList<Vector>> groups = getAllGroupsOfColour(color);
        setTile(vector, Color.WHITE);
        int noOfGroups = Math.max(groups.size(), 1);
        int maxGroup = getMaxGroupSize(groups);
        return maxGroup <= noOfGroups;
    }

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

    private int getMaxGroupSize(ArrayList<ArrayList<Vector>> groups)
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

