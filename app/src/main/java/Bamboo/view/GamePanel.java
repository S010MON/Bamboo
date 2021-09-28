package Bamboo.view;

import Bamboo.model.Game;
import Bamboo.model.Grid;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel
{
    private Game game;
    private Grid grid;
    private SidePanel sidePanel;
    private Canvas canvas;

    public GamePanel(Dimension screenSize, Game game, Grid grid)
    {
        this.game = game;
        this.grid = grid;

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
