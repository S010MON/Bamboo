package Bamboo.view;

import Bamboo.controller.AxialVector;
import Bamboo.controller.CubeVector;
import Bamboo.controller.GameLogic;
import Bamboo.controller.VectorConverter;
import Bamboo.model.Game;
import Bamboo.model.Tile;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.List;


public class RollOverListener implements MouseMotionListener {

    private int legality_indication_threshold = 0;

    private static HashMap<Integer, Tile> legal_moves_red;
    private static HashMap<Integer, Tile> legal_moves_blue;
    private int previous_groups_red = 0;
    private int previous_groups_blue = 0;

    private Game game;
    private Canvas canvas;

    public RollOverListener(Game game, Canvas canvas) {
        legal_moves_red = new HashMap<>();
        legal_moves_blue = new HashMap<>();
        this.game = game;
        this.canvas = canvas;
        for(Tile tile : game.getGrid().getAllTiles()){
            legal_moves_red.put(tile.getVector().hashCode(), tile);
            legal_moves_blue.put(tile.getVector().hashCode(), tile);
        }
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

                if(game.getTurn_count(game.getCurrentPlayer()) >= legality_indication_threshold){
                    if(game.getCurrentPlayer().getColor() == Color.RED && !legal_moves_red.containsKey(tile.getVector().hashCode()))
                        tile.setOutline(Color.BLACK);
                    else if(game.getCurrentPlayer().getColor() == Color.BLUE && !legal_moves_blue.containsKey(tile.getVector().hashCode()))
                        tile.setOutline(Color.BLACK);
                }

                tile.setCircle_thickness(new BasicStroke(6));
                canvas.repaint();
            } else {
                tile.setOutline(Color.black);
                tile.setCircle_thickness(new BasicStroke(3));

                canvas.repaint();
            }

        }

    }

    public static void update_legal_move_map(Game game, CubeVector move, Color player_color){
        List<Tile> neighbours = game.getGrid().getAllNeighbours(move);
        legal_moves_red.remove(move.hashCode());
        legal_moves_blue.remove(move.hashCode());
        for(Tile tile : neighbours){
            if(GameLogic.is_legal_move(game, tile, player_color)){
                if(player_color == Color.red && legal_moves_red.containsKey(tile.getVector().hashCode()))
                    legal_moves_red.remove(tile.getVector().hashCode());
                else if(legal_moves_blue.containsKey(tile.getVector().hashCode()))
                    legal_moves_blue.remove(tile.getVector().hashCode());
            }
        }
    }
}

