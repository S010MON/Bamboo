package Bamboo.view.startup;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class HelpPanel extends JPanel
{
    public HelpPanel()
    {
        setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        int x = 0;
        int y = 0;
        int imgWidth = 1004;
        int imgHeight = 822;
        g2d.drawImage(loadImage(), x, y, imgWidth, imgHeight,null);
    }

    public BufferedImage loadImage()
    {
        FileSystem fileSystem = FileSystems.getDefault();
        String systemPath = fileSystem.getPath("").toAbsolutePath().toString();
        String appPath = "/app/src/main/java/Bamboo/view/resources/";
        String fileName = "rules.png";
        String path = systemPath + appPath + fileName;

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
