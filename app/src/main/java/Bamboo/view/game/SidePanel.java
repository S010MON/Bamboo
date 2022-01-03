package Bamboo.view.game;

import Bamboo.controller.Agent;
import Bamboo.model.GameWithGUI;
import Bamboo.view.MainFrame;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.Button;
import Bamboo.view.resources.Label;

import javax.swing.*;
import java.awt.*;


public class SidePanel extends JPanel
{
    private Color background = Colour.background() ;
    private GameWithGUI game;
    private MainFrame mainFrame;
    private InfoPanel infoPanel;
    private Canvas canvas ;
    private Button snaphotToggle;


    public SidePanel(GameWithGUI game, MainFrame mainFrame, Canvas canvas) {
        this.canvas = canvas ;
        this.game = game;
        this.mainFrame = mainFrame;
        setBackground(background);
        setLayout(new BorderLayout());
        infoPanel = new InfoPanel(game);
        add(infoPanel, BorderLayout.NORTH);
        JPanel panelHint = new JPanel() ;
        panelHint.setBackground(Colour.background());
        panelHint.setLayout(new BorderLayout());
        add(panelHint,BorderLayout.SOUTH) ;
        JPanel panel = new JPanel();
        panel.setBackground(Colour.background());
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        Button quitButton = new Button("btnQuit.png");
        Button hintButton = new Button("btnHint.png") ;
        hintButton.addActionListener(e -> {
            canvas.changeHint();
            canvas.repaint();
        });

        Label toggleLabel = new Label("toggleLabel.png");
        snaphotToggle = new Button(toggleButtonLabel(game.loggingEnabled()));
        snaphotToggle.addActionListener(e -> toggle());
        panel.add(toggleLabel, BorderLayout.SOUTH);

        panelHint.add(snaphotToggle, BorderLayout.NORTH);
        panelHint.add(hintButton, BorderLayout.CENTER) ;
        panelHint.add(quitButton, BorderLayout.SOUTH);
        quitButton.addActionListener(e -> mainFrame.quitGame());
        setVisible(true);
    }

    public void updateInfo()
    {
        Agent currentPlayer = game.getCurrentPlayer();
        String name = currentPlayer.getName();
        int numberOfGroups = game.getNumberOfGroupsForPlayer(currentPlayer);
        int maxGroupSize = game.getSizeOfMaxOfGroups(currentPlayer);
        infoPanel.update(name,maxGroupSize,numberOfGroups);
    }

    private void toggle()
    {
        // If no logging is happening, turn off logging
        if(game.loggingEnabled())
        {
            game.setLoggingRed(false);
            game.setLoggingBlue(false);
            snaphotToggle.changeIcon("btnOFF.png");
        }
        else // Prompt the user to chose what they want to log
        {
            Object[] options = {"Blue","Red","Both"};
            int n = JOptionPane.showOptionDialog(mainFrame,
                    "Which player would you like to record?",
                    "Data Saving",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);

            switch (n)
            {
                case 0 -> game.setLoggingBlue(true);
                case 1 -> game.setLoggingRed(true);
                case 2 -> { game.setLoggingRed(true);
                            game.setLoggingBlue(true);}
            }
            snaphotToggle.changeIcon("btnON.png");
        }
    }

    private String toggleButtonLabel(boolean on){
        if(on)
            return "btnON.png";
        return "btnOFF.png";
    }
}





