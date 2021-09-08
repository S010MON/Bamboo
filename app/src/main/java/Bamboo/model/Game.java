package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.CubeVector;
import Bamboo.controller.Settings;
import Bamboo.controller.VectorConverter;

import java.awt.*;
import java.util.List;

public class Game
{
    private Grid grid;
    private Agent player1;
    private Agent player2;
    private Agent currentPlayer;

    public Game(Settings settings)
    {
        this.grid = new GridArrayImp(settings.boardSize);
    }

    public List<Tile> getAllTiles()
    {
        return grid.getAllTiles();
    }

    public void placeNextAt(CubeVector v)
    {
        grid.setTile(v, Color.RED);
    }
}
