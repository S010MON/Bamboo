package Bamboo.view.startup;

import Bamboo.controller.Human;
import Bamboo.controller.Settings;

import javax.swing.*;
import java.awt.*;

public class StartupPanel extends JPanel {

    private boolean settingsReady = false;
    private JPanel currentPanel;
    private SettingsPanel settingsPanel;
    private HelpPanel helpPanel;
    private JFrame frame;
    private JPanel panel;



    public StartupPanel() {
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        ButtonPanel buttonPanel = new ButtonPanel(this);
        add(buttonPanel, BorderLayout.WEST);

        settingsPanel = new SettingsPanel();
        helpPanel = new HelpPanel();
        displaySettingsPanel();

        setVisible(true);
    }


    public Settings getSettings() {
        return new Settings(
                new Human(settingsPanel.getConfigurationPanel().getNamePlayer1(), settingsPanel.getConfigurationPanel().getPlayer1Color()),
                new Human(settingsPanel.getConfigurationPanel().getNamePlayer2(), settingsPanel.getConfigurationPanel().getPlayer2Color()),
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

    private  void removeComponentCenter(){
        Component centreComp = ((BorderLayout) getLayout()).getLayoutComponent(BorderLayout.CENTER);
        if(centreComp != null)
            remove(centreComp);
    }

    public void displayHelpPanel() {
        setVisible(false);
        removeComponentCenter();
        add(helpPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void displaySettingsPanel(){
        setVisible(false);
        removeComponentCenter();
        add(settingsPanel, BorderLayout.CENTER);
        setVisible(true);

    }

}
