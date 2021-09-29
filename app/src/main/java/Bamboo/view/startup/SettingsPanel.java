package Bamboo.view.startup;

import Bamboo.view.resources.Colour;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Hashtable;

public class SettingsPanel extends JPanel {

    private JPanel buttonPanel ;
    private Button hvh ;
    private Button hva ;
    private Button ava ;
    private ConfigurationPanel configurationPanel ;
    private JSlider slider;
    private int boardSize = 5;

    public SettingsPanel() {

        setBackground(Colour.background());
        setLayout(new GridLayout(3,1));
        setVisible(true);

        configurationPanel = new ConfigurationPanel() ;
        buttonPanel = new JPanel() ;
        buttonPanel.setBackground(Colour.background());
        buttonPanel.setLayout(null);

        ////////////
        slider = new JSlider(JSlider.HORIZONTAL, 2,7,boardSize);
        slider.addChangeListener(e -> {
            JSlider src = (JSlider) e.getSource();
            if (src.getValueIsAdjusting())
                boardSize = slider.getValue();
        });
        slider.setPaintLabels(true);
        slider.setBackground(Colour.background());

        Hashtable position = new Hashtable();
        position.put(2, new JLabel("2"));
        position.put(3, new JLabel("3"));
        position.put(4, new JLabel("4"));
        position.put(5, new JLabel("5"));
        position.put(6, new JLabel("6"));
        position.put(7, new JLabel("7"));
        slider.setLabelTable(position);

        ///////////////////

        hvh = new Button("btnMulti.png") ;
        hvh.setBounds(100,50,145,55);
        hvh.addActionListener(e -> configurationPanel.setVisible(true));

        hva = new Button("btnSingle.png") ;
        hva.setBounds(250,50,145,55);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        ava = new Button("btnDemo.png") ;
        ava.setBounds(400,50,145,55);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        buttonPanel.add(hvh) ;
        buttonPanel.add(hva) ;
        buttonPanel.add(ava) ;


        add(buttonPanel) ;
        add(slider) ;
        add(configurationPanel) ;
    }

    public ConfigurationPanel getConfigurationPanel(){
        return configurationPanel ;
    }

    public int getBoardSize() {
        return boardSize;
    }
}
