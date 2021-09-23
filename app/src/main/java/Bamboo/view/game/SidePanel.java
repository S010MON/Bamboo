package Bamboo.view.game;

import Bamboo.model.Game;
import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel
{
    private Color background = Colour.background() ;
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





