package Bamboo.view;

import Bamboo.controller.Human;
import Bamboo.controller.Settings;
import Bamboo.model.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;

public class MainFrame extends JFrame
{
    private Game currentGame;
    private JPanel currentPanel;
    private Dimension screenSize;


    public MainFrame()
    {
        buildFrame();

        // TODO add setupPanel ** here ** to get the settings for the new game
        currentGame = new Game(defaultSettings());
        currentPanel = new GamePanel(screenSize, currentGame);

        setLayout(new BorderLayout());
        add(currentPanel);
        setVisible(true);
    }

    /** Temporary Measure for testing - REMOVE! */
    private Settings defaultSettings()
    {
        return new Settings(
                new Human("Player 1", Color.BLUE),
                new Human("Player 2", Color.RED),
                5);
    }

    /** All frame settings detailed here */
    private void buildFrame()
    {
        screenSize  = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Bamboo");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
