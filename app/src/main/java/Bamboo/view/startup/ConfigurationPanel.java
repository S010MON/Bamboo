package Bamboo.view.startup;

import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;

public class ConfigurationPanel extends JPanel {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;

    private JPanel buttonColorPanel;
    private JPanel textLabelPanel ;
    private JPanel textFieldPanel ;

    private JPanel buttonColorPanel2 ;
    private JPanel textLabelPanel2 ;
    private JPanel textFieldPanel2 ;

    private JButton red1;
    private JButton blue1;
    private JButton red2;
    private JButton blue2;

    private JLabel player1label;
    private JLabel player2label;

    private JTextField player1textfiled;
    private JTextField player2textField;



    public ConfigurationPanel(){

        setLayout(new GridLayout(4, 1));
        setVisible(false);

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();

        panel1.setBackground(Colour.background());
        panel1.setLayout(new GridLayout(1,3));
        add(panel1);

        panel2.setBackground(Color.red);
        panel2.setLayout(new GridLayout(1, 3));
        add(panel2);

        panel3.setBackground(Colour.background());
        panel3.setLayout(new GridLayout(1,3));
        add(panel3);

        panel4.setBackground(Colour.background());
        panel4.setLayout(new GridLayout(1,3));
        add(panel4);


        textLabelPanel = new JPanel() ;
        textLabelPanel.setBackground(Colour.background());
        textLabelPanel.setLayout(new BorderLayout());
        player1label = new JLabel("Player 1's name : ") ;
        player1label.setFont(new Font("Serif", Font.PLAIN,35)) ;
        textLabelPanel.add(player1label,BorderLayout.EAST) ;
        panel1.add(textLabelPanel) ;

        textFieldPanel = new JPanel() ;
        textFieldPanel.setLayout(null);
        textFieldPanel.setBackground(Colour.background());
        player1textfiled = new JTextField() ;
        player1textfiled.setBounds(65,35,300,50);
        textFieldPanel.add(player1textfiled) ;
        panel1.add(textFieldPanel) ;

        buttonColorPanel = new JPanel();
        buttonColorPanel.setLayout(null);
        buttonColorPanel.setBackground(Colour.background());

        red1 = new JButton("RED");
        red1.setBounds(80, 40, 100, 50);
        red1.addActionListener(e -> changeColorButton(red1,blue1,red2,blue2,Color.red,Color.blue));
        buttonColorPanel.add(red1);

        blue1 = new JButton("BLUE");
        blue1.setBounds(210, 40, 100, 50);
        blue1.addActionListener(e -> changeColorButton(blue1,red1,blue2,red2,Color.blue,Color.red)) ;
        buttonColorPanel.add(blue1);
        panel1.add(buttonColorPanel) ;

        textLabelPanel2 = new JPanel() ;
        textLabelPanel2.setBackground(Colour.background());
        textLabelPanel2.setLayout(new BorderLayout());
        player2label = new JLabel("Player 2's name : ") ;
        player2label.setFont(new Font("Serif", Font.PLAIN,35)) ;
        textLabelPanel2.add(player2label,BorderLayout.EAST) ;
        panel2.add(textLabelPanel2) ;

        textFieldPanel2 = new JPanel() ;
        textFieldPanel2.setLayout(null);
        textFieldPanel2.setBackground(Colour.background());
        player2textField = new JTextField() ;
        player2textField.setBounds(65,35,300,50);
        textFieldPanel2.add(player2textField) ;
        panel2.add(textFieldPanel2) ;

        buttonColorPanel2 = new JPanel();
        buttonColorPanel2.setLayout(null);
        buttonColorPanel2.setBackground(Colour.background());

        red2 = new JButton("RED");
        red2.setBounds(80, 40, 100, 50);
        red2.addActionListener(e -> changeColorButton(red2,red1,blue2,blue1,Color.red,Color.blue)) ;
        buttonColorPanel2.add(red2);

        blue2 = new JButton("BLUE");
        blue2.setBackground(Color.red);
        blue2.setBounds(210, 40, 100, 50);
        blue2.addActionListener(e -> changeColorButton(blue2,red2,blue1,red1,Color.blue,Color.red)) ;
        buttonColorPanel2.add(blue2);

        panel2.add(buttonColorPanel2) ;

}

    public String getNamePlayer1(){ return player1textfiled.getText();}

    public String getNamePlayer2(){ return player2textField.getText();}

    public Color getPlayer1Color(){
        if (blue1.getBackground()==Color.blue)
            return blue1.getBackground() ;
        else
            return red1.getBackground() ;
}
    public Color getPlayer2Color(){
        if(blue2.getBackground()==Color.blue)
            return blue2.getBackground() ;
        else
            return red2.getBackground() ;
}
    private void changeColorButton(JButton button1, JButton button2 ,JButton button3, JButton button4, Color color1, Color color2){

        button1.setBackground(color1);
        button1.setOpaque(true);
        button1.setBorderPainted(false);

        button4.setBackground(color2);
        button4.setOpaque(true);
        button4.setBorderPainted(false);

        if(button2.getBackground()!=null){
            button2.setBackground(null);
            button2.setOpaque(false);
            button2.setBorderPainted(true);
        }

        if(button3.getBackground()!=null){
            button3.setBackground(null);
            button3.setOpaque(false);
            button3.setBorderPainted(true);
        }
    }
}
