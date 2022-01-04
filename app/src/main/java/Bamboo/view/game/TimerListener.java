package Bamboo.view.game;

import Bamboo.model.GameWithGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener
{
    private GameWithGUI game;
    private Canvas canvas;

    public TimerListener(GameWithGUI game, Canvas canvas)
    {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(!game.getCurrentPlayer().isHuman())
        game.placeNextAt(game.getCurrentPlayer().getNextMove(game));
        canvas.repaint();
    }
}
