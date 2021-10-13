package Bamboo.view.startup;

import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.ResourceLoader;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class SettingsPanel extends JPanel {

    private JPanel buttonPanel;
    private Button hvh;
    private Button hva;
    private Button ava;
    private ConfigurationPanel configurationPanel;
    private JSlider slider;
    private Image boardPicture;
    private int boardSize = 5;

    private JLabel labelImage2;
    private JLabel labelImage3;
    private JLabel labelImage4;
    private JLabel labelImage5;


    public SettingsPanel() {
        setBackground(Colour.background());
        setLayout(new GridLayout(4, 3));
        setVisible(true);

        configurationPanel = new ConfigurationPanel();
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Colour.background());
        buttonPanel.setLayout(null);

        hvh = new Bamboo.view.resources.Button("btnMulti.png");
        ////////////
        slider = new JSlider(JSlider.HORIZONTAL, 2, 5, boardSize);
        slider.addChangeListener(e -> {
            JSlider src = (JSlider) e.getSource();
            if (src.getValueIsAdjusting())
                boardSize = slider.getValue();
            changeBoardImage2(boardSize);
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

        hvh = new Button("btnMulti.png");
        hvh.setBounds(100, 50, 145, 55);
        hvh.addActionListener(e -> configurationPanel.setVisible(true));

        hva = new Bamboo.view.resources.Button("btnSingle.png");
        hva.setBounds(250, 50, 145, 55);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        ava = new Button("btnDemo.png");
        ava.setBounds(400, 50, 145, 55);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        buttonPanel.add(hvh);
        buttonPanel.add(hva);
        buttonPanel.add(ava);
        add(buttonPanel);

        add(configurationPanel);

        JPanel panelImage = new JPanel();
        panelImage.setBackground(Colour.background());

        labelImage5 = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardsizeDim5.png")));
        labelImage4 = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardSizeDim4.png")));
        labelImage4.setVisible(false);
        labelImage3 = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardSizeDim3.png")));
        labelImage3.setVisible(false);
        labelImage2 = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardSizeDim2.png")));
        labelImage2.setVisible(false);

        panelImage.add(labelImage4);
        panelImage.add(labelImage3);
        panelImage.add(labelImage2);
        panelImage.add(labelImage5);
        add(panelImage);

        add(slider);
        add(configurationPanel);
    }

    public ConfigurationPanel getConfigurationPanel() {
        return configurationPanel;
    }

    public int getBoardSize() {
        return boardSize;
    }

    private void changeBoardImage2(int size) {

        if (size == 2) {
            labelImage3.setVisible(false);
            labelImage4.setVisible(false);
            labelImage5.setVisible(false);
            labelImage2.setVisible(true);

        }

        if (size == 3) {
            labelImage2.setVisible(false);
            labelImage4.setVisible(false);
            labelImage5.setVisible(false);
            labelImage3.setVisible(true);

        }

        if (size == 4) {
            labelImage2.setVisible(false);
            labelImage3.setVisible(false);
            labelImage5.setVisible(false);
            labelImage4.setVisible(true);
        }

        if (size == 5) {
            labelImage2.setVisible(false);
            labelImage3.setVisible(false);
            labelImage4.setVisible(false);
            labelImage5.setVisible(true);

        }
    }

}
