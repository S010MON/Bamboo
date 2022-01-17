package Bamboo.controllerTesting;

import Bamboo.controller.Agent;
import Bamboo.controller.Settings;
import Bamboo.controller.random.Random;

import java.awt.Color;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SettingsTest
{
    @Test void currentPlayerNotNull()
    {
        Agent agent1 = new Random(Color.BLUE);
        Agent agent2 = new Random(Color.RED);
        Settings settings = new Settings(agent1, agent2, 5);
        assertNotNull(settings.getCurrentPlayer());
    }
}
