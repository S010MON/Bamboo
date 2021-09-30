package Bamboo.view.startup;

import Bamboo.view.resources.Button;
import Bamboo.view.resources.Colour;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel
{
    private JPanel buttonPanel;
    private Bamboo.view.resources.Button hvh;
    private Bamboo.view.resources.Button hva;
    private Bamboo.view.resources.Button ava;
    private ConfigurationPanel configurationPanel;

    public SettingsPanel()
    {
        setBackground(Colour.background());
        setLayout(new GridLayout(2,1));
        setVisible(true);

        configurationPanel = new ConfigurationPanel();
        buttonPanel = new JPanel();
        buttonPanel.setBackground(Colour.background());
        buttonPanel.setLayout(null);

        hvh = new Bamboo.view.resources.Button("btnMulti.png");
        hvh.setBounds(100,50,145,55);
        hvh.addActionListener(e -> configurationPanel.setVisible(true));

        hva = new Bamboo.view.resources.Button("btnSingle.png");
        hva.setBounds(250,50,145,55);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        ava = new Button("btnDemo.png");
        ava.setBounds(400,50,145,55);
        hva.addActionListener(e -> configurationPanel.setVisible(false));

        buttonPanel.add(hvh);
        buttonPanel.add(hva);
        buttonPanel.add(ava);
        add(buttonPanel);

        add(configurationPanel);
    }

    public ConfigurationPanel getConfigurationPanel(){
        return configurationPanel ;
    }
}
