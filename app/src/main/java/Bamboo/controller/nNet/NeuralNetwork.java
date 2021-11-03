package Bamboo.controller.nNet;

import Bamboo.controller.Agent;
import Bamboo.controller.DataManager;
import Bamboo.controller.Vector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.GridGraphImp;
import deepnetts.data.DataSets;
import deepnetts.data.TabularDataSet;
import deepnetts.eval.Evaluators;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.train.BackpropagationTrainer;
import deepnetts.util.FileIO;

import javax.visrec.ml.data.Column;
import javax.visrec.ml.eval.EvaluationMetrics;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NeuralNetwork implements Agent
{

    private FeedForwardNetwork neuralNet;
    private Color color;
    private String nNetSavePath = "networkSave.json";

    public NeuralNetwork(Color color) throws IOException {
        this.color = color;
        try{
            this.neuralNet = (FeedForwardNetwork) FileIO.createFromJson(new File(nNetSavePath));
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
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
    public Vector getNextMove(Game game)
    {
        Grid grid = game.getGrid();
        int[] input = DataManager.flatten(grid,color);
        float[] floatInputs = new float[input.length];
        for(int i = 0; i < input.length; i++)
            floatInputs[i] = (float)input[i];
        float[] output = neuralNet.predict(floatInputs);
        Vector move = getMoveFromPrediction(output,game);
        if(grid.isLegalMove(move,color))
            System.out.println("NNet chooses move " + move);
        else
            System.out.println("At " + move + " NNet tried to break rules");
        return move;
    }

    @Override
    public Color getColor()
    {
        return color;
    }

    public static void main(String[] args) throws IOException {
        NeuralNetwork neuralNetwork = new NeuralNetwork(Color.RED);
        //float [] predictions = neuralNetwork.neuralNet.predict(new float[]{-1,1,-1,1,-1,-1,1,-1,1,0,-1,1,1,-1,1,-1,0,0,0,-1,-1,1,0,-1,1,-1,1,-1,1,-1,1,-1,1,0,1,0,1,1,-1,1,-1,0,1,-1,-1,1,-1,1,0,0,-1,1,-1,0,1,1,1,0,-1,0,1,-1,1,-1,0,-1,1,0,1,-1,-1,0,-1,1,0,-1,1,1,1,-1,0,-1,1,0,-1,-1,1,-1,1,-1,1});
        //System.out.println(neuralNetwork.getMoveFromPrediction(predictions));
        neuralNetwork.train();
    }

    public void train()
    {

        // Load data.csv from CSV file
        int inputsNum = 91;
        int outputsNum = 91;

        System.out.println("Test");

        //DataSet<MLDataItem> trainingSet;
        try {

            String filePath = "C:\\Users\\Alex\\IdeaProjects\\Bamboo\\app\\src\\main\\java\\saved\\data.csv";
            filePath = "C:\\Users\\Alex\\IdeaProjects\\Bamboo\\app\\src\\main\\java\\Bamboo\\Controller\\nNet\\data.csv";
            TabularDataSet trainingSet = DataSets.readCsv(filePath, inputsNum, outputsNum,true);
            buildColumns(trainingSet,91,91);
            neuralNet = FeedForwardNetwork.builder()
                    .addInputLayer(inputsNum)
                    .addFullyConnectedLayer(120,ActivationType.RELU)
                    .addFullyConnectedLayer(120,ActivationType.RELU)
                    .addOutputLayer(outputsNum, ActivationType.SOFTMAX)
                    .lossFunction(LossType.CROSS_ENTROPY)
                    .randomSeed(123)
                    .build();


            BackpropagationTrainer trainer = neuralNet.getTrainer();
            trainer.setMaxEpochs(500);
            neuralNet.train(trainingSet);

            EvaluationMetrics em = Evaluators.evaluateClassifier(neuralNet, trainingSet);
            System.out.println(em);

            FileIO.writeToFileAsJson(neuralNet,"networkSave.json");

            float [] predictions = neuralNet.predict(new float[]{-1,1,-1,1,-1,-1,1,-1,1,0,-1,1,1,-1,1,-1,0,0,0,-1,-1,1,0,-1,1,-1,1,-1,1,-1,1,-1,1,0,1,0,1,1,-1,1,-1,0,1,-1,-1,1,-1,1,0,0,-1,1,-1,0,1,1,1,0,-1,0,1,-1,1,-1,0,-1,1,0,1,-1,-1,0,-1,1,0,-1,1,1,1,-1,0,-1,1,0,-1,-1,1,-1,1,-1,1});
            for(float pred : predictions)
                System.out.println(pred);

            //FeedForwardNetwork newNet = FileIO.createFromFile("networkSave.dnet");

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

    Vector getMoveFromPrediction(float[] prediction){
        Grid grid = new GridGraphImp(5);
        ArrayList<Vector> vectors = new ArrayList<Vector>(grid.getAllVectors());
        int iterator = 0;
        int bestID = 0;
        float bestPred = 0;
        for(float pred : prediction){
            if(pred > bestPred){
                bestPred = pred;
                bestID = iterator;
            }
            iterator++;
        }
        return vectors.get(bestID);
    }

    Vector getMoveFromPrediction(float[] prediction,Game game){
        Grid grid = game.getGrid();
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
}
