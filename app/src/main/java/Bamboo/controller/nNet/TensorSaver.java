package Bamboo.controller.nNet;

import deepnetts.net.FeedForwardNetwork;

public class TensorSaver {



    int rows(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getWeights().getRows();}
    int cols(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getWeights().getCols();}
    float[] weights(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getWeights().getValues();}
    float[] biases(FeedForwardNetwork n, int layerID){return n.getLayers().get(layerID).getBiases();}

}
