package Bamboo.view.startup;

import Bamboo.controller.AgentType;
import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;

public class SingleConfigurationPanel extends JPanel
{
    private JPanel textLabelPanel;
    private JPanel textFieldPanel;
    private JPanel textLabelPanel2;
    private JPanel textFieldPanel2;
    private SingleButtonPanel buttonPanel1;
    private SingleButtonPanel buttonPanel2;
    private JLabel player1label;
    private JLabel AIlabel;
    private JTextField player1textField;
    private JComboBox AIcombobox;
    private String[] AIstring ;
    private AgentType AT ;
    private SettingsPanel settingsPanel;

    public SingleConfigurationPanel( SettingsPanel settingsPanel, StartupPanel startupPanel)
    {
        this.settingsPanel=settingsPanel;
        setLayout(new GridLayout(4, 1));
        setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.setBackground(Colour.BACKGROUND());
        panel1.setLayout(new GridLayout(1,3));
        add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setBackground(Colour.BACKGROUND());
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2);

        JPanel panel3 = new JPanel();
        panel3.setBackground(Colour.BACKGROUND());
        panel3.setLayout(new GridLayout(1,3));
        add(panel3);

        JPanel panel4 = new JPanel();
        panel4.setBackground(Colour.BACKGROUND());
        panel4.setLayout(new GridLayout(1,3));
        add(panel4);

        textLabelPanel = new JPanel();
        textLabelPanel.setBackground(Colour.BACKGROUND());
        textLabelPanel.setLayout(new BorderLayout());
        player1label = new JLabel("PLAYER NAME : ");
        player1label.setFont(new Font("SansSerif", Font.PLAIN,20));
        textLabelPanel.add(player1label,BorderLayout.EAST);
        panel1.add(textLabelPanel);

        textFieldPanel = new JPanel();
        textFieldPanel.setLayout(null);
        textFieldPanel.setBackground(Colour.BACKGROUND());
        player1textField = new JTextField("PLAYER");
        player1textField.setForeground(Colour.INACTIVE_TEXT());
        player1textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                player1textField.setForeground(Colour.ACTIVE_TEXT());
                player1textField.repaint();
                if(player1textField.getText().equalsIgnoreCase("PLAYER")){
                 player1textField.setText(null);
                 player1textField.repaint();
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(player1textField.getText().length()!=0){
                    setForeground(Colour.INACTIVE_TEXT());
                    player1textField.repaint();
                }
                else{
                    player1textField.setText("PLAYER");
                    player1textField.setForeground(Colour.INACTIVE_TEXT());
                    player1textField.repaint();
                }
            }
        });
        player1textField.setBounds(20,8,200,30);
        textFieldPanel.add(player1textField);
        panel1.add(textFieldPanel);

        buttonPanel1 = new SingleButtonPanel(Color.red);
        panel1.add(buttonPanel1);

        textLabelPanel2 = new JPanel();
        textLabelPanel2.setBackground(Colour.BACKGROUND());
        textLabelPanel2.setLayout(new BorderLayout());
        AIlabel = new JLabel("SELECT AI : ");
        AIlabel.setFont(new Font("SansSerif", Font.PLAIN,20));
        textLabelPanel2.add(AIlabel,BorderLayout.EAST);
        panel2.add(textLabelPanel2);

        textFieldPanel2 = new JPanel();
        textFieldPanel2.setLayout(null);
        textFieldPanel2.setBackground(Colour.BACKGROUND());
        AIstring = AgentType.getNames(AgentType.class);
        AIcombobox = new JComboBox(AgentType.values());
        AIcombobox.setBounds(20,8,200,30);
        ComboListener comboListener = new ComboListener(this,AIcombobox);
        AIcombobox.addActionListener(comboListener);
        textFieldPanel2.add(AIcombobox);
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

    public String getNamePlayer1(){ return player1textField.getText();}

    public AgentType getAgentType()
    {
        return (AgentType) AIcombobox.getSelectedItem();

    }

    public Color getPlayer1Color(){return buttonPanel1.getPlayerColor();}

    public  Color getAIcolor(){return buttonPanel2.getPlayerColor() ; }

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
            setBackground(Colour.BACKGROUND());
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

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }
}

