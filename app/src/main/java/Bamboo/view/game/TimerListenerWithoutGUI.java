package Bamboo.view.game;

import Bamboo.model.Game;
import Bamboo.model.GameWithoutGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListenerWithoutGUI implements ActionListener
{
    private GameWithoutGUI game;

    public TimerListenerWithoutGUI(GameWithoutGUI game)
    {
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        game.placeNextAt(game.getCurrentPlayer().getNextMove(game));
    }
}