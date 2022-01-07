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

    public ComboListener(SingleConfigurationPanel singleConfigurationPanel, JComboBox comb){
        this.singleConfigurationPanel=singleConfigurationPanel;
        this.comb=comb;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(checkNN(comb)) {

            singleConfigurationPanel.getSettingsPanel().removeSlider();
            singleConfigurationPanel.getSettingsPanel().repaint();
            singleConfigurationPanel.getSettingsPanel().changeBoardImage2(5);
        }
        else {
            singleConfigurationPanel.getSettingsPanel().removeSliderNN();
            singleConfigurationPanel.getSettingsPanel().changeBoardImage2(singleConfigurationPanel.getSettingsPanel().getBoardSize());}
            singleConfigurationPanel.getSettingsPanel().repaint();
        }
    public boolean checkNN(JComboBox comb){
        if(comb.getSelectedItem()==AgentType.NEURAL_NET||comb.getSelectedItem()==AgentType.HYBRID_NNMM)
            return true ;
        else
            return false ;
    }
}

