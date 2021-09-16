package Bamboo.model;

import Bamboo.controller.*;

import java.awt.*;
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

    public Game(Grid grid){
        this.grid = grid;
    }

    public Game(int size){
        this.grid = new GridArrayImp(size);
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

    public Grid getGrid(){return grid;}

    public void placeNextAt(CubeVector v) throws TileAlreadyColouredException
    {
        if(GameLogic.is_legal_move(this, grid.getTile(v), currentPlayer.getColor()))
        {
            grid.setTile(v, currentPlayer.getColor());
            toggleTurn();
        }
        else
            System.out.println("Illegal Move");
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

