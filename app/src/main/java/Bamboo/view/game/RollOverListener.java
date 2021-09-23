package Bamboo.view.game;

import Bamboo.controller.AxialVector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Tile;
import Bamboo.view.game.Canvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class RollOverListener implements MouseMotionListener {

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

                tile.setOutline(new Color(56, 154, 51));
                tile.setCircle_thickness(new BasicStroke(6));
                canvas.repaint();
            } else {
                tile.setOutline(Color.black);
                tile.setCircle_thickness(new BasicStroke(3));

                canvas.repaint();
            }

        }

    }
}

