package Bamboo.view.game;

import Bamboo.controller.AxialVector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Tile;
import Bamboo.view.resources.Colour;
import Bamboo.view.resources.ResourceLoader;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Canvas extends JPanel
{
    private Game game;

    private static int circle_radius = 50;
    private static int centreX;
    private static int centreY;
    private int offsetX = 90;
    private int offsetY = 0;
    private boolean hint ;
    private int demoDelay = 500;

    private Color background = Colour.background();
    private RollOverListener rollOverListener;
    private HashMap<Color, BufferedImage> images = new HashMap<>();

    public Canvas(Dimension screenSize, Game game)
    {
        this.hint = false;
        this.game = game;
        centreX = (screenSize.width/2) - circle_radius - offsetX;
        centreY = (screenSize.height/2) - circle_radius - offsetY;
        setSize(screenSize.width, screenSize.height);
        addMouseListener(new TileClickListener(game, this));
        rollOverListener = new RollOverListener(game, this);
        addMouseMotionListener(rollOverListener);

        images.put(Color.RED, ResourceLoader.getImage("red_circle.png"));
        images.put(Color.BLUE, ResourceLoader.getImage("blue_circle.png"));
        images.put(Color.WHITE,ResourceLoader.getImage("white_circle.png"));
        images.put(Color.GREEN,ResourceLoader.getImage("green_circle.png"));
        images.put(Color.GRAY,ResourceLoader.getImage("gray_circle.png"));

        Timer timer2 = new Timer(demoDelay, new TimerListener(game, this));
        timer2.start();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        paintGrid( (Graphics2D) g);
        setBackground(background);
    }

    private void paintGrid(Graphics2D g2d)
    {
        for(Tile tile: game.getAllTiles())
        {
            colorTile(tile, tile.getColour(), g2d);
            if(hint)
                colorGreen(tile, g2d);
        }

        Tile rollover = rollOverListener.getRolloverTile();
        if(rollover != null)
        {
            Color color = game.getCurrentPlayer().getColor();

            // If not legal move -> set to gray
            if(!game.getGrid().isLegalMove(rollover.getVector(), color))
                color = Color.GRAY;

            colorTile(rollover, color, g2d);
        }
    }

    public void colorGreen(Tile tile, Graphics2D g2d)
    {
        AxialVector v = VectorConverter.convertToAxial(tile.getVector());
        v = VectorConverter.doubleAndOffsetOddRows(v);
        int x = centreX + (v.getQ() * circle_radius/2);
        int y = centreY + (v.getR() * circle_radius/2);

        if(game.getGrid().isLegalMove(tile.getVector(), game.getCurrentPlayer().getColor()))
            g2d.drawImage(images.get(Color.GREEN),x+3,y+3,circle_radius-6,circle_radius-6,null);
        else if(tile.getColour() == Color.WHITE)
            g2d.drawImage(images.get(Color.GRAY), x, y, circle_radius, circle_radius, null);
    }

    private void colorTile(Tile tile, Color color, Graphics2D g2d)
    {
        AxialVector v = VectorConverter.convertToAxial(tile.getVector());
        v = VectorConverter.doubleAndOffsetOddRows(v);
        int x = centreX + (v.getQ() * circle_radius/2);
        int y = centreY + (v.getR() * circle_radius/2);

        g2d.drawImage(images.get(color), x, y, circle_radius, circle_radius, null);
    }

    public static int getCircle_radius()
    {
        return circle_radius;
    }

    public static int getCentreX()
    {
        return centreX;
    }

    public static int getCentreY()
    {
        return centreY;
    }

    public void changeHint()
    {
        hint = !hint;
    }

    public void changeHintToFalse()
    {
        hint = false;
    }
}

