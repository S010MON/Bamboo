package Bamboo.view;


import Bamboo.model.Game;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    private Color background = new Color(158, 208, 239) ;
    GroupsPanel groupsPanel;
    PlayerPanel playerPanel;

    public SidePanel(Game game) {
        setBackground(background);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        playerPanel = new PlayerPanel(game);
        groupsPanel = new GroupsPanel();
        add(playerPanel);
        add(groupsPanel);
        setVisible(true);
    }

    public void update()
    {

    }

}





