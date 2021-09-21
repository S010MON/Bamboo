package Bamboo.view;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.model.Game;
import Bamboo.view.startup.StartupPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class MainFrame extends JFrame
{
    private Game currentGame;
    private Component currentPanel = null;
    private StartupPanel startupPanel = new StartupPanel();
    private Dimension screenSize;

    public MainFrame()
    {
        buildFrame();
        setVisible(true);
        showMenu();
    }

    public void showMenu()
    {
        add(startupPanel);
        while (startupPanel.isNotSettingsReady()) {
            sleep(1000);
        }
        Settings settings = startupPanel.getSettings();
        startupPanel.reset();
        runGame(startupPanel, settings);
    }

    public void showMenu(Component currentPanel)
    {
        remove(currentPanel);
        add(startupPanel);
        while (startupPanel.isNotSettingsReady()) {
            sleep(1000);
        }
        Settings settings = startupPanel.getSettings();
        startupPanel.reset();
        runGame(startupPanel, settings);
    }

    public void runGame(Component currentPanel, Settings settings)
    {
        remove(currentPanel);
        Game game = new Game(settings);
        Component gamePanel = new GamePanel(screenSize, game);
        add(gamePanel);
        while (game.isNotFinished()) {
            sleep(1000);
        }
        endGame(gamePanel, null);
    }

    public void endGame(Component currentPanel, Agent winner)
    {
        remove(currentPanel);
        Component endGamePanel =  new EndGame(null, screenSize);
        add(endGamePanel);
        sleep(10000);
        showMenu(endGamePanel);
    }

    private void setCurrentPanel(JPanel incoming)
    {
        if(currentPanel != null)
            remove(currentPanel);
        add(incoming);
        currentPanel = incoming;
        setVisible(true);
    }

    /** All frame settings detailed here */
    private void buildFrame()
    {
        screenSize  = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Bamboo");
        setSize(screenSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());
    }

    private void sleep(int amount) {
        try {
            Thread.sleep(amount);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
