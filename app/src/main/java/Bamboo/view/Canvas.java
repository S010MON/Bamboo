package Bamboo.view;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel
{
    private int circle_radius = 10;
    private int circle_thickness = 2;

    private Color foreground;

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(foreground);
        g2d.drawOval(100,100,50,50);
    }
}
