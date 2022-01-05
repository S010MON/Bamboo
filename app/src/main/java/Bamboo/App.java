package Bamboo;

import Bamboo.controller.nNet.NetworkTraining;
import Bamboo.view.MainFrame;

import java.io.IOException;

public class App
{
    public static void main(String[] args) throws IOException {
        NetworkTraining.train();
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
