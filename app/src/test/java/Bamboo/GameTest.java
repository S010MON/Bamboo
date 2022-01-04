package Bamboo;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.controller.Vector;
import Bamboo.controller.random.Random;

import java.awt.*;
import java.util.ArrayList;

import Bamboo.model.Game;
import Bamboo.model.GameWithoutGUI;
import Bamboo.model.Tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest
{
    @Test void testCopy_blank_board()
    {
        Agent agent1 = new Random(Color.BLUE);
        Agent agent2 = new Random(Color.RED);
        Settings settings = new Settings(agent1, agent2, 5);
        Game game = new GameWithoutGUI(settings);
        Game gameCopy = game.copy();

        checkEquals(game, gameCopy);
    }

    @Test void testCopy_played_board()
    {
        Agent agent1 = new Random(Color.BLUE);
        Agent agent2 = new Random(Color.RED);
        Settings settings = new Settings(agent1, agent2, 5);
        Game g1 = new GameWithoutGUI(settings);
        g1.getGrid().setTile(new Vector(0,0,0), Color.BLUE);
        g1.getGrid().setTile(new Vector(0,1,-1), Color.RED);
        g1.getGrid().setTile(new Vector(0,2,-2), Color.BLUE);
        g1.getGrid().setTile(new Vector(0,3,-3), Color.RED);
        g1.getGrid().setTile(new Vector(2,-1,-1), Color.BLUE);

        Game g2 = g1.copy();

        checkEquals(g1, g2);
    }

    private void checkEquals(Game g1, Game g2)
    {
        assertEquals(g1.getCurrentPlayer().getName(), g2.getCurrentPlayer().getName());
        assertEquals(g1.getCurrentPlayer().getColor(), g2.getCurrentPlayer().getColor());
        assertEquals(g1.getCurrentPlayer().getType(), g2.getCurrentPlayer().getType());

        ArrayList<Tile> tiles1 = (ArrayList<Tile>) g1.getAllTiles();
        ArrayList<Tile> tiles2 = (ArrayList<Tile>) g2.getAllTiles();
        assertEquals(tiles1.size(), tiles2.size());

        for(int i = 0; i < tiles1.size(); i++)
        {
            assertEquals(tiles1.get(i).getColour(), tiles2.get(i).getColour());
            assertEquals(tiles1.get(i).getVector(), tiles2.get(i).getVector());
        }
    }
}
