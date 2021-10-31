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
        this.grid = grid.copy();
        this.colour = colour;
    }

    /**
     * Select the next node from those explored
     */
    public NodeMCTS selectBest()
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
    public NodeMCTS select()
    {
        /* children not fully explored? -> select randomly */
        if(!unexplored.isEmpty())
        {
            Vector v = selectNextLegalMove();
            if(v != null)
            {
                Grid gridCopy = grid.copy();
                gridCopy.setTile(v, colour);
                return new NodeMCTS(gridCopy, v, toggleColour(colour), this);
            }
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

    public void expand(NodeMCTS node)
    {
        children.add(node);
    }

    /**
     * Plays the game randomly until termination
     * @return  if win -> 1,
     *          else -> 0
     */
    public int playout()
    {
        Color startingColour = colour;
        Color currrentColour = colour;
        Stack<Vector> moves = collectRemainingMoves(grid);

        while (!moves.isEmpty())
        {
            if(grid.isLegalMove(moves.peek(), currrentColour))
            {
                grid.setTile(moves.pop(), currrentColour);
                if(grid.isFinished(currrentColour))
                {
                    if (currrentColour == startingColour)
                        return 1;
                    else
                        return 0;
                }
                colour = toggleColour(currrentColour);
            }
            else
                moves.pop();
        }
        if (currrentColour == startingColour)
            return 1;
        else
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
        return UCB.calculate(wins, plays, parent_visits, visits);
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
        Vector selected = null;
        while(!unexplored.isEmpty())
        {
            selected = unexplored.pop();
            if(grid.isLegalMove(selected, colour))
                return selected;
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
