package Bamboo.view;

import Bamboo.controller.Settings;
import Bamboo.model.Game;
import Bamboo.view.startup.StartupPanel;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private Game currentGame;
    private GamePanel gamePanel;
    private StartupPanel startupPanel = new StartupPanel();
    private Dimension screenSize;

    public MainFrame()
    {
        buildFrame();

        // Settings Panel
        setCurrentPanel(null, startupPanel);
        while (startupPanel.isNotSettingsReady()) {
            sleep(1000);
        }
        Settings settings = startupPanel.getSettings();
        startupPanel.reset();

        // Set the current game
        currentGame = new Game(settings);
        setCurrentPanel(startupPanel, new GamePanel(screenSize, currentGame));
    }

    private void setCurrentPanel(JPanel outgoing, JPanel incoming) {
        if(outgoing != null)
            remove(outgoing);
        add(incoming);
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

    private void sleep(int amount) {
        try {
            Thread.sleep(amount);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
