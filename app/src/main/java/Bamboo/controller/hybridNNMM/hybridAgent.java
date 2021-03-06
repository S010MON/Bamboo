package Bamboo.controller.hybridNNMM;

import Bamboo.controller.Agent;
import Bamboo.controller.Mutable;
import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.miniMax.MiniMaxSortedAB;
import Bamboo.controller.nNet.NeuralNetwork;
import Bamboo.model.Game;
import Bamboo.model.Grid;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

public class hybridAgent implements Agent {
    private Mutable<Integer> switchThreshold = new Mutable<>(20);
    private NeuralNetwork nn;
    private MiniMaxSortedAB mm;
    private Color color;
    private ArrayList<Vector> uncolored_vectors = new ArrayList<>();
    private boolean testing = false;

    public hybridAgent(Color c) throws IOException {
        color = c;
        switchThreshold.set(20);
        mm = new MiniMaxSortedAB(c);
        nn = new NeuralNetwork(c);
    }

    @Override
    public String getName() {
        return "Gerald";
    }

    @Override
    public String getType() {
        return "Hybrid";
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public Vector getNextMove(Game game) {
        if(uncolored_vectors.size() == 0)
            uncolored_vectors = new ArrayList<>(game.getGrid().getAllVectors());
        else
            updateUncoloredVectors(game.getGrid());
        if(testing){
            if(uncolored_vectors.size() > (float)(Number) switchThreshold.get())
                return nn.getNextMove(game);
            else
                return mm.getNextMove(game);
        }
        else{
            if(uncolored_vectors.size() > switchThreshold.get())
                return nn.getNextMove(game);
            else
                return mm.getNextMove(game);
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Mutable<Integer> getDepth() {
        return mm.getDepth();
    }

    @Override
    public Mutable<Integer> getIterations() {
        return null;
    }

    @Override
    public Mutable<Float> getC() {
        return null;
    }

    @Override
    public Mutable<Heuristic> getHeuristic() {
        return null;
    }

    @Override
    public Mutable<Integer> getSwitchThreshold() {
        testing = true;
        return switchThreshold;
    }

    public void updateUncoloredVectors(Grid grid){
        uncolored_vectors.removeIf(vec -> grid.getTile(vec).getColour() != Color.WHITE);
    }
}
