package Bamboo.controller;

import Bamboo.model.Grid;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class DataManager
{
    public static int[] flatten(Grid grid, Color currentPlayer)
    {
        ArrayList<Vector> V = (ArrayList<Vector>) enumerateTiles(grid.getSize());
        int[] encoding = new int[V.size()];
        for (int i = 0; i < encoding.length; i++)
        {
            Vector v = V.get(i);
            Color thisColour = grid.getTile(v).getColour();
            if(thisColour != Color.WHITE)
            {
                if(thisColour == currentPlayer)
                    encoding[i] = 1;
                else
                    encoding[i] = -1;
            }
            else
            {
                encoding[i] = 0;
            }
        }
        return encoding;
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

    public static String concatToCSV(int[] X, int[] Y)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < X.length; i++)
        {
            sb.append(X[i] + ",");
        }

        for(int j = 0; j < Y.length; j++)
        {
            sb.append(Y[j]);
            if(j < Y.length-1)
                sb.append(",");
            else
                sb.append("\n");
        }

        return sb.toString();
    }
}
