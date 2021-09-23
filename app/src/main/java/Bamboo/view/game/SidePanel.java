package Bamboo.view.game;

import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel
{
    public SidePanel()
    {
        buildSidePanel();
    }

    public void buildSidePanel()
    {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        panel1.setBackground(Colour.background());
        panel2.setBackground(Colour.background());

        //create the pause button
        JButton pauseButton = new JButton("pause");
        pauseButton.setBackground(Colour.background());
        pauseButton.setForeground(Colour.background());

        //create the group label
        JLabel groups = new JLabel("Number of groups:");

        //create the player label
        JLabel player = new JLabel("Player:");

        //create timer label
        JLabel timer = new JLabel("Timer");

        //adding everything to the side panel
        add(panel1,BorderLayout.NORTH);
        add(panel2,BorderLayout.CENTER);
        panel1.add(pauseButton,BorderLayout.NORTH);
        panel1.add(groups,BorderLayout.SOUTH);
        panel1.add(player,BorderLayout.CENTER);
        panel2.add(timer,BorderLayout.NORTH);

        //add the actionListener on the button
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectionButtonPressed();
            }
        } );

        setVisible(true);
    }

    public void selectionButtonPressed() {

    }
}





