package Bamboo.controller.nNet;

import Bamboo.controller.FilePath;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.AbstractLayer;
import deepnetts.util.Tensor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class NetworkManager
{
    public static void save(NeuralNetwork neuralNetwork) throws IOException {
        String folder = NetworkArchitecture.getFolder(neuralNetwork.getArchitecture());
        FeedForwardNetwork n = neuralNetwork.getNeuralNet();
        int layerID = 0;
        for(AbstractLayer layer : n.getLayers()){
            float[] biases = layer.getBiases();
            Tensor weights = layer.getWeights();
            if(weights != null)
                TensorSaver.save(folder+"weights_layer_"+layerID,weights);
            StringBuilder biasString = new StringBuilder();
            if(biases != null){
                for(float bias : biases){
                    biasString.append(bias).append("\n");
                }
            }
            String fileNameBias = folder+"biases_layer_"+layerID;
            TensorSaver.clear(fileNameBias);
            TensorSaver.write(fileNameBias, biasString.toString());
            layerID++;
        }
    }

    public static void fillNN(NeuralNetwork neuralNetwork) throws IOException {
        FeedForwardNetwork n = neuralNetwork.getNeuralNet();
        String folder = NetworkArchitecture.getFolder(neuralNetwork.getArchitecture());
        int layerID = 0;
        List<AbstractLayer> layers = n.getLayers();
        for(AbstractLayer layer : layers){
            if(layerID != 0){
                Tensor weights = TensorLoader.load(folder+"weights_layer_"+layerID);
                layer.setWeights(weights);
                BufferedReader reader = new BufferedReader(new FileReader(FilePath.getNNetPath(folder+"biases_layer_"+layerID)));
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
    }
}
