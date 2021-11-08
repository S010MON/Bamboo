package Bamboo.view.startup;

import Bamboo.controller.AgentFactory;
import Bamboo.controller.AgentType;
import Bamboo.controller.Human;
import Bamboo.controller.MiniMax.MiniMax;
import Bamboo.controller.Settings;
import Bamboo.view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class StartupPanel extends JPanel
{
    private SettingsPanel settingsPanel;
    private HelpPanel helpPanel;
    private MainFrame view;
    private int tossCoin ;
    private int size = 5;
    private Settings set ;

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

   /* public Settings getSettings()
    {
        return switch (settingsPanel.getMode()) {
            case SINGLE -> new Settings(
                    new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                    AgentFactory.makeAgent(settingsPanel.getAgentType(), settingsPanel.getPlayer2Colour()),
                    settingsPanel.getBoardSize());

            case MULTI -> new Settings(
                    new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                    new Human(settingsPanel.getPlayer2Name(), settingsPanel.getPlayer2Colour()),
                    settingsPanel.getBoardSize());
            case DEMO ->new Settings(
                    AgentFactory.makeAgent(settingsPanel.getAgentType1(), settingsPanel.getPlayer1Colour()),
                    AgentFactory.makeAgent(settingsPanel.getAgentType2(), settingsPanel.getPlayer2Colour()),
                    settingsPanel.getBoardSize());
        };
    }*/

    public Settings getSettings(){
        int tossCoin = (int) ( Math.random() * 2 + 1);
        if(settingsPanel.getMode()== Mode.SINGLE){
            set = new Settings(
                    new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                    AgentFactory.makeAgent(settingsPanel.getAgentType(), settingsPanel.getPlayer2Colour()),
                    settingsPanel.getBoardSize());
                    set.setCurrentPlayer(tossCoin);
                    return set ;
        }
        if(settingsPanel.getMode()== Mode.MULTI){
            set = new Settings(
                    new Human(settingsPanel.getPlayer1Name(), settingsPanel.getPlayer1Colour()),
                    new Human(settingsPanel.getPlayer2Name(), settingsPanel.getPlayer2Colour()),
                    settingsPanel.getBoardSize());
                    set.setCurrentPlayer(tossCoin);
                    return set ;
        }
        if(settingsPanel.getMode()== Mode.DEMO){
            set = new Settings(
                    AgentFactory.makeAgent(settingsPanel.getAgentType1(), settingsPanel.getPlayer1Colour()),
                    AgentFactory.makeAgent(settingsPanel.getAgentType2(), settingsPanel.getPlayer2Colour()),
                    settingsPanel.getBoardSize());
                    set.setCurrentPlayer(tossCoin);
                    return set ;
        }
        return set ;
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

    public int getTossCoin() {
        return tossCoin;
    }
}


