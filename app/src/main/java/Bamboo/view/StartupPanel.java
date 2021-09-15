package Bamboo.view;

import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class StartupPanel extends JPanel {
    private String player1name;
    private String player2name;

    public StartupPanel() {
        //test comment
        player1name = JOptionPane.showInputDialog("Player 1 : ");
        player2name = JOptionPane.showInputDialog("Player 2 : ");
    }
}
