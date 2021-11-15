package Bamboo.view.game;

import Bamboo.model.Game;
import Bamboo.view.MainFrame;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel
{
    private Game game;
    private SidePanel sidePanel;
    private Bamboo.view.game.Canvas canvas;

    public GamePanel(Dimension screenSize, Game game, MainFrame mainFrame)
    {
        this.game = game;
        setSize(screenSize);
        setLayout(new BorderLayout());

        //set the canvas panel
        canvas = new Canvas(screenSize,game);
        add(canvas, BorderLayout.CENTER);

        //set the sidePanel panel
        sidePanel = new SidePanel(game, mainFrame, canvas);
        add(sidePanel, BorderLayout.WEST);
        
        setVisible(true);
    }

    public Canvas getCanvas(){ return canvas ; }

    public void updateSidePanel()
    {
        sidePanel.updateInfo();
    }


}
