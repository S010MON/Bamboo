package Bamboo.controller;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FilePath
{
    private static final String internalPath = "/app/src/main/java/saved/";

    /**
     * Creates a file path to saved games with the correct file name appended
     */
    public static String getFilePath(String fileName)
    {
        FileSystem fileSystem = FileSystems.getDefault();
        String path = fileSystem.getPath("").toAbsolutePath().toString();
        return path.concat(internalPath + fileName);
    }
}
