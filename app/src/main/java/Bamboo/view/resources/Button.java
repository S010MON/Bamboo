package Bamboo.view.resources;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;

public class Button extends JButton
{
    public Button(String iconName)
    {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(new Color(158, 208, 239));
        this.setIcon(ResourceLoader.getIcon(iconName));
    }

    public void changeIcon(String iconName)
    {
        this.setIcon(ResourceLoader.getIcon(iconName));
    }
}
