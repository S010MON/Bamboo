package Bamboo.controller.nNet;

import deepnetts.net.FeedForwardNetwork;

public class TensorSaver {


    //rows and columns of weight tensor
    int rows(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getWeights().getRows();}
    int cols(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getWeights().getCols();}
    float[] weights(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getWeights().getValues();}
    //bias tensor has just one row, so no rows and columns needed
    float[] biases(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getBiases();}
    //If I get all of this per layer (layerID), I can construct tensors and set the weights and baises in loaded models
    //For the input layer, weights are of course null, so I dont know whether that is something you need to take into account
}
