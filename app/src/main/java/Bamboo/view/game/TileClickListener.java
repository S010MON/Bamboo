package Bamboo.view.game;

import Bamboo.controller.AxialVector;
import Bamboo.controller.VectorConverter;
import Bamboo.model.GameWithGUI;
import Bamboo.model.Tile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TileClickListener implements MouseListener
{
    private GameWithGUI game;
    private Canvas canvas;

    public TileClickListener(GameWithGUI game, Canvas canvas)
    {
        this.game = game;
        this.canvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (game.currentPlayerHuman())
        {
            for (Tile tile : game.getAllTiles())
            {
                AxialVector v = VectorConverter.convertToAxial(tile.getVector());
                v = VectorConverter.doubleAndOffsetOddRows(v);
                int r = canvas.getCircle_radius();
                int x = canvas.getCentreX() + (v.getQ() * r / 2) + r / 2;
                int y = canvas.getCentreY() + (v.getR() * r / 2) + r / 2;

                if (e.getX() > (x - r / 2)
                        && e.getX() < (x + r / 2)
                        && e.getY() > (y - r / 2)
                        && e.getY() < (y + r / 2)) {
                    if (game.getGrid().isLegalMove(tile.getVector(),game.getCurrentPlayer().getColor())) {
                        canvas.setHintFalse();
                    }
                    game.placeNextAt(tile.getVector());
                    canvas.repaint();

                    if(!tile.isCouloured()){
                        tile.color();
                    }
                }
            }

            if(game.nextPlayerAI())
            {
                game.placeNextAt(game.getCurrentPlayer().getNextMove(game));
                canvas.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited (MouseEvent e){}

 }
