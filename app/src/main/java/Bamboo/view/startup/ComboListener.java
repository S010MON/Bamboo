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
    private SingleConfigurationPanel singleConfigurationPanel;
    private JComboBox comb;
    public ComboListener(DemoConfigurationPanel demoConfigurationPanel, JComboBox comb){
        this.demoConfigurationPanel= demoConfigurationPanel;
        this.comb=comb;
    }

    public ComboListener(SingleConfigurationPanel singleConfigurationPanel, JComboBox comb){
        this.singleConfigurationPanel=singleConfigurationPanel;
        this.comb=comb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // TODO look at the change in name, if NNet is selected, then block the SLiderListener
        if(comb.getSelectedItem()==AgentType.NEURAL_NET) {
           // demoConfigurationPanel.getSliderListener().block();
            if(demoConfigurationPanel!=null){
            demoConfigurationPanel.getSettingsPanel().removeSlider();
            demoConfigurationPanel.getSettingsPanel().repaint();
            demoConfigurationPanel.getSettingsPanel().checkNNtoTrue();
            demoConfigurationPanel.getSettingsPanel().changeBoardImage2(5);}

            if(singleConfigurationPanel!=null){
            singleConfigurationPanel.getSettingsPanel().removeSlider();
            singleConfigurationPanel.getSettingsPanel().repaint();
            singleConfigurationPanel.getSettingsPanel().checkNNtoTrue();
            singleConfigurationPanel.getSettingsPanel().changeBoardImage2(5);}

        }
        else {
            if(demoConfigurationPanel!=null){
            demoConfigurationPanel.getSettingsPanel().removeSliderNN();
            demoConfigurationPanel.getSettingsPanel().repaint();
            demoConfigurationPanel.getSettingsPanel().checkNNtoFalse();
            demoConfigurationPanel.getSettingsPanel().changeBoardImage2(demoConfigurationPanel.getSettingsPanel().getBoardSize());}

            if(singleConfigurationPanel!=null){
            singleConfigurationPanel.getSettingsPanel().removeSliderNN();
            singleConfigurationPanel.getSettingsPanel().repaint();
            singleConfigurationPanel.getSettingsPanel().checkNNtoFalse();
            singleConfigurationPanel.getSettingsPanel().changeBoardImage2(singleConfigurationPanel.getSettingsPanel().getBoardSize());}
        }
    }
}
