package Bamboo.view.resources;

import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class Label extends JLabel
{
    public Label(String iconName)
    {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Colour.BACKGROUND());
        this.setIcon(new ResourceLoader().getIcon(iconName));
    }
}
