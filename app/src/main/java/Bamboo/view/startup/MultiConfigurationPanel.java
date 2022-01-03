package Bamboo.view.startup;

import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;

public class MultiConfigurationPanel extends JPanel
{
    private JPanel textLabelPanel;
    private JPanel textFieldPanel;
    private JPanel textLabelPanel2;
    private JPanel textFieldPanel2;
    private ColourButtonPanel buttonPanel1;
    private ColourButtonPanel buttonPanel2;
    private JButton toggleButton;
    private JLabel player1label;
    private JLabel player2label;
    private JTextField player1textField;
    private JTextField player2textField;

    public MultiConfigurationPanel()
    {
        setLayout(new GridLayout(4, 1));
        setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Colour.background());
        panel1.setLayout(new GridLayout(1,3));
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Colour.background());
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Colour.background());
        panel3.setLayout(new GridLayout(1,3));
        add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Colour.background());
        panel4.setLayout(new GridLayout(1,3));
        add(panel4);

        textLabelPanel = new JPanel();
        textLabelPanel.setBackground(Colour.background());
        textLabelPanel.setLayout(new BorderLayout());
        player1label = new JLabel("PLAYER 1: ");
        player1label.setFont(new Font("SansSerif", Font.PLAIN,20));
        textLabelPanel.add(player1label,BorderLayout.EAST);
        panel1.add(textLabelPanel);

        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(null);
        textFieldPanel.setBackground(Colour.background());

        player1textField = new JTextField();
        player1textField.setBounds(20,8,200,30);
        textFieldPanel.add(player1textField);
        panel1.add(textFieldPanel);

        buttonPanel1 = new ColourButtonPanel(Color.red);
        panel1.add(buttonPanel1);

        textLabelPanel2 = new JPanel();
        textLabelPanel2.setBackground(Colour.background());
        textLabelPanel2.setLayout(new BorderLayout());
        player2label = new JLabel("PLAYER 2: ");
        player2label.setFont(new Font("SansSerif", Font.PLAIN,20));
        textLabelPanel2.add(player2label,BorderLayout.EAST);
        panel2.add(textLabelPanel2);

        textFieldPanel2 = new JPanel();
        textFieldPanel2.setLayout(null);
        textFieldPanel2.setBackground(Colour.background());
        player2textField = new JTextField();
        player2textField.setBounds(20,8,200,30);
        textFieldPanel2.add(player2textField);
        panel2.add(textFieldPanel2);

        buttonPanel2 = new ColourButtonPanel(Color.blue);
        panel2.add(buttonPanel2);

        toggleButton = new Button("btnToggle.png");
        toggleButton.setBounds(50,50,145,55);
        toggleButton.addActionListener(e -> {buttonPanel1.changeColor();buttonPanel2.changeColor();});
        panel3.add(toggleButton);
    }

    public String getNamePlayer1(){ return player1textField.getText();}

    public String getNamePlayer2(){ return player2textField.getText();}

    public Color getPlayer1Color(){return buttonPanel1.getPlayerColor();}

    public Color getPlayer2Color(){return buttonPanel2.getPlayerColor();}

    public void swapColor(){
        buttonPanel1.changeColor();buttonPanel2.changeColor();
    }

    class ColourButtonPanel extends JPanel
    {
        private Color color;

        public ColourButtonPanel(Color color)
        {
            this.color = color;
            setLayout(null);
            setBackground(Colour.background());
        }

        public void paint(Graphics g)
        {
            super.paint(g);
            g.setColor(color);
            g.fillOval(30,8,30,30);
        }

        public void changeColor()
        {
            if(color == Color.blue)
                color = Color.red;
            else
                color = Color.blue;
            repaint();
        }

        public Color getPlayerColor()
        {
            return color;
        }
    }
}