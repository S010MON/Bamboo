package Bamboo;

import Bamboo.controller.nNet.NetworkManager;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NetworkManagerTest {
    @Test void fillTest() throws IOException {
        //C:\Users\Alex\IdeaProjects\Bamboo\app\src\main\java\Bamboo\controller\nNet\TrainingData\biases_layer_0
        FeedForwardNetwork n = new FeedForwardNetwork.Builder()
                .addInputLayer(3)
                .addFullyConnectedLayer(50, ActivationType.RELU)
                .addOutputLayer(1,ActivationType.SIGMOID)
                .randomSeed(111)
                .build();

        float[] inputs = {3,4,1};
        float prediction = n.predict(inputs)[0];
        NetworkManager.save(n);
        NetworkManager.fillNN(n);

        FeedForwardNetwork n2 = new FeedForwardNetwork.Builder()
                .addInputLayer(3)
                .addFullyConnectedLayer(50, ActivationType.RELU)
                .addOutputLayer(1,ActivationType.SIGMOID)
                .randomSeed(999)
                .build();
        NetworkManager.fillNN(n2);
        float prediction2 = n2.predict(inputs)[0];
        assertEquals(prediction,prediction2);
    }
}
