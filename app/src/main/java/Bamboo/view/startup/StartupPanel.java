package Bamboo.view.startup;

import Bamboo.controller.Human;
import Bamboo.controller.Settings;

import javax.swing.*;
import java.awt.*;

public class StartupPanel extends JPanel {

    private boolean settingsReady = false;
    private JPanel displayPanel;
    private HelpPanel helpPanel;
    private JFrame frame;
    private JPanel panel;

    public StartupPanel() {
        setBackground(Color.BLACK);
        helpPanel = new HelpPanel();



        setVisible(true);

    }


    public Settings getSettings() {
        return new Settings(
                new Human("Player 1", Color.BLUE),
                new Human("Player 2", Color.RED),
                5);
    }

    public boolean isNotSettingsReady() {
        return !settingsReady;
    }

    public void reset() {
        settingsReady = false;
    }

    public void toggleSettingReady() {
        settingsReady = !settingsReady;
    }

    public void displayHelpPanel() {
        add(displayPanel);
    }

    public void displaySettingsPanel(){

    }
}
