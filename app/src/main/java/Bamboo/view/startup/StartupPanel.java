package Bamboo.view.startup;

import Bamboo.controller.AgentFactory;
import Bamboo.controller.Human;
import Bamboo.controller.MiniMax.MiniMax;
import Bamboo.controller.MiniMax.abMiniMax;
import Bamboo.controller.MiniMax.sortedABMiniMax;
import Bamboo.controller.Random;
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

    private int size = 5;

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

    public Settings getSettings()
    {
        switch (settingsPanel.getMode())
        {
            case SINGLE: return new Settings(
                            new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                            AgentFactory.makeAgent(settingsPanel.getAgentType(), settingsPanel.getPlayer2Colour()),
                            settingsPanel.getBoardSize());
                            
            case MULTI: return new Settings(
                            new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                            new Human(settingsPanel.getPlayer2Name(), settingsPanel.getPlayer2Colour()),
                            settingsPanel.getBoardSize());

            case DEMO: return new Settings(
                            new MiniMax(settingsPanel.getPlayer1Colour()),
                            new MiniMax(settingsPanel.getPlayer2Colour()),
                            settingsPanel.getBoardSize());
        }
        return null;
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
