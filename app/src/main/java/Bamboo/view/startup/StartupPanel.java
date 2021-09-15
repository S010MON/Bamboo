package Bamboo.view.startup;

import Bamboo.controller.Human;
import Bamboo.controller.Settings;

import javax.swing.*;
import java.awt.*;

public class StartupPanel extends JPanel {
    private String player1name;
    private String player2name;
    private boolean settingsReady = false;
    private JPanel displayPanel;
    private HelpPanel helpPanel;
    private JFrame frame;
    private JPanel panel;

    public StartupPanel() {
        setBackground(Color.BLACK);
        helpPanel = new HelpPanel();

        JButton help = new JButton("Help");
        help.addActionListener(e -> displayHelpPanel());
        add(help);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> toggleSettingReady());
        add(startButton);

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

    private void toggleSettingReady() {
        settingsReady = !settingsReady;
    }

    private void displayHelpPanel() {
        add(displayPanel);
    }
}
