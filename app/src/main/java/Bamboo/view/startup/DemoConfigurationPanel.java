package Bamboo.view.startup;

import Bamboo.controller.AgentType;
import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DemoConfigurationPanel extends JPanel
{
    private JPanel textLabelPanel;
    private JPanel textFieldPanel;
    private JPanel textLabelPanel2;
    private JPanel textFieldPanel2;
    private Button button;
    private SingleButtonPanel buttonPanel1;
    private SingleButtonPanel buttonPanel2;
    private JButton toggleButton;
    private JLabel player1label;
    private JLabel AIlabel;
    private JComboBox AIcombobox1;
    private JComboBox AIcombobox2 ;
    private String[] AIstring ;

    public DemoConfigurationPanel(StartupPanel startupPanel)
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
        player1label = new JLabel("SELECT AI 1 : ");
        player1label.setFont(new Font("SansSerif", Font.PLAIN,20));
        textLabelPanel.add(player1label,BorderLayout.EAST);
        panel1.add(textLabelPanel);

        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(null);
        textFieldPanel.setBackground(Colour.background());
        AIstring = AgentType.getNames(AgentType.class);
        AIcombobox1 = new JComboBox(AgentType.values());
        AIcombobox1.setBounds(20,8,200,30);
        textFieldPanel.add(AIcombobox1);
        panel1.add(textFieldPanel);

        buttonPanel1 = new SingleButtonPanel(Color.red);
        panel1.add(buttonPanel1);

        textLabelPanel2 = new JPanel();
        textLabelPanel2.setBackground(Colour.background());
        textLabelPanel2.setLayout(new BorderLayout());
        AIlabel = new JLabel("SELECT AI 2 : ");
        AIlabel.setFont(new Font("SansSerif", Font.PLAIN,20));
        textLabelPanel2.add(AIlabel,BorderLayout.EAST);
        panel2.add(textLabelPanel2);

        textFieldPanel2 = new JPanel();
        textFieldPanel2.setLayout(null);
        textFieldPanel2.setBackground(Colour.background());
        AIcombobox2 = new JComboBox(AgentType.values());
        AIcombobox2.setBounds(20,8,200,30);
        textFieldPanel2.add(AIcombobox2);
        panel2.add(textFieldPanel2);

        buttonPanel2 = new SingleButtonPanel(Color.blue);
        panel2.add(buttonPanel2);

        Button startBtn = new Button("btnStart.png");
        startBtn.addActionListener(e -> {
            try {
                startupPanel.startGame();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        panel3.add(startBtn);

    }

    public AgentType getAgentType1()
    {
        return  (AgentType) AIcombobox1.getSelectedItem();
    }

    public AgentType getAgentType2()
    {
        return  (AgentType) AIcombobox2.getSelectedItem();
    }

    public Color getAI1color(){return buttonPanel1.getPlayerColor();}

    public  Color getAI2color(){return buttonPanel2.getPlayerColor() ; }

    public void swapColor(){
        buttonPanel1.changeColor();buttonPanel2.changeColor();
    }

    class SingleButtonPanel extends JPanel
    {
        private Color color;

        public SingleButtonPanel(Color color)
        {
            this.color = color;
            setLayout(null);
            setBackground(Colour.background());
            setVisible(true);
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
