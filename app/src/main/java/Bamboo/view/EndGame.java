package Bamboo.view;

import Bamboo.controller.Agent;
import Bamboo.view.resources.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class EndGame extends JPanel
{
    private Dimension screenSize;
    private Agent winner;

    public EndGame(Agent winner, Dimension screenSize)
    {
        this.winner = winner;
        this.screenSize = screenSize;
        setBackground(Color.BLACK);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        int imgWidth = 474;
        int imgHeight = 267;
        int x = (int) screenSize.getWidth()/2 - imgWidth/2;
        int y = (int) screenSize.getHeight()/2 - imgHeight/2;

        BufferedImage img = ResourceLoader.getImage("gameOver.jpeg");
        g2d.drawImage(img, x, y, imgWidth, imgHeight,null);
    }
}
