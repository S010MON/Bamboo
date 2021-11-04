package Bamboo.controller.nNet;

import Bamboo.controller.FilePath;
import deepnetts.net.FeedForwardNetwork;

import java.io.File;
import java.io.FileWriter;

public class TensorSaver
{
    //rows and columns of weight tensor
    public int rows(FeedForwardNetwork n, int layerID)
    {
        return n.getLayers().get(layerID).getWeights().getRows();
    }

    public int cols(FeedForwardNetwork n, int layerID)
    {
        return n.getLayers().get(layerID).getWeights().getCols();
    }
    public float[] weights(FeedForwardNetwork n, int layerID)
    {
        return n.getLayers().get(layerID).getWeights().getValues();
    }

    /**
     * @return an array of floats
     */
    public float[] biases(FeedForwardNetwork n, int layerID)
    {
        return n.getLayers().get(layerID).getBiases();
    }
    //If I get all of this per layer (layerID), I can construct tensors and set the weights and baises in loaded models
    //For the input layer, weights are of course null, so I dont know whether that is something you need to take into account



    private static void write(String fileName, String data)
    {
        try {
            String filePath = FilePath.getFilePath(fileName);

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
