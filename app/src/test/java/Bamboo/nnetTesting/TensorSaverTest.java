package Bamboo.nnetTesting;

import Bamboo.controller.nNet.TensorLoader;
import Bamboo.controller.nNet.TensorSaver;
import deepnetts.util.Tensor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TensorSaverTest
{
    @Test void testSave()
    {
        float[][] t = {
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
                {0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f},
        };

        Tensor exp = new Tensor(t);
        Tensor act = null;
        try{
            TensorSaver.clear("test/testBiases");
            TensorSaver.save("test/testBiases", exp);
        } catch (IOException e)
        {
            System.out.println("Failed to save tensor");
            e.printStackTrace();
        }
    }
}
