package Bamboo.view.startup;

import javax.swing.*;
import Bamboo.view.resources.ResourceLoader;
import java.awt.*;


public class Label  extends JLabel {
    public Label(String iconName){
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(158, 208, 239));
        this.setIcon(ResourceLoader.getIcon(iconName));

    }
}
