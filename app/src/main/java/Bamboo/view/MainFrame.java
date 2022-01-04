package Bamboo.view;

import Bamboo.controller.Agent;
import Bamboo.controller.FileManager;
import Bamboo.controller.Settings;
import Bamboo.model.Game;
import Bamboo.view.game.GamePanel;
import Bamboo.view.startup.StartupPanel;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private Component currentPanel = null;
    private Dimension screenSize;
    private Game currentGame;
    private Component gamePanel ;

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
        currentGame = new Game(settings, this);
        gamePanel = new GamePanel(screenSize, currentGame, this);
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

    public void gameOverOption(Game game)
    {
        Object[] options = {"Yes","No"};

        int response = JOptionPane.showOptionDialog(this, game.getNonCurrentPlayer().getName() + " won. Do you want to go back to the game?",
                "END GAME", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
                options[1]);
        if (response == JOptionPane.NO_OPTION) {
            endGame(game.getNonCurrentPlayer());
        }
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
        screenSize = new Dimension(800, 700);
        setTitle("Bamboo");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    public void quitGame() {
        Object[] options = {"Yes","No"};

        int response = JOptionPane.showOptionDialog(this, "Are you sure you want to quit? ",
                "Bamboo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
                options[1]);
        if (response == JOptionPane.YES_OPTION) {
            Object[] options2 = {"Yes","No"};

            int response2 = JOptionPane.showOptionDialog(this, "Do you want to save the current game? ",
                    "Bamboo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options2,
                    options2[1]);
            if (response2 == JOptionPane.YES_OPTION) {
                save();
            }
            showMenu();
        }
    }

    public void quitProgram()
    {
        Object[] options = {"Yes","No"};

        int response = JOptionPane.showOptionDialog(this, "Are you sure you want to quit? ",
                "Bamboo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options,
                options[1]);
        if (response == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    public void save()
    {
        if(currentPanel instanceof GamePanel)
            FileManager.save(currentGame);
    }

    public void load()
    {
        Settings settings = FileManager.load();
        if(settings != null)
            runGame(settings);
    }

    public void nextTurn()
    {
        if(currentGame != null && currentPanel!=null) {
            GamePanel panel = (GamePanel) currentPanel;
            panel.updateSidePanel();
        }
    }
    public GamePanel getgamePanel(){
        return (GamePanel) gamePanel ;
}
}
