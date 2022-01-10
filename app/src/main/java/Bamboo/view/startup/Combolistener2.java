package Bamboo.view.startup;

import Bamboo.controller.AgentType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Combolistener2 implements ActionListener {
    private DemoConfigurationPanel demoConfigurationPanel;
    private JComboBox comb;
    private JComboBox comb2;

    public Combolistener2(DemoConfigurationPanel demoConfigurationPanel,JComboBox comb, JComboBox comb2){
    this.demoConfigurationPanel=demoConfigurationPanel;
    this.comb=comb;
    this.comb2=comb2;
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(checkNN(comb,comb2)) {
            demoConfigurationPanel.getSettingsPanel().removeSlider();
            demoConfigurationPanel.getSettingsPanel().changeBoardImage2(5);
            demoConfigurationPanel.getSettingsPanel().repaint();}
        else{
            demoConfigurationPanel.getSettingsPanel().removeSliderNN();
            demoConfigurationPanel.getSettingsPanel().changeBoardImage2(demoConfigurationPanel.getSettingsPanel().getBoardSize());}
            demoConfigurationPanel.getSettingsPanel().repaint();
    }

    public boolean checkNN(JComboBox comb, JComboBox comb2){
        if(comb.getSelectedItem()==AgentType.NEURAL_NET
                ||comb.getSelectedItem()==AgentType.HYBRID_NNMM
                ||comb2.getSelectedItem()==AgentType.NEURAL_NET
                ||comb2.getSelectedItem()==AgentType.HYBRID_NNMM)
            return true;
        else
            return false;
    }
}

