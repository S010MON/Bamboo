package Bamboo.view.startup;

import Bamboo.view.ConfigurationPanel;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private JPanel buttonPanel ;
    private JButton hvh ;
    private JButton hva ;
    private JButton ava ;
    private ConfigurationPanel configurationPanel ;

    public SettingsPanel() {

        setBackground(new Color(158, 208, 239));
        setLayout(new GridLayout(2,1));
        setVisible(true);

        configurationPanel = new ConfigurationPanel() ;
        buttonPanel = new JPanel() ;
        buttonPanel.setBackground(new Color(158, 208, 239));
        buttonPanel.setLayout(null);

        hvh = new JButton("HUMAN vs HUMAN") ;
        hvh.setBounds(300,50,200,100);
        hvh.addActionListener(e -> configurationPanel.setVisible(true));

        hva = new JButton("HUMAN vs AI") ;
        hva.setBounds(550,50,200,100);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        ava = new JButton("AI vs AI") ;
        ava.setBounds(800,50,200,100);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        buttonPanel.add(hvh) ;
        buttonPanel.add(hva) ;
        buttonPanel.add(ava) ;
        add(buttonPanel) ;

        add(configurationPanel) ;
    }
    public ConfigurationPanel getConfigurationPanel(){
        return configurationPanel ;
    }
}
