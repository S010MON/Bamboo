package Bamboo.view.startup;

import Bamboo.view.resources.Colour;
import Bamboo.view.resources.ResourceLoader;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class SettingsPanel extends JPanel {

    private JPanel buttonPanel ;
    private Button hvh ;
    private Button hva ;
    private Button ava ;
    private ConfigurationPanel configurationPanel ;
    private JSlider slider;
    private Image boardPicture;
    private int boardSize = 5;

    public SettingsPanel() {

        setBackground(Colour.background());
        setLayout(new GridLayout(3,3));
        setVisible(true);

        configurationPanel = new ConfigurationPanel() ;
        buttonPanel = new JPanel() ;
        buttonPanel.setBackground(Colour.background());
        buttonPanel.setLayout(null);

        ////////////
        slider = new JSlider(JSlider.HORIZONTAL, 2,7,boardSize);
        boardPicture = ResourceLoader.getImage("BoardSize5.PNG");
        slider.addChangeListener(e -> {
            JSlider src = (JSlider) e.getSource();
            if (src.getValueIsAdjusting())
                boardSize = slider.getValue();
                changeBoardImage(boardSize);
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

    //take a number and swap the image
    private void changeBoardImage(int size){
        switch (size){
            case 2: boardPicture = ResourceLoader.getImage("BoardSize2.PNG");
            break;
            case 3: boardPicture = ResourceLoader.getImage("BoardSize3.PNG");
            break;
            case 4: boardPicture = ResourceLoader.getImage("BoardSize4.PNG");
                break;
            case 5: boardPicture = ResourceLoader.getImage("BoardSize5.PNG");
                break;
            case 6: boardPicture = ResourceLoader.getImage("BoardSize6.PNG");
                break;
            case 7: boardPicture = ResourceLoader.getImage("BoardSize7.PNG");
                break;
        }
        repaint();
    }


}
