package Bamboo.view.startup;

import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.ResourceLoader;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Hashtable;

public class SettingsPanel extends JPanel
{
    private ConfigurationPanel configurationPanel;
    private JSlider slider;
    private JLabel[] labelImage = new JLabel[4];
    private Mode mode = Mode.MULTI;
    private int labelImagesOffset = 2;
    private int boardSize = 5;

    public SettingsPanel()
    {
        setBackground(Colour.background());
        setLayout(new GridLayout(4, 3));
        setVisible(true);

        configurationPanel = new ConfigurationPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Colour.background());
        buttonPanel.setLayout(null);

        slider = new JSlider(JSlider.HORIZONTAL, 2, 5, boardSize);
        slider.addChangeListener(e -> {
            JSlider src = (JSlider) e.getSource();
            if (src.getValueIsAdjusting())
                boardSize = slider.getValue();
            changeBoardImage2(boardSize);
        });

        slider.setPaintLabels(true);
        slider.setBackground(Colour.background());
        slider.setLabelTable(buildHashtableOfPositions());

        Button multiBtn = new Button("btnMulti.png");
        multiBtn.setBounds(100, 50, 145, 55);
        multiBtn.addActionListener(e -> selectMulti());

        Button singleBtn = new Button("btnSingle.png");
        singleBtn.setBounds(250, 50, 145, 55);
        singleBtn.addActionListener(e -> selectSingle());

        Button demoBtn = new Button("btnDemo.png");
        demoBtn.setBounds(400, 50, 145, 55);
        demoBtn.addActionListener(e -> selectDemo());

        buttonPanel.add(multiBtn);
        buttonPanel.add(singleBtn);
        buttonPanel.add(demoBtn);

        JPanel panelImage = buildImagePanel();
        panelImage.setBackground(Colour.background());

        add(buttonPanel);
        add(panelImage);
        add(slider);
        add(configurationPanel);
    }

    private JPanel buildImagePanel()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Colour.background());

        labelImage[0] = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardSizeDim2.png")));
        labelImage[0].setVisible(false);
        labelImage[1] = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardSizeDim3.png")));
        labelImage[1].setVisible(false);
        labelImage[2] = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardSizeDim4.png")));
        labelImage[2].setVisible(false);
        labelImage[3] = new JLabel(new ImageIcon(ResourceLoader.getImage("BoardSizeDim5.png")));

        panel.add(labelImage[0]);
        panel.add(labelImage[1]);
        panel.add(labelImage[2]);
        panel.add(labelImage[3]);
        return panel;
    }

    private Hashtable buildHashtableOfPositions()
    {
        Hashtable position = new Hashtable();
        position.put(2, new JLabel("2"));
        position.put(3, new JLabel("3"));
        position.put(4, new JLabel("4"));
        position.put(5, new JLabel("5"));
        position.put(6, new JLabel("6"));
        position.put(7, new JLabel("7"));
        return position;
    }

    private void changeBoardImage2(int size)
    {
        size = size - labelImagesOffset;
        for(int i = 0; i < labelImage.length; i++)
        {
            if(i != size)
                labelImage[i].setVisible(false);
        }
        labelImage[size].setVisible(true);
    }

    public ConfigurationPanel getConfigurationPanel() {
        return configurationPanel;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void selectMulti()
    {
        mode = Mode.MULTI;
        configurationPanel.setVisible(true);
    }

    public void selectSingle()
    {
        mode = Mode.SINGLE;
        configurationPanel.setVisible(false);
    }

    public void selectDemo()
    {
        mode = Mode.DEMO;
        configurationPanel.setVisible(false);
    }

    public Mode getMode()
    {
        return mode;
    }
}
