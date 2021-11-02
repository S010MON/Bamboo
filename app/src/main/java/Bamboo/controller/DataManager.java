package Bamboo.controller;

import Bamboo.model.Grid;

import java.util.List;
import java.util.ArrayList;

public class DataManager
{
    public static String flatten(Grid grid)
    {
        return null;
    }

    public static int[] oneHotEncode(int grid_size, Vector vector)
    {
        ArrayList<Vector> V = (ArrayList<Vector>) enumerateTiles(grid_size);
        int[] encoding = new int[V.size()];
        for(int i = 0; i < V.size(); i++)
        {
            if(V.get(i).equals(vector))
                encoding[i] = 1;
            else
                encoding[i] = 0;
        }
        return encoding;
    }

    public static List<Vector> enumerateTiles(int grid_size)
    {
        ArrayList<Vector> V = new ArrayList<>();
        if(grid_size == 0)
            return V;

        int n = Math.abs(grid_size);

        for (int x = -n; x <= n; x++)
        {
            for (int y = -n; y <= n; y++)
            {
                for (int z = -n; z <= n; z++)
                {
                    if (x+y+z == 0)
                        V.add(new Vector(x,y,z));
                }
            }
        }
        return V;
    }
}
