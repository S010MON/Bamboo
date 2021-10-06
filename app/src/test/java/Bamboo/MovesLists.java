package Bamboo;

import Bamboo.controller.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MovesLists
{
    public static List<Vector> getMoves()
    {
        ArrayList<Vector> V = new ArrayList<>();
        V.add(new Vector(1,0,-1));
        return V;
    }

    public static List<Color> getColours()
    {
        ArrayList<Color> C = new ArrayList<>();
        C.add(Color.BLUE);
        return C;
    }
}
