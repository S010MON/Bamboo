package Bamboo.view.game;

import Bamboo.controller.AxialVector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.GameWithGUI;
import Bamboo.model.Tile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


public class RollOverListener implements MouseMotionListener
{
    private Tile rolledOverTile = null;

    private GameWithGUI game;
    private Canvas canvas;

    public RollOverListener(GameWithGUI game, Canvas canvas) {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
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
                break ;
            } else {
                rolledOverTile = null ;
                canvas.repaint();
            }
        }
    }

    public Tile getRolloverTile(){
        return rolledOverTile;
    }

    @Override
    public void mouseDragged(MouseEvent e) {}
}

