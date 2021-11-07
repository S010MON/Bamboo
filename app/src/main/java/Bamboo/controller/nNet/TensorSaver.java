package Bamboo.controller.nNet;

import Bamboo.controller.FilePath;
import deepnetts.util.Tensor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TensorSaver
{
    public static void save(String fileName, Tensor tensor) throws IOException
    {
        Integer col = tensor.getCols();
        Integer row = tensor.getRows();
        write(fileName, col.toString());
        write(fileName, row.toString());

        for(int r = 0; r < row; r++)
        {
            for(int c = 0; c < col; c++)
            {
                write(fileName, Float.toString(tensor.get(r,c)));
            }
        }
    }

    private static void write(String fileName, String data)
    {
        try {
            String filePath = FilePath.getNNetPath(fileName);

            File file = new File(filePath);
            if (!file.exists())
                file.createNewFile();

            FileWriter writer = new FileWriter(file,true);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to save file");
        }
    }
}
