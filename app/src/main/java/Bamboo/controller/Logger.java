package Bamboo.controller;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;

public class Logger
{
    public static void logMove(Vector v, Color c)
    {
        try {

            String filePath = FilePath.getFilePath("moves");

            File file = new File(filePath);
            if (!file.exists())
                file.createNewFile();

            FileWriter writer = new FileWriter(file,true);
            writer.write(v.toCSV() + "," + color_to_char(c) + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to save file");
        }
    }

    private static char color_to_char(Color c)
    {
        if(c.equals(Color.RED))
            return 'r';
        else if (c.equals(Color.BLUE))
            return 'b';
        else
            return 'w';
    }

    public static void logCSV(String fileName, String data)
    {
        try {

            String filePath = FilePath.getFilePath(fileName);
            File file = new File(filePath);

            if (!file.exists())
                file.createNewFile();

            FileWriter writer = new FileWriter(file,true);
            writer.write(data + "\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to write to CSV file");
        }
    }
}
