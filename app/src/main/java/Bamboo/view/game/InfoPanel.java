package Bamboo.view.game;

import Bamboo.model.Game;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.Label;
import Bamboo.view.resources.Button;

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
    private Button snaphotToggle;

    private Game game;

    public InfoPanel(Game game)
    {
        this.game = game;
        BoxLayout layout = new BoxLayout(this,BoxLayout.Y_AXIS);
        setLayout(layout);
        setBackground(Colour.background());

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

        snaphotToggle = new Button(toggleButtonLabel(game.getLogMoves()));
        snaphotToggle.setAlignmentX(0.5f);
        snaphotToggle.setFont(new Font("Monospaced", Font.PLAIN, 22));
        add(snaphotToggle);
        snaphotToggle.addActionListener(e -> toggle());

        setVisible(true);
    }

    public void update(String name, Integer maxSize, Integer noOfGroups)
    {
        groupsDisplay.setText(noOfGroups.toString());
        sizeDisplay.setText(maxSize.toString());
        playerDisplay.setText(name);
    }

    private void toggle()
    {
        game.toggleLogging();
        if(game.getLogMoves())
            snaphotToggle.changeIcon("btnStart.png");
        else
            snaphotToggle.changeIcon("btn.png");
    }

    private String toggleButtonLabel(boolean on){
        if(on)
            return "btnStart.png";
        return "btn.png";
    }
}
