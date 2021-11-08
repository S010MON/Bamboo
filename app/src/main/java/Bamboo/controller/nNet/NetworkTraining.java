package Bamboo.controller.nNet;

import deepnetts.util.Tensor;

import java.awt.*;
import java.io.IOException;

public class NetworkTraining
{
    public static void main(String[] args) throws IOException {
        NeuralNetwork neuralNetwork = new NeuralNetwork(Color.RED);
        neuralNetwork.train();
    }
}
