package Bamboo.view.game;

import Bamboo.model.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener
{
    private Game game;
    private Canvas canvas;

    public TimerListener(Game game, Canvas canvas)
    {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        game.placeNextAt(game.getCurrentPlayer().getNextMove(game));
        canvas.repaint();
    }
}