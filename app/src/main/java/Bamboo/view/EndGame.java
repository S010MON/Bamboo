package Bamboo.view;

import Bamboo.controller.Agent;
import Bamboo.view.resources.ResourceLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class EndGame extends JPanel
{
    private Dimension screenSize;
    private Agent winner;

    public EndGame(Agent winner, Dimension screenSize, MainFrame view)
    {
        this.winner = winner;
        this.screenSize = screenSize;
        this.addMouseListener(
                new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        view.showMenu();
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                }
        );
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

        g2d.setColor(Color.WHITE);
        x = (int) screenSize.getWidth()/2 - 50;
        y = (int) screenSize.getHeight()/2 + 50;
        g2d.drawString(winner.getName() + " won!", x, y);

        x = (int) screenSize.getWidth()/2 - 90;
        y = (int) screenSize.getHeight()/2 + 70;
        g2d.drawString("Click anywhere to continue", x, y);
    }
}
