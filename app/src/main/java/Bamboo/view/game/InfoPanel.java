package Bamboo.view.game;

import Bamboo.model.Game;
import Bamboo.view.startup.Label;

import javax.swing.*;
import java.awt.*;


public class InfoPanel extends JPanel {
    private Color background = new Color(158, 208, 239) ;
    private JLabel numberOfGroups;
    private  JLabel maxSizeOfGroupsLabel;
    private JLabel maxSizeOfGroups;
    private  JLabel player;
    private JLabel currentPlayer;
    private JLabel numberOfGroupsLabel;



    public InfoPanel(String firstPlayer)
    {


        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(background);


        numberOfGroupsLabel = new  JLabel("Number of groups:");
        numberOfGroupsLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
        add(numberOfGroupsLabel);

        numberOfGroups = new JLabel("0");
        add(numberOfGroups);

        maxSizeOfGroupsLabel = new JLabel("Maximum size group:");
        maxSizeOfGroupsLabel.setFont(new Font("Monospaced", Font.PLAIN, 18));
        add(maxSizeOfGroupsLabel);

        maxSizeOfGroups = new JLabel("0");
        add(maxSizeOfGroups);

        player = new JLabel("Player:");
        player.setFont(new Font("Monospaced", Font.PLAIN, 18));
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
