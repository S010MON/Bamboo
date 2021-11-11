package Bamboo;

import Bamboo.controller.nNet.NetworkTraining;
import Bamboo.view.MainFrame;

import java.io.IOException;

public class App {
    // Test for CI/CD
    public static void main(String[] args) throws IOException {
        if(true)
            NetworkTraining.train();
        MainFrame frame = new MainFrame() ;
        frame.setVisible(true);
    }
}
