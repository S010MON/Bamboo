package Bamboo.view;

import Bamboo.controller.AxialVector;
import Bamboo.controller.Vector;
import Bamboo.controller.GameLogic;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Tile;
import Bamboo.view.resources.Colour;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;


public class RollOverListener implements MouseMotionListener {

    private static Tile rolledOverTile = new Tile(new CubeVector(20,20,20));
    private int legality_indication_threshold = 0;

    private Game game;
    private Bamboo.view.game.Canvas canvas;

    public RollOverListener(Game game, Canvas canvas) {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

        int X = e.getX();
        int Y = e.getY();

        for (Tile tile : game.getAllTiles()) {
            AxialVector v = VectorConverter.convertToAxial(tile.getVector());
            v = VectorConverter.doubleAndOffsetOddRows(v);
            int r = canvas.getCircle_radius();
            int x = canvas.getCentreX() + (v.getQ() * r / 2) + r / 2;
            int y = canvas.getCentreY() + (v.getR() * r / 2) + r / 2;

            if (X > (x - r / 2)
                    && X < (x + r / 2)
                    && Y > (y - r / 2)
                    && Y < (y + r / 2)) {

                rolledOverTile = tile;
                canvas.repaint();
            } else {
                tile.setOutline(Color.black);
                tile.setCircle_thickness(new BasicStroke(3));

                canvas.repaint();
            }

        }

    }

    public static Tile getRolloverTile(){
        return rolledOverTile;
    }

}

