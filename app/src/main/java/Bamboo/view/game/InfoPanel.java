package Bamboo.view.game;

import Bamboo.model.Game;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private Color background = new Color(158, 208, 239) ;
    private JLabel numberOfGroupsLabel;
    private JLabel numberOfGroups;
    private  JLabel maxSizeOfGroupsLabel;
    private JLabel maxSizeOfGroups;
    private  JLabel player;
    private JLabel currentPlayer;

    public InfoPanel(String firstPlayer)
    {
        setBackground(background);
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));

        numberOfGroupsLabel = new JLabel("NumberOfGroups:");
        add(numberOfGroupsLabel);

        numberOfGroups = new JLabel("0");
        add(numberOfGroups);

        maxSizeOfGroupsLabel = new JLabel("MaxSize:");
        add(maxSizeOfGroupsLabel);

        maxSizeOfGroups = new JLabel("0");
        add(maxSizeOfGroups);

        player = new JLabel("Player:");
        add(player);

        currentPlayer = new JLabel(firstPlayer);
        add(currentPlayer);

        setVisible(true);
    }

    public void update(String name, Integer maxSize, Integer noOfGroups)
    {
        numberOfGroups.setText(noOfGroups.toString());
        maxSizeOfGroups.setText(maxSize.toString());
        currentPlayer.setText(name);
    }
}
