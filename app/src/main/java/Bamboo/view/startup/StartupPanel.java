package Bamboo.view.startup;

import Bamboo.controller.AgentFactory;
import Bamboo.controller.AgentType;
import Bamboo.controller.Human;
import Bamboo.controller.Settings;
import Bamboo.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class StartupPanel extends JPanel
{
    private SettingsPanel settingsPanel;
    private HelpPanel helpPanel;
    private MainFrame view;
    private int size = 5;
    private DemoConfigurationPanel demoConfigurationPanel;
    private SingleConfigurationPanel singleConfigurationPanel;
    private MultiConfigurationPanel multiConfigurationPanel;

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

    public void startGame() throws IOException {
        view.runGame(getSettings());
    }

    public Settings getSettings() throws IOException {

        demoConfigurationPanel = settingsPanel.getDemoConfigurationPanel();
        singleConfigurationPanel = settingsPanel.getSingleConfigurationPanel();

        if (demoConfigurationPanel.getAgentType1() != AgentType.NEURAL_NET &&
                demoConfigurationPanel.getAgentType2() != AgentType.NEURAL_NET &&
                singleConfigurationPanel.getAgentType() != AgentType.NEURAL_NET) {

            return switch (settingsPanel.getMode()) {
                case SINGLE -> new Settings(
                        new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                        AgentFactory.makeAgent(settingsPanel.getAgentType(), settingsPanel.getPlayer2Colour()),
                        settingsPanel.getBoardSize());
                case MULTI -> new Settings(
                        new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                        new Human(settingsPanel.getPlayer2Name(), settingsPanel.getPlayer2Colour()),
                        settingsPanel.getBoardSize());
                case DEMO -> new Settings(
                        AgentFactory.makeAgent(settingsPanel.getAgentType1(), settingsPanel.getPlayer1Colour()),
                        AgentFactory.makeAgent(settingsPanel.getAgentType2(), settingsPanel.getPlayer2Colour()),
                        settingsPanel.getBoardSize());
            };
        }
        return switch (settingsPanel.getMode()) {
            case SINGLE -> new Settings(
                    new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                    AgentFactory.makeAgent(settingsPanel.getAgentType(), settingsPanel.getPlayer2Colour()),
                    5);
            case MULTI -> new Settings(
                    new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                    new Human(settingsPanel.getPlayer2Name(), settingsPanel.getPlayer2Colour()),
                    settingsPanel.getBoardSize());
            case DEMO -> new Settings(
                    AgentFactory.makeAgent(settingsPanel.getAgentType1(), settingsPanel.getPlayer1Colour()),
                    AgentFactory.makeAgent(settingsPanel.getAgentType2(), settingsPanel.getPlayer2Colour()),
                    5);
        };
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
