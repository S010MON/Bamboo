package Bamboo.view;

import Bamboo.model.Game;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private  Game game;
    private Color background = new Color(158, 208, 239) ;

    public PlayerPanel(Game game){
        this.game = game;
        setBackground(background);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        JLabel player = new JLabel("Player:");
        JLabel currentPlayer = new JLabel(game.getCurrentPlayer().getName());
        add(player);
        add(currentPlayer);
        setVisible(true);

    }
    public void update ()
    {

    }

}
