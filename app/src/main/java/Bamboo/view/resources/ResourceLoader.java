package Bamboo.view.resources;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ResourceLoader
{
    public Icon getIcon(String name)
    {
        URL url = getClass().getClassLoader().getResource(name);
        BufferedImage image;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            image = null;
            System.out.println(name + " not found");
            e.printStackTrace();
        }
        return new ImageIcon(image);
    }

    public BufferedImage getImage(String name)
    {
        URL url = getClass().getClassLoader().getResource(name);
        BufferedImage image;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            image = null;
            System.out.println(name + " not found");
            e.printStackTrace();
        }
        return image;
    }

}