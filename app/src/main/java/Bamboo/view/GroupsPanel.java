package Bamboo.view;


import Bamboo.controller.GroupController;

import javax.swing.*;
import java.awt.*;

public class GroupsPanel extends JPanel {
    private Color background = new Color(158, 208, 239) ;
    public GroupsPanel( )
    {
        setBackground(background);
        setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        JLabel groups = new JLabel("Groups:");
        add(groups);
        JLabel numberOfGroups = new JLabel("3");
        add(numberOfGroups);
        setVisible(true);
    }

}
