package Bamboo.view.game;

import Bamboo.model.GameWithGUI;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.Label;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Font;

public class InfoPanel extends JPanel
{
    private Label groupsLabel;
    private JLabel groupsDisplay;

    private Label sizeLabel;
    private JLabel sizeDisplay;

    private Label playerLabel;
    private JLabel playerDisplay;

    private GameWithGUI game;

    public InfoPanel(GameWithGUI game)
    {
        this.game = game;
        BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(layout);
        setBackground(Colour.BACKGROUND());

        groupsLabel = new  Label("labelGroups.png");
        groupsLabel.setAlignmentX(0.5f);
        add(groupsLabel);

        groupsDisplay = new JLabel("0");
        groupsDisplay.setAlignmentX(0.5f);
        groupsDisplay.setFont(new Font("Monospaced", Font.PLAIN, 22));
        add(groupsDisplay);

        sizeLabel = new Label("labelSize.png");
        sizeLabel.setAlignmentX(0.5f);
        add(sizeLabel);

        sizeDisplay = new JLabel("0");
        sizeDisplay.setAlignmentX(0.5f);
        sizeDisplay.setFont(new Font("Monospaced", Font.PLAIN, 22));
        add(sizeDisplay);

        playerLabel = new Label("labelPlayer.png");
        playerLabel.setAlignmentX(0.5f);
        add(playerLabel);

        playerDisplay = new JLabel(game.getCurrentPlayer().getName());
        playerDisplay.setAlignmentX(0.5f);
        playerDisplay.setFont(new Font("Monospaced", Font.PLAIN, 22));
        add(playerDisplay);

        setVisible(true);
    }

    public void update(String name, Integer maxSize, Integer noOfGroups)
    {
        groupsDisplay.setText(noOfGroups.toString());
        sizeDisplay.setText(maxSize.toString());
        playerDisplay.setText(name);
    }


}
