package Bamboo;

import Bamboo.controller.Settings;
import Bamboo.model.Game;
import Bamboo.model.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    @Test
    void testGraphImplementation()
    {
        Game game = new Game(Settings.getDefaultSetting());
        Graph graph = game.getGameAsGraph();
        graph.printEdges();
        graph.printNodes();
    }
}
