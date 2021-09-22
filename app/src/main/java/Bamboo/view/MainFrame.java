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
    private Component currentPanel = null;
    private Dimension screenSize;

    public MainFrame()
    {
        buildFrame();
        setVisible(true);
        showMenu();
    }

    public void showMenu()
    {
        removeCurrentPanel();
        add(new StartupPanel(this));
    }

    public void runGame(Settings settings)
    {
        removeCurrentPanel();
        Game game = new Game(settings, this);
        Component gamePanel = new GamePanel(screenSize, game);
        add(gamePanel);
        endGame(gamePanel, null);
    }

    public void endGame(Component currentPanel, Agent winner)
    {
        removeCurrentPanel();
        Component endGamePanel =  new EndGame(null, screenSize);
        add(endGamePanel);
        sleep(1000);
        showMenu();
    }

    private void removeCurrentPanel()
    {
        if(currentPanel != null)
            remove(currentPanel);
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
