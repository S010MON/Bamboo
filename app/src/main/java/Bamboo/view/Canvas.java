package Bamboo.view;

import Bamboo.controller.AxialVector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Tile;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Canvas extends JPanel
{
    private Game game;

    private int circle_radius = 50;
    private BasicStroke circle_thickness = new BasicStroke(2);
    private int centreX;
    private int centreY;

    private Color foreground = Color.white;
    private Color background = new Color(158, 208, 239) ;


    public Canvas(Dimension screenSize, Game game)
    {
        this.game = game;
        centreX = screenSize.width/2;
        centreY = screenSize.height/2;
        setSize(screenSize.width, screenSize.height);
        addMouseListener(new TileClickListener(game, this));
        addMouseMotionListener(new RollOverListener(game,this));
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
        //g2d.setColor(foreground);
        g2d.setStroke(circle_thickness);
        int i = 1 ;
        for(Tile tile: game.getAllTiles())
        {
            AxialVector v = VectorConverter.convertToAxial(tile.getVector());
            v = VectorConverter.doubleAndOffsetOddRows(v);
            int x = centreX + (v.getQ() * circle_radius/2) ;
            int y = centreY + (v.getR() * circle_radius/2) ;

            //System.out.println(i++ +" " +x+" "+y) ;

            g2d.setStroke(tile.getCircle_thickness());
            g2d.setColor(tile.getColour());
            g2d.fillOval(x,y,circle_radius,circle_radius);
            g2d.setColor(tile.getOutline());
            g2d.drawOval(x, y, circle_radius, circle_radius);
        }
    }

    public int getCircle_radius() {
        return circle_radius;
    }

    public int getCentreX() {
        return centreX;
    }

    public int getCentreY() {
        return centreY;
    }
}

