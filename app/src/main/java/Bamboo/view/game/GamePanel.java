package Bamboo.view.game;

import Bamboo.model.GameWithGUI;
import Bamboo.view.MainFrame;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel
{
    private GameWithGUI game;
    private SidePanel sidePanel;
    private Bamboo.view.game.Canvas canvas;

    public GamePanel(Dimension screenSize, GameWithGUI game, MainFrame mainFrame)
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
