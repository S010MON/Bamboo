package Bamboo.view.game;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.model.Game;
import Bamboo.view.EndGame;
import Bamboo.view.MenuBar;
import Bamboo.view.startup.StartupPanel;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private Component currentPanel = null;
    private Dimension screenSize = new Dimension(800, 700);

    public MainFrame()
    {
        buildFrame();
        setVisible(true);
        showMenu();
    }

    public void showMenu()
    {
        removeCurrentPanel();
        StartupPanel startupPanel = new StartupPanel(this);
        add(startupPanel);
        currentPanel = startupPanel;
    }

    public void runGame(Settings settings)
    {
        removeCurrentPanel();
        Game game = new Game(settings, this);
        Component gamePanel = new GamePanel(screenSize, game);
        add(gamePanel);
        currentPanel = gamePanel;
    }

    public void endGame(Agent winner)
    {
        removeCurrentPanel();
        Component endGamePanel =  new EndGame(winner, screenSize, this);
        add(endGamePanel);
        currentPanel = endGamePanel;
    }

    private void removeCurrentPanel()
    {
        if(currentPanel != null)
        {
            currentPanel.setVisible(false);
            remove(currentPanel);
        }
    }

    /** All frame settings detailed here */
    private void buildFrame()
    {
        setTitle("Bamboo");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
        setJMenuBar(new MenuBar(this));
    }

    public void quit() {
        Object[] options = {"Yes","No"};

        int n = JOptionPane.showOptionDialog(this, "Do you want to save and quit? ",
                "Bamboo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
                options[1]);

        if (n == JOptionPane.NO_OPTION)
            System.exit(0);
        else
            saveAndQuit();
    }

    public void saveAndQuit() {

    }

    public void save() {

    }

    public void load() {

    }
}
