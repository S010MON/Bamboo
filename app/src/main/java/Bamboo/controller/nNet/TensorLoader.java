package Bamboo.controller.nNet;

import Bamboo.controller.FilePath;
import deepnetts.util.Tensor;

import java.io.*;

public class TensorLoader
{
    public static Tensor load(String fileName) throws IOException
    {
        String path = FilePath.getNNetPath(fileName);
        File file = new File(path);
        return new Tensor(readTensor(file));
    }

    private static int getCols(File file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int cols = Integer.parseInt(reader.readLine());
        int rows = Integer.parseInt(reader.readLine());
        reader.close();
        return cols;
    }

    private static int getRows(File file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int cols = Integer.parseInt(reader.readLine());
        int rows = Integer.parseInt(reader.readLine());
        reader.close();
        return rows;
    }

    private static float[][] readTensor(File file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int cols = Integer.parseInt(reader.readLine());
        int rows = Integer.parseInt(reader.readLine());
        float[][] tensor = new float[cols][rows];

        String[] data = new String[rows];
        for(int i = 0; i < data.length; i++)
        {
            data[i] = reader.readLine();
        }
        reader.close();

        for(int i = 0; i < data.length; i++)
        {
            String[] subString = data[i].split(",");
            for(int j = 0; j < subString.length; j++)
            {
                tensor[i][j] = Float.parseFloat(subString[j]);
            }
        }
        return tensor;
    }
}
