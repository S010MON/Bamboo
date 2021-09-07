package Bamboo.view;

import Bamboo.controller.AxialVector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.Tile;

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel
{
    private Grid grid;

    private int circle_radius = 50;
    private BasicStroke circle_thickness = new BasicStroke(2);
    private int centreX;
    private int centreY;

    private Color foreground = Color.BLACK;
    private Color background = Color.WHITE;

    public Canvas(Dimension screenSize, Game game)
    {
        this.grid = game.getGrid();
        centreX = screenSize.width/2;
        centreY = screenSize.height/2;
        setSize(screenSize.width, screenSize.height);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        paintGrid(g2d);
        setBackground(background);
    }

    private void paintGrid(Graphics2D g2d)
    {
        g2d.setColor(foreground);
        g2d.setStroke(circle_thickness);
        for(Tile tile: grid.getAllTiles())
        {
            AxialVector v = VectorConverter.convertToAxial(tile.getVector());
            v = VectorConverter.doubleAndOffsetOddRows(v);
            int x = centreX + (v.getQ() * circle_radius/2);
            int y = centreY + (v.getR() * circle_radius/2);
            g2d.drawOval(x, y, circle_radius, circle_radius);
        }
    }


}