package Bamboo.view.startup;

import Bamboo.controller.Human;
import Bamboo.controller.Settings;
import Bamboo.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class StartupPanel extends JPanel
{
    private SettingsPanel settingsPanel;
    private HelpPanel helpPanel;
    private MainFrame view;
    private Settings settings = Settings.getDefaultSetting();

    public StartupPanel(MainFrame view)
    {
        this.view = view;
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        ButtonPanel buttonPanel = new ButtonPanel(this);
        add(buttonPanel, BorderLayout.WEST);

        settingsPanel = new SettingsPanel();
        helpPanel = new HelpPanel();
        displaySettingsPanel();

        setVisible(true);
    }

    public void startGame()
    {
        view.runGame(getSettings());
    }

    public Settings getSettings() {
        return new Settings(
                new Human(settingsPanel.getConfigurationPanel().getNamePlayer1(), settingsPanel.getConfigurationPanel().getPlayer1Color()),
                new Human(settingsPanel.getConfigurationPanel().getNamePlayer2(), settingsPanel.getConfigurationPanel().getPlayer2Color()),
                5);
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

    public MainFrame getMainFrame()
    {
        return view;
    }
}
