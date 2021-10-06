package Bamboo;

import Bamboo.controller.FilePath;
import Bamboo.controller.Vector;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class MovesLists
{
    public static HashMap<Vector, Color> getMoves()
    {
        HashMap<Vector, Color> moves = new HashMap<>();

        try {
            File file = new File(FilePath.getFilePath("moves"));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();

            do{
                System.out.println(line);
                String[] subString = line.split(",");
                int x = Integer.parseInt(subString[0]);
                int y = Integer.parseInt(subString[1]);
                int z = Integer.parseInt(subString[2]);
                Color color = parseColour(subString[3]);
                moves.put(new Vector(x, y, z), color);
                line = reader.readLine();
            } while(line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moves;
    }

    private static Color parseColour(String colour)
    {
        if(colour.equalsIgnoreCase("R"))
            return Color.RED;
        else if (colour.equalsIgnoreCase("B"))
            return Color.BLUE;
        else
            return Color.WHITE;
    }
}
