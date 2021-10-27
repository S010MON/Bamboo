package Bamboo.model;

import Bamboo.controller.Vector;

import java.awt.Color;
import java.util.Collections;
import java.util.Stack;

public class NodeMCTS extends Node
{
    private Color colour;

    private final double UCB_CONST = 1;
    private int plays;
    private int wins;
    private int visits;
    private int possibleMoves;

    public NodeMCTS(Grid new_grid, Color colour)
    {
        super(new_grid);
        plays = 0;
        wins = 0;
        visits = 0;

        this.colour = colour;
    }

    public Node select()
    {
        if(children.isEmpty())
        {

        }
        return null;
    }

    public void expand()
    {

    }

    /**
     * Plays the game randomly until termination
     * @param colour - the current colour of the game
     * @return  if win -> 1,
     *          else -> -1
     */
    public int playout(Color colour)
    {
        Color startingColour = colour;
        Stack<Vector> moves = collectRemainingMoves(grid);

        boolean keepGoing = true;
        while (keepGoing)
        {
            if(grid.isLegalMove(moves.peek(), colour))
            {
                grid.setTile(moves.pop(), colour);
                colour = toggleColour(colour);
            }
            else
            {
                keepGoing = false;
                if(colour == startingColour)
                    return -1;
                else
                    return 1;
            }
        }
        return 0;
    }

    /**
     * Calculate the Upper Confidence Bound for the current node
     * @param parent_visits
     * @return
     */
    public double getUCBscore(int parent_visits)
    {
        double x_bar = wins/plays;
        double C = UCB_CONST;
        double frac = Math.log(parent_visits) / visits;
        return x_bar + (C * Math.sqrt(frac));
    }

    /**
     * @return a shuffled stack of all the remaining
     * available moves in the current grid
     */
    private Stack<Vector> collectRemainingMoves(Grid grid)
    {
        Stack<Vector> stack = new Stack<>();
        for(Tile t: grid.getAllTiles())
        {
            if(!t.isCouloured())
                stack.add(t.getVector());
        }
        Collections.shuffle(stack);
        return stack;
    }

    private Color toggleColour(Color colour)
    {
        if(colour == Color.BLUE)
            return Color.RED;

        return Color.BLUE;
    }
}
