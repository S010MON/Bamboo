package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Human;
import Bamboo.controller.Settings;
import Bamboo.controller.CubeVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Game
{
    private Grid grid;
    private Agent player1;
    private Agent player2;
    private Agent currentPlayer;

    public Game(Settings settings)
    {
        this.grid = new GridHashImp(settings.boardSize);
        this.player1 = settings.player1;
        this.player2 = settings.player2;
        currentPlayer = player1;
    }

    public Agent getCurrentPlayer()
    {
        return currentPlayer;
    }

    public boolean currentPlayerHuman()
    {
       return currentPlayer instanceof Human;
    }

    public List<Tile> getAllTiles()
    {
        return grid.getAllTiles();
    }

    public void placeNextAt(CubeVector v) throws TileAlreadyColouredException
    {
       grid.setTile(v, currentPlayer.getColor());
       toggleTurn();
    }

    private void toggleTurn()
    {
        if(currentPlayer == player1)
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }

    public Graph getGameAsGraph()
    {
        ArrayList<CubeVector> vectors = new ArrayList<>(grid.getAllVectors());
        ArrayList<Edge> edges = new ArrayList<>();
        HashMap<CubeVector, Node> nodes = new HashMap<>();

        for(CubeVector v: vectors)
        {
            nodes.put(v, new Node(v, grid.getTile(v).getColour()));
            for(Tile tile: grid.getAllNeighbours(v))
            {
                edges.add(new Edge(v, tile.getVector()));
            }
        }
        return new Graph(nodes, edges);
    }
}

