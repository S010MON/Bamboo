package Bamboo.controller.MCTS;

import Bamboo.controller.Vector;
import Bamboo.model.Grid;
import Bamboo.model.Tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class NodeMCTS
{
    private Color colour;

    private final double UCB_CONST = 1;
    private int plays;
    private int wins;
    private int visits;
    private Grid grid;
    private Vector move;
    private NodeMCTS parent;
    private ArrayList<NodeMCTS> children;
    private Stack<Vector> unexplored;

    public NodeMCTS(Grid grid, Vector move, Color colour, NodeMCTS parent)
    {
        plays = 0;
        wins = 0;
        visits = 1;
        unexplored = collectRemainingMoves(grid);
        children =  new ArrayList<>();
        this.parent = parent;
        this.move = move;
        this.grid = grid;
        this.colour = colour;
    }

    /**
     * Select the next node from those explored
     */
    public NodeMCTS select()
    {
        NodeMCTS best = children.get(0);
        double bestUCB = best.getUCBscore(visits);
        for(NodeMCTS n: children)
        {
            if(n.getUCBscore(visits) > bestUCB)
            {
                best = n;
                bestUCB = n.getUCBscore(visits);
            }
        }
        return best;
    }

    /**
     * Select the next node and expand it
     */
    public NodeMCTS selectAndExpand()
    {
        /* children not fully explored? -> select randomly */
        if(!unexplored.isEmpty())
        {
            Vector v = selectNextLegalMove();
            Grid gridCopy = grid.copy();
            grid.setTile(v, colour);
            return new NodeMCTS(gridCopy, v, toggleColour(colour), this);
        }

        /* children explored? -> select by UCB */
        NodeMCTS best = children.get(0);
        double bestUCB = best.getUCBscore(visits);
        for(NodeMCTS n: children)
        {
            if(n.getUCBscore(visits) > bestUCB)
            {
                best = n;
                bestUCB = n.getUCBscore(visits);
            }
        }
        return best;
    }

    /**
     * Plays the game randomly until termination
     * @return  if win -> 1,
     *          else -> 0
     */
    public int playout()
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
                    return 0;
                else
                    return 1;
            }
        }
        return 0;
    }

    /**
     * Feeds back all results to the root node
     * @param result - {0, 1} the result of the playout
     */
    public void backProp(int result)
    {
        if(parent != null)
        {
            parent.addResult(result);
            parent.incrementPlays();
            parent.incrementVisits();
        }
    }

    public void addResult(int result)
    {
        wins += result;
    }

    public void incrementVisits()
    {
        visits++;
    }

    public void incrementPlays()
    {
        plays++;
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

    public Vector getMove()
    {
        return move;
    }

    public boolean isLeaf()
    {
        return !children.isEmpty();
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

    private Vector selectNextLegalMove()
    {
        Vector selected = unexplored.pop();
        while(!grid.isLegalMove(selected, colour))
        {
            selected = unexplored.pop();
        }
        return selected;
    }

    private Color toggleColour(Color colour)
    {
        if(colour == Color.BLUE)
            return Color.RED;

        return Color.BLUE;
    }
}
