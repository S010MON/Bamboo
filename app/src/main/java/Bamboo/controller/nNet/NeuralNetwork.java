package Bamboo.controller.nNet;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.Game;
import deepnetts.data.DataSets;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.train.BackpropagationTrainer;
import deepnetts.util.FileIO;

import javax.visrec.ml.data.DataSet;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;

public class NeuralNetwork implements Agent
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

    public static void main(String[] args)
    {
        NeuralNetwork neuralNetwork = new NeuralNetwork();
        neuralNetwork.train();
    }

    public void train()
    {

        // Load data.csv from CSV file
        int inputsNum = 91;
        int outputsNum = 91;

        System.out.println("Test");

        DataSet trainingSet;
        try {

            String filePath = "/home/leon/IdeaProjects/Bamboo/app/src/main/java/Bamboo/controller/nNet/data.csv";
            trainingSet = DataSets.readCsv(filePath, inputsNum, outputsNum);

            // Create a feed forward neural network using builder
            FeedForwardNetwork neuralNet = FeedForwardNetwork.builder()
                    .addInputLayer(inputsNum)
                    .addFullyConnectedLayer(180, ActivationType.RELU)
                    .addOutputLayer(outputsNum, ActivationType.SIGMOID)
                    .lossFunction(LossType.CROSS_ENTROPY)
                    .build();

            System.out.println("TRAINING CONFIGURATIONS.");
            neuralNet.setLabel("TRAINING DATA");
            BackpropagationTrainer trainer = neuralNet.getTrainer();
            trainer.setTrainingSnapshots(true);

            // Train network
            neuralNet.train(trainingSet);

            // Save?
            //String neuralNetFile = "neuralNetwork_" + LocalDateTime.now().toString() + ".dnet";
            //FileIO.writeToFile(neuralNet, neuralNetFile);
        }
        catch (IOException e) { e.printStackTrace();}

        // use the trained model (neural network) for prediction
        //neuralNet.setInput(someNewInput);
        //float[] prediction = neuralNet.getOutput();
    }
}
