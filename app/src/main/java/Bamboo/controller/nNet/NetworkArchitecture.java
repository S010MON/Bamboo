package Bamboo.controller.nNet;

import Bamboo.controller.FilePath;

public enum NetworkArchitecture {
    TEST,
    BASIC;

    public static String getFolder(NetworkArchitecture na){
        return switch (na) {
            case TEST -> "test/";
            case BASIC -> "basic/";
        };
    }
}
