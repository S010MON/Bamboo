package Bamboo.controller;

public class Settings
{
    public Agent player1;
    public Agent player2;
    public int boardSize;

    public Settings(Agent player1, Agent player2, int boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardSize = boardSize;
    }
}
