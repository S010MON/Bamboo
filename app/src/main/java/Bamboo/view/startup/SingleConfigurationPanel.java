package Bamboo.view.startup;

import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;

public class SingleConfigurationPanel extends JPanel {

    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel textLabelPanel;
    private JPanel textFieldPanel;
    private ConfigurationPanel.ButtonPanel buttonPanel1;
    private JButton toggleButton;
    private JLabel player1label;
    private JTextField player1textField;


    public SingleConfigurationPanel()
    {
        setLayout(new GridLayout(4, 1));
        setVisible(true);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        panel1.setBackground(Colour.background());
        panel1.setLayout(new GridLayout(1,3));
        add(panel1);

        panel2.setBackground(Colour.background());
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2);

        panel3.setBackground(Colour.background());
        panel3.setLayout(new GridLayout(1,3));
        add(panel3);

        panel4.setBackground(Colour.background());
        panel4.setLayout(new GridLayout(1,3));
        add(panel4);

        textLabelPanel = new JPanel();
        textLabelPanel.setBackground(Colour.background());
        textLabelPanel.setLayout(new BorderLayout());
        player1label = new JLabel("PLAYER NAME : ");
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

        buttonPanel1 = new ConfigurationPanel.ButtonPanel(Color.red);
        panel1.add(buttonPanel1);



        toggleButton = new Button("btnToggle.png");
        toggleButton.setBounds(50,50,145,55);
        toggleButton.addActionListener(e -> {buttonPanel1.changeColor();});
        panel3.add(toggleButton);
    }

    public String getNamePlayer1(){ return player1textField.getText();}



    public Color getPlayer1Color(){return buttonPanel1.getPlayerColor();}



    class SingleButtonPanel extends JPanel
    {
        private Color color;

        public SingleButtonPanel(Color color)
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

