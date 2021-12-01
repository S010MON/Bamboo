package Bamboo;

import Bamboo.controller.FilePath;
import Bamboo.controller.nNet.NetworkTraining;
import Bamboo.view.MainFrame;

import java.io.IOException;
import java.net.URL;

public class App {
    // Test for CI/CD
    public static void main(String[] args) throws IOException
    {
        //Test test = new Test();
        if(false)
            NetworkTraining.train();
        MainFrame frame = new MainFrame() ;
        frame.setVisible(true);
    }
}

class Test
{
    public Test() {
        URL url = getClass().getResource("blue.png");
        System.out.println(url.toString());
    }
}
