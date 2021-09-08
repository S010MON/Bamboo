package Bamboo.view;

import Bamboo.controller.AxialVector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Tile;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class TileClickListener implements MouseListener
{
    private Game game;
    private Canvas canvas;

    public TileClickListener(Game game, Canvas canvas)
    {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(game.currentPlayerHuman())
        {
            List<Tile> tileList = game.getAllTiles();

            for(Tile tile: game.getAllTiles())
            {
                AxialVector v = VectorConverter.convertToAxial(tile.getVector());
                v = VectorConverter.doubleAndOffsetOddRows(v);
                int x = canvas.getCentreX() + (v.getQ() * canvas.getCircle_radius()/2) + canvas.getCircle_radius()/2;
                int y = canvas.getCentreY() + (v.getR() * canvas.getCircle_radius()/2) + canvas.getCircle_radius()/2;

                if(e.getX()>(x-canvas.getCircle_radius()/2)
                        && e.getX()<(x+canvas.getCircle_radius()/2)
                        && e.getY()>(y-canvas.getCircle_radius()/2)
                        && e.getY()<(y+canvas.getCircle_radius()/2))
                {
                    game.placeNextAt(tile.getVector());
                    canvas.repaint();
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
