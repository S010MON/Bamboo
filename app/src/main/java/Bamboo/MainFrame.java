package Bamboo;

import javax.swing.*;

public class MainFrame extends JFrame {

    private JPanel currentPanel ;

    private String player1name ;
    private String player2name ;

    MainFrame(){

        setTitle("Bamboo");
        setSize(1000,900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        String player1 = JOptionPane.showInputDialog("Player 1 : ") ;
        String player2 = JOptionPane.showInputDialog("Player 2 : ") ;

        player1name = player1 ;
        player2name = player2 ;
    }

    public String getPlayer1Name(){
        return player1name;
    }

    public String getPlayer2Name(){
        return player2name ;
    }
}
