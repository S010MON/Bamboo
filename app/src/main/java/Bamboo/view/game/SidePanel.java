package Bamboo.view.game;

import Bamboo.controller.Agent;
import Bamboo.model.Game;
import Bamboo.view.MainFrame;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.Button;
import javax.swing.*;
import java.awt.*;


public class SidePanel extends JPanel
{
    private Color background = Colour.background() ;
    private Game game;
    private InfoPanel infoPanel;
    private Canvas canvas ;

    public SidePanel(Game game, MainFrame mainFrame, Canvas canvas) {
        this.canvas = canvas ;
        this.game = game;
        setBackground(background);
        setLayout(new BorderLayout());
        infoPanel = new InfoPanel(game.getCurrentPlayer().getName());
        add(infoPanel, BorderLayout.NORTH);
        Button quitButton = new Button("btnQuit.png");

        JPanel panelHint = new JPanel() ;
        panelHint.setBackground(Colour.background());
        panelHint.setLayout(new BorderLayout());
        add(panelHint,BorderLayout.CENTER) ;
        Button hintButton = new Button("btnHint.png") ;
        hintButton.addActionListener(e -> {
            canvas.changeHint();
            canvas.repaint();
        });
        panelHint.add(hintButton, BorderLayout.SOUTH) ;

        add(quitButton, BorderLayout.SOUTH);
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
}





