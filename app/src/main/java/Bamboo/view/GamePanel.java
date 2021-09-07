package Bamboo.view;

import Bamboo.model.Game;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
    private Game game;
    private Canvas canvas;

    public GamePanel(Dimension screenSize, Game game)
    {
        this.game = game;

        setSize(screenSize);
        setLayout(new BorderLayout());

        canvas = new Canvas(screenSize, game);
        add(canvas, BorderLayout.CENTER);

        setVisible(true);
    }
}
