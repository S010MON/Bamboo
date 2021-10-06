package Bamboo;

import Bamboo.controller.Vector;

import java.awt.*;
import java.util.HashMap;

public class MovesLists
{

    public static HashMap<Vector, Color> getMoves()
    {
        HashMap<Vector, Color> moves = new HashMap<>();
        moves.put(new Vector(1,0,-1), Color.BLUE);
        return moves;
    }

}
