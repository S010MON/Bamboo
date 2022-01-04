package Bamboo.view.startup;

import Bamboo.controller.AgentType;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

public class ComboListener implements ActionListener
{
    private DemoConfigurationPanel demoConfigurationPanel;
    private JComboBox comb;
    public ComboListener(DemoConfigurationPanel demoConfigurationPanel, JComboBox comb){
        this.demoConfigurationPanel=demoConfigurationPanel;
        this.comb=comb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO look at the change in name, if NNet is selected, then block the SLiderListener
        if(comb.getSelectedItem()==AgentType.NEURAL_NET) {
            demoConfigurationPanel.getSliderListener().block();
            demoConfigurationPanel.getSettingsPanel().removeSlider();
            demoConfigurationPanel.getSettingsPanel().repaint();
        }
        else {
            demoConfigurationPanel.getSliderListener().unBlock();
            demoConfigurationPanel.getSettingsPanel().removeSliderNN();
            demoConfigurationPanel.getSettingsPanel().repaint();
        }


    }
}
