package Bamboo.view.game;

import Bamboo.model.Game;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel
{
    private Game game;
    private SidePanel sidePanel;
    private Bamboo.view.game.Canvas canvas;

    public GamePanel(Dimension screenSize, Game game)
    {
        this.game = game;
        setSize(screenSize);
        setLayout(new BorderLayout());

        //set the canvas panel
        canvas = new Canvas(screenSize,game);
        add(canvas, BorderLayout.CENTER);

        //set the sidePanel panel
        sidePanel = new SidePanel(game);
        add(sidePanel, BorderLayout.WEST);
        
        setVisible(true);
    }


}
