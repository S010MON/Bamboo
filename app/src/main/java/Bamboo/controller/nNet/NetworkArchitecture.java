package Bamboo.controller.nNet;

import Bamboo.controller.FilePath;

public enum NetworkArchitecture {
    TEST,
    BASIC;

    public static String getFilePath(NetworkArchitecture na){
        String path = FilePath.getNNetPath("");
        return switch (na) {
            case TEST -> path.concat("test/");
            case BASIC -> path.concat("basic/");
        };
    }
}
