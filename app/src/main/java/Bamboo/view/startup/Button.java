package Bamboo.view.startup;

import Bamboo.view.resources.ResourceLoader;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton
{
    public Button(String iconName)
    {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(158, 208, 239));
        this.setIcon(ResourceLoader.getIcon(iconName));
    }
}
