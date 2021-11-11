package Bamboo.view.game;

import Bamboo.controller.Agent;
import Bamboo.model.Game;
import Bamboo.view.MainFrame;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.Button;
import Bamboo.view.resources.Label;

import javax.swing.*;
import java.awt.*;


public class SidePanel extends JPanel
{
    private Color background = Colour.background() ;
    private Game game;
    private InfoPanel infoPanel;
    private Canvas canvas ;
    private Button snaphotToggle;


    public SidePanel(Game game, MainFrame mainFrame, Canvas canvas) {
        this.canvas = canvas ;
        this.game = game;
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
        Label toggleLabel = new Label("toggleLabel.png");
        Button quitButton = new Button("btnQuit.png");
        Button hintButton = new Button("btnHint.png") ;
        hintButton.addActionListener(e -> {
            canvas.changeHint();
            canvas.repaint();
        });
        snaphotToggle = new Button(toggleButtonLabel(game.getLogMoves()));
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
        game.toggleLogging();
        if(game.getLogMoves())
            snaphotToggle.changeIcon("btnON.png");
        else
            snaphotToggle.changeIcon("btnOFF.png");
    }
    private String toggleButtonLabel(boolean on){
        if(on)
            return "btnON.png";
        return "btnOFF.png";
    }
}





