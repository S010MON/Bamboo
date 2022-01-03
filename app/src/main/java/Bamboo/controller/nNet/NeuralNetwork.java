package Bamboo.controller.nNet;

import Bamboo.controller.*;
import Bamboo.model.GameWithGUI;
import Bamboo.model.GameWithoutGUI;
import Bamboo.model.Grid;
import deepnetts.data.DataSets;
import deepnetts.data.MLDataItem;
import deepnetts.data.TabularDataSet;
import deepnetts.eval.Evaluators;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.train.BackpropagationTrainer;
import deepnetts.net.train.opt.OptimizerType;
import deepnetts.util.FileIO;

import javax.visrec.ml.data.Column;
import javax.visrec.ml.data.DataSet;
import javax.visrec.ml.eval.EvaluationMetrics;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NeuralNetwork implements Agent
{

    private FeedForwardNetwork neuralNet;
    private NetworkArchitecture architecture;
    private Color color;
    private String nNetSaveName = "networkSave.json";
    private String nNetSavePath = "";

    public NeuralNetwork(Color color) throws IOException {
        this.color = color;
        this.architecture = NetworkArchitecture.BASIC;
        nNetSavePath = FilePath.getNNetPath("");
        nNetSavePath = nNetSavePath.concat(NetworkArchitecture.getFolder(architecture)).concat(nNetSaveName);
        try{
            this.neuralNet = (FeedForwardNetwork) FileIO.createFromJson(new File(nNetSavePath));
            NetworkManager.fillNN(this);
        }
        catch(IOException exception){
            exception.printStackTrace();
            this.train();
            NetworkManager.fillNN(this);
        }
    }

    public NeuralNetwork(FeedForwardNetwork n,NetworkArchitecture na){
        this.architecture = na;
        this.neuralNet = n;
    }

    @Override
    public String getName()
    {
        return "Nathan";
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
    public Vector getNextMove(GameWithGUI game)
    {
        Grid grid = game.getGrid();
        int[] input = DataManager.flatten(grid,color);
        float[] floatInputs = new float[input.length];
        for(int i = 0; i < input.length; i++)
            floatInputs[i] = (float)input[i];
        float[] output = neuralNet.predict(floatInputs);
        Vector move = getMoveFromPrediction(output,game.getGrid());
        if(grid.isLegalMove(move,color))
            System.out.println("NNet chooses move " + move + " at probability " + maximum(output));
        return move;
    }

    @Override
    public Vector getNextMove(GameWithoutGUI game)
    {
        Grid grid = game.getGrid();
        int[] input = DataManager.flatten(grid,color);
        float[] floatInputs = new float[input.length];
        for(int i = 0; i < input.length; i++)
            floatInputs[i] = (float)input[i];
        float[] output = neuralNet.predict(floatInputs);
        Vector move = getMoveFromPrediction(output,game.getGrid());
        if(grid.isLegalMove(move,color))
            System.out.println("NNet chooses move " + move + " at probability " + maximum(output));
        return move;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    public NetworkArchitecture getArchitecture(){return this.architecture;}

    public FeedForwardNetwork getNeuralNet(){return this.neuralNet;}

    public void train()
    {
        int inputsNum = 91;
        int outputsNum = 91;
        try {
            String filePath = FilePath.getNNetPath("data.csv");
            TabularDataSet<MLDataItem> data = DataSets.readCsv(filePath, inputsNum, outputsNum,true);
            data.shuffle();
            TabularDataSet[] split = trainTestSplit(data,0.3f,inputsNum,outputsNum);
            TabularDataSet trainData = split[0];
            TabularDataSet testData = split[1];
            int hiddenSize = 2 * (int) Math.round(Math.sqrt(inputsNum*outputsNum));
            if(neuralNet == null)
                buildNN(inputsNum,hiddenSize,outputsNum);
            optimizeTrainer(neuralNet,testData);
            neuralNet.train(trainData);
            printMetrics(trainData,testData);
            FileIO.writeToFileAsJson(neuralNet,nNetSavePath);
            NetworkManager.save(this);
        }
        catch (IOException e) { e.printStackTrace();}
    }

    void buildColumns(TabularDataSet dataSet, int numInputs, int numOutputs){
        ArrayList<Column> cols = new ArrayList<>();
        for(int in = 0; in < numInputs; in++){
            Column temp = new Column("i"+in,Column.Type.DECIMAL,false);
            cols.add(temp);
        }
        for(int out = 0; out < numOutputs; out++){
            Column temp = new Column("o"+out,Column.Type.BINARY,true);
            cols.add(temp);
        }
        dataSet.setColumns(cols);
    }

    Vector getMoveFromPrediction(float[] prediction,Grid grid){
        ArrayList<Vector> vectors = new ArrayList<Vector>(grid.getAllVectors());
        int iterator = 0;
        int bestID = 0;
        float bestPred = 0;
        for(float pred : prediction){
            if(pred > bestPred && grid.isLegalMove(vectors.get(iterator),color)){
                bestPred = pred;
                bestID = iterator;
            }
            iterator++;
        }
        return vectors.get(bestID);
    }

    float maximum(float[] array) {
        if (array.length <= 0)
            throw new IllegalArgumentException("The array is empty");
        float max = array[0];
        for (int i = 1; i < array.length; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    private void buildNN(int inputs, int hiddensize, int outputs){
        this.neuralNet = FeedForwardNetwork.builder()
                .addInputLayer(inputs)
                .addFullyConnectedLayer(hiddensize,ActivationType.RELU)
                .addFullyConnectedLayer(hiddensize,ActivationType.RELU)
                .addFullyConnectedLayer(hiddensize,ActivationType.RELU)
                .addOutputLayer(outputs, ActivationType.SOFTMAX)
                .lossFunction(LossType.CROSS_ENTROPY)
                .randomSeed(123)
                .build();
    }

    private TabularDataSet[] trainTestSplit(TabularDataSet dataSet,float testSize,int inputs, int outputs){
        DataSet<MLDataItem>[] dataSplit = dataSet.split(1.0-testSize,testSize);
        TabularDataSet<MLDataItem> trainData = (TabularDataSet) dataSplit[0];
        TabularDataSet<MLDataItem> testData = (TabularDataSet) dataSplit[1];
        buildColumns(trainData,inputs,outputs);
        buildColumns(testData,inputs,outputs);
        return new TabularDataSet[]{trainData,testData};
    }

    private void optimizeTrainer(FeedForwardNetwork n, TabularDataSet testDataSet){
        BackpropagationTrainer trainer = n.getTrainer();
        trainer.setOptimizer(OptimizerType.MOMENTUM);
        trainer.setShuffle(true);
        trainer.setTestSet(testDataSet);
        trainer.setMaxEpochs(500);
        trainer.setEarlyStopping(true);
        trainer.setEarlyStoppingPatience(10);
    }

    private void printMetrics(TabularDataSet trainData, TabularDataSet testData){
        System.out.println("---------TRAIN DATA--------");
        EvaluationMetrics em = Evaluators.evaluateClassifier(neuralNet,trainData);
        System.out.println(em);
        System.out.println("---------TEST DATA--------");
        EvaluationMetrics em2 = Evaluators.evaluateClassifier(neuralNet,testData);
        System.out.println(em2);
    }
}
