package Bamboo.model;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;

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

    public Grid getGrid()
    {
        return grid;
    }
}
