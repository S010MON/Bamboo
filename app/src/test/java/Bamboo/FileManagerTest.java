package Bamboo;

import Bamboo.controller.FileManager;
import Bamboo.controller.Settings;
import Bamboo.controller.Vector;

import java.awt.*;
import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest
{
    @Test void testSettingsReader()
    {
        File file = new File(getFilePath("test_saved_game"));
        Settings settings = null;
        try {
            settings = FileManager.readSettingsFromFile(file);
        } catch (Exception e) {
            System.out.println("Shit happened!");
        }
        assertEquals(5, settings.boardSize);
        assertEquals("joe", settings.player1.getName());
        assertEquals("bob", settings.player2.getName());
        assertEquals(Color.RED, settings.player1.getColor());
        assertEquals(Color.BLUE, settings.player2.getColor());
    }

    @Test void testTilesReader()
    {
        File file = new File(getFilePath("test_saved_game"));
        HashMap<Vector, Color> tiles = new HashMap<>();
        try {
            tiles = FileManager.readTilesFromFile(file);
        } catch (Exception e) {
            System.out.println("Shit happened again!");
        }

        assertEquals(tiles.get(new Vector(0,0,0)), Color.BLUE);
        assertEquals(tiles.get(new Vector(0,1,-1)), Color.RED);
    }


    /**
     * Creates a file path to saved games with the correct file name appended
     */
    private static String getFilePath(String fileName)
    {
        FileSystem fileSystem = FileSystems.getDefault();
        String path = fileSystem.getPath("").toAbsolutePath().toString();
        return path.concat("/src/main/java/saved/" + fileName);
    }
}
