package Bamboo.controller.nNet;

import Bamboo.controller.FileManager;
import Bamboo.controller.FilePath;
import Bamboo.controller.Logger;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.AbstractLayer;
import deepnetts.util.Tensor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NetworkManager
{
    public static void save(FeedForwardNetwork n, String filePath) throws IOException {
        System.out.println("Writing NN to file...");
        int layerID = 0;
        for(AbstractLayer layer : n.getLayers()){
            float[] biases = layer.getBiases();
            Tensor weights = layer.getWeights();
            if(weights != null)
                TensorSaver.save("weights_layer_"+layerID,weights);
            String biasString = "";
            if(biases != null){
                for(float bias : biases){
                    biasString += bias + "\n";
                }
            }
            TensorSaver.write("biases_layer_"+layerID,biasString);
            System.out.println("Done writing layer " + layerID + ".");
            layerID++;
        }
    }

    public static void fillNN(FeedForwardNetwork n) throws IOException {
        System.out.println("Filling...");
        int layerID = 0;
        List<AbstractLayer> layers = n.getLayers();
        for(AbstractLayer layer : layers){
            if(layerID != 0){
                System.out.println("Layer "+layerID);
                Tensor weights = TensorLoader.load("weights_layer_"+layerID);
                layer.setWeights(weights);
                BufferedReader reader = new BufferedReader(new FileReader(FilePath.getNNetPath("biases_layer_"+layerID)));
                float[] biases = new float[layer.getBiases().length];
                for(int i = 0; i < biases.length; i++)
                {
                    biases[i] = Float.parseFloat(reader.readLine());
                }
                reader.close();
                layer.setBiases(biases);
            }
            layerID++;
        }
        System.out.println("Done filling!");
    }
}
