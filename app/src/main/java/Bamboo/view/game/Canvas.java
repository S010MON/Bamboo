package Bamboo.view.game;

import Bamboo.controller.AxialVector;
import Bamboo.controller.Vector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Tile;
import Bamboo.view.resources.Colour;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Canvas extends JPanel
{
    private Game game;

    private static int circle_radius = 50;
    private BasicStroke circle_thickness = new BasicStroke(2);
    private static int centreX;
    private static int centreY;
    private int offsetX = 90;
    private int offsetY = 0;
    private boolean hint ;
    private int demoDelay = 1000;

    public Tile previous_rollover = new Tile(new Vector(0,0,0));
    private Color background = Colour.background();
    private RollOverListener rollOverListener;

    public Canvas(Dimension screenSize, Game game)
    {
        this.hint = false ;
        this.game = game;
        centreX = (screenSize.width/2) - circle_radius - offsetX;
        centreY = (screenSize.height/2) - circle_radius - offsetY;
        setSize(screenSize.width, screenSize.height);
        addMouseListener(new TileClickListener(game, this));
        rollOverListener = new RollOverListener(game, this);
        addMouseMotionListener(rollOverListener);

              Timer timer2 = new Timer(demoDelay, new TimerListener(game, this));
              timer2.start();
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

        g2d.setStroke(circle_thickness);
        for(Tile tile: game.getAllTiles())
        {
            colorTile(tile, tile.getColour(), g2d);

            if(hint) {
                colorLegalTiles(tile, g2d);
            }
        }
        Tile rollover = rollOverListener.getRolloverTile();
        if(rollover != null){
            Color color;
            if(game.getGrid().isLegalMove(rollover.getVector(), game.getCurrentPlayer().getColor())){
                color = game.getCurrentPlayer().getColor();
            }
            else{
                color = Color.GRAY;
            }
            previous_rollover = rollover;
            colorTile(rollover, color, g2d);
        }
        else{
            colorTile(previous_rollover, previous_rollover.getColour(), g2d);

            if (hint) {
                if (game.getGrid().isLegalMove(previous_rollover.getVector(), game.getCurrentPlayer().getColor())) {
                    colorLegalTiles(previous_rollover, g2d);
                }
            }
        }
    }

    private void colorTile(Tile tile, Color color, Graphics2D g2d){
        AxialVector v = VectorConverter.convertToAxial(tile.getVector());
        v = VectorConverter.doubleAndOffsetOddRows(v);
        int x = centreX + (v.getQ() * circle_radius/2) ;
        int y = centreY + (v.getR() * circle_radius/2) ;

        g2d.setStroke(tile.getCircle_thickness());
        g2d.setColor(color);
        g2d.fillOval(x,y,circle_radius,circle_radius);
        g2d.setColor(tile.getOutline());
        g2d.drawOval(x, y, circle_radius, circle_radius);
    }

    public static int getCircle_radius() {
        return circle_radius;
    }

    public static int getCentreX() {
        return centreX;
    }

    public static int getCentreY() {
        return centreY;
    }

    public void colorLegalTiles(Tile tile, Graphics2D g2d){

        AxialVector v = VectorConverter.convertToAxial(tile.getVector());
        v = VectorConverter.doubleAndOffsetOddRows(v);
        int x = centreX + (v.getQ() * circle_radius/2) ;
        int y = centreY + (v.getR() * circle_radius/2) ;

        if(game.getGrid().isLegalMove(tile.getVector(), game.getCurrentPlayer().getColor()))
        {
            g2d.setStroke(tile.getCircle_thickness());
            g2d.setColor(new Color(44, 154, 21));
            g2d.fillOval(x+14,y+14,circle_radius-30,circle_radius-30);
            g2d.setColor(tile.getOutline());
            g2d.drawOval(x, y, circle_radius, circle_radius);
        }
    }

    public void changeHint() {
        if (!hint)
            hint = true;
        else
            hint = false;
    }

    public void changeHintToFalse(){
        hint = false ;
    }
}

