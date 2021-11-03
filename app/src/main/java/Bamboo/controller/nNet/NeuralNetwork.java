package Bamboo.controller.nNet;

import Bamboo.controller.Agent;
import Bamboo.controller.Vector;
import Bamboo.model.Game;
import deepnetts.data.DataSets;
import deepnetts.data.MLDataItem;
import deepnetts.data.TabularDataSet;
import deepnetts.eval.Evaluators;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.train.BackpropagationTrainer;
import deepnetts.util.FileIO;

import javax.visrec.ml.data.BasicDataSet;
import javax.visrec.ml.data.Column;
import javax.visrec.ml.data.DataSet;
import javax.visrec.ml.eval.EvaluationMetrics;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
        int inputsNum = 4;
        int outputsNum = 3;

        System.out.println("Test");

        //DataSet<MLDataItem> trainingSet;
        try {

            String filePath = "C:\\Users\\Alex\\IdeaProjects\\Bamboo\\app\\src\\main\\java\\saved\\data.csv";
            TabularDataSet trainingSet = DataSets.readCsv(filePath, inputsNum, outputsNum,true);
            trainingSet.setColumnNames(new String[]{"a","b","c","d","o1","o2","o3"});
            Column c1 = new Column("a", Column.Type.DECIMAL,false);
            Column c2 = new Column("b", Column.Type.DECIMAL,false);
            Column c3 = new Column("c", Column.Type.DECIMAL,false);
            Column c4 = new Column("d", Column.Type.DECIMAL,false);

            Column o1 = new Column("o1", Column.Type.BINARY,true);
            Column o2 = new Column("o2", Column.Type.BINARY,true);
            Column o3 = new Column("o3", Column.Type.BINARY,true);
            ArrayList cols = new ArrayList<Column>();
            cols.add(c1);
            cols.add(c2);
            cols.add(c3);
            cols.add(c4);
            cols.add(o1);
            cols.add(o2);
            cols.add(o3);
            trainingSet.setColumns(cols);
            System.out.println(trainingSet.getItems());
            System.out.println(trainingSet.getColumns());
            trainingSet.setAsTargetColumns(new int[]{4, 5, 6});
            // Create an instance of the Feed Forward Neural Network using builder.
            // To understand structure and components of the neural network see http://www.deepnetts.com/blog/terms#feed-forward-net
            FeedForwardNetwork neuralNet = FeedForwardNetwork.builder()
                    .addInputLayer(inputsNum)
                    .addFullyConnectedLayer(8,ActivationType.RELU)
                    .addOutputLayer(outputsNum, ActivationType.SOFTMAX)
                    .lossFunction(LossType.CROSS_ENTROPY)
                    .randomSeed(123)
                    .build();

            // TRAIN: Start training. To understand training output see link
            neuralNet.train(trainingSet);

            // TEST: test network /  evaluate classifier.
            // To understand classifier evaluation see http://www.deepnetts.com/blog/terms#evaluation
            EvaluationMetrics em = Evaluators.evaluateClassifier(neuralNet, trainingSet);
            System.out.println(em);

            FileIO.writeToFile(neuralNet,"networkSave.dnet");

            //FeedForwardNetwork newNet = FileIO.createFromFile("networkSave.dnet");

        }
        catch (IOException e) { e.printStackTrace();}

        // use the trained model (neural network) for prediction
        //neuralNet.setInput(someNewInput);
        //float[] prediction = neuralNet.getOutput();
    }
}
