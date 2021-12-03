package Bamboo.view.resources;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;

public class Label extends JLabel
{
    public Label(String iconName)
    {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Colour.background());
        this.setIcon(new ResourceLoader().getIcon(iconName));
    }
}
