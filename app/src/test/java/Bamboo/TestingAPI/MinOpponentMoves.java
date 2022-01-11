package Bamboo.TestingAPI;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.MaximiseNumOfGroups;
import Bamboo.controller.random.Random;
import Bamboo.model.Game;
import Bamboo.model.Grid;
import Bamboo.model.GridGraphImp;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class MinOpponentMoves {

    @Test void test_one_remaining_slot(){
        //Game game = new Game();
        Grid grid = new GridGraphImp(1);
        grid.setTile(new Vector(0, 0, 0), Color.RED);
        grid.setTile(new Vector(0, 1, -1), Color.RED);
        grid.setTile(new Vector(1, 0, -1), Color.RED);
        grid.setTile(new Vector(1, -1, 0), Color.RED);
        grid.setTile(new Vector(-1, 1, 0), Color.RED);
        grid.setTile(new Vector(-1, 0, 1), Color.RED);
        MinOpponentMoves max = new MinOpponentMoves();
        Agent agent1 = new Random(Color.BLUE);
        Agent agent2 = new Random(Color.RED);
        Settings settings = new Settings(agent1, agent2, 5);


        Vector v = max.getNextMove(settings);



    }
}


