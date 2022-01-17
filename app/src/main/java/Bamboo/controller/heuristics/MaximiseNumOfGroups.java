package Bamboo.controller.heuristics;

import Bamboo.controller.Vector;
import Bamboo.model.*;

import java.awt.Color;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

public class MaximiseNumOfGroups implements Heuristic
{
    private Grid grid;
    private Color colour;

    @Override
    public Vector getNextMove(Grid grid, Color colour)
    {
        this.grid = grid;
        this.colour = colour;
        Comparator<Vector> comparator = new GroupsComparator();
        Queue<Vector> queue = new PriorityQueue<>(grid.getAllVectors().size(),comparator);
        ArrayList<Tile> tiles = (ArrayList<Tile>) grid.getAllTiles();
        Collections.shuffle(tiles);
        for (Tile t : tiles) {
            if (!t.isCouloured())
                queue.add(t.getVector());
        }
        Vector v;
        while (!queue.isEmpty()) {
            v = queue.remove();
            if (grid.isLegalMove(v, colour))
                return v;
        }
        return null;
    }

    @Override
    public String getType() {
        return "MaximiseNumOfGroups";
    }

    class GroupsComparator implements Comparator<Vector> {
        public int compare(Vector x, Vector y) {
            Color currentColor = colour;
            Grid gridX = grid.copy();
            Grid gridY = grid.copy();
            gridX.getTile(x).setColour(currentColor);
            gridY.getTile(y).setColour(currentColor);
            int xGroups = gridX.getAllGroupsOfColour(currentColor).size();
            int yGroups = gridY.getAllGroupsOfColour(currentColor).size();
            return Integer.compare(yGroups, xGroups);
        }
    }
}
