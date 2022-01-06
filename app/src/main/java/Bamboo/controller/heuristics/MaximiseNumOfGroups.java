package Bamboo.controller.heuristics;
import Bamboo.controller.Vector;
import Bamboo.model.*;

import java.awt.*;
import java.util.*;
public class MaximiseNumOfGroups implements Heuristic{
    private Game game;

    @Override
    public Vector getNextMove(Game game) {
        this.game = game;
        Comparator<Vector> comparator = new GroupsComparator();
        Queue<Vector> queue = new PriorityQueue<>(game.getGrid().getAllVectors().size(),comparator);
        ArrayList<Tile> tiles = (ArrayList<Tile>) game.getAllTiles();
        Collections.shuffle(tiles);
        for (Tile t : tiles) {
            if (!t.isCouloured())
                queue.add(t.getVector());
        }
        Vector v;
        while (!queue.isEmpty()) {
            v = queue.remove();
            if (game.getGrid().isLegalMove(v, game.getCurrentPlayer().getColor()))
                return v;
        }
        return null;
    }
    class GroupsComparator implements Comparator<Vector> {
        public int compare(Vector x, Vector y) {
            Grid grid = game.getGrid();
            Color currentColor = game.getCurrentPlayer().getColor();
            Grid gridX = grid.copy();
            Grid gridY = grid.copy();
            gridX.getTile(x).setColour(currentColor);
            gridY.getTile(y).setColour(currentColor);
            int xGroups = gridX.getAllGroupsOfColour(currentColor).size();
            int yGroups = gridY.getAllGroupsOfColour(currentColor).size();
            if (xGroups< yGroups) {
                return 1;
            }
            if (xGroups > yGroups) {
                return -1;
            }
            return 0;
        }
    }
}
