package Bamboo.view.startup;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    private JLabel label;
    public HelpPanel() {
        setSize(500,500);
        label = new JLabel("Bamboo is a two player game played on an initially empty hexagonal grid. The" +
                "two players, Red and Blue, place their own stones onto unoccupied cells on the" +
                "board, one stone per turn. Players are not allowed to pass." +
                "A player’s group can’t contain more stones than the number of groups he has.The last player to place a stone wins");

        label.setFont(new Font("Serif", Font.PLAIN, 10));
        setBackground(Color.RED);
        add(label);
        setVisible(true);
    }
}
