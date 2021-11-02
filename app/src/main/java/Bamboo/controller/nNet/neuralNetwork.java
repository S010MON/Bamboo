package Bamboo.controller.nNet;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.Game;
import deepnetts.data.DataSets;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;

import javax.visrec.ml.data.DataSet;
import java.awt.*;
import java.io.IOException;

public class neuralNetwork implements Agent
{

    @Override
    public String getName()
    {
        return "Neural Net";
    }

    @Override
    public String getType()
    {
        return "Neural Net";
    }

    @Override
    public boolean isHuman()
    {
        return false;
    }

    @Override
    public Vector getNextMove(Game game)
    {
        return null;
    }

    @Override
    public Color getColor()
    {
        return null;
    }

    public void train()
    {

        // Load data from CSV file
        int inputsNum = 91;
        int outputsNum = 91;

        DataSet trainingSet;
        try {
            trainingSet = DataSets.readCsv("data", inputsNum, outputsNum);

            // Create a feed forward neural network using builder
            FeedForwardNetwork neuralNet = FeedForwardNetwork.builder()
                    .addInputLayer(inputsNum)
                    .addFullyConnectedLayer(90, ActivationType.RELU)
                    .addOutputLayer(outputsNum, ActivationType.SIGMOID)
                    .lossFunction(LossType.CROSS_ENTROPY)
                    .build();

            // Train network
            neuralNet.train(trainingSet);

            // Save?
            // neuralNet.save("weights");
        }
        catch (IOException e) { e.printStackTrace();}

        // use the trained model (neural network) for prediction
        //neuralNet.setInput(someNewInput);
        //float[] prediction = neuralNet.getOutput();
    }
}
