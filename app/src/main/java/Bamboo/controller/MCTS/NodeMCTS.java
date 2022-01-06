package Bamboo.controller.MCTS;

import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.heuristics.OuterWeighted;
import Bamboo.model.Game;
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
    private ArrayList<NodeMCTS> children;
    private Stack<Vector> unexplored;

    public Heuristic heuristic = new OuterWeighted();

    public NodeMCTS(Grid grid, Vector move, Color colour)
    {
        this.move = move;
        this.grid = grid.copy();
        this.colour = colour;

        plays = 0;
        wins = 0;
        visits = 1;
        unexplored = collectRemainingMoves(grid);
        children =  new ArrayList<>();
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

    public NodeMCTS selectNode(Vector v)
    {
        for(NodeMCTS n: children)
        {
            if(n.getMove().equals(v))
                return n;
        }

        return new NodeMCTS(grid, v, toggleColour(colour));
    }

    /**
     * Select the next node and expand it
     */
    public int selectAndExpand()
    {
        visits++;
        /* children not fully explored? -> select randomly */
        if(!unexplored.isEmpty())
        {
            Vector v = selectNextLegalMove();
            if(v != null)
            {
                Grid gridCopy = grid.copy();
                gridCopy.setTile(v, colour);
                NodeMCTS n = new NodeMCTS(gridCopy, v, toggleColour(colour));
                children.add(n);
                int result = n.playout();
                wins += result;
                return result;
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
        int result = best.selectAndExpand();
        wins += result;
        return result;
    }

    /**
     * Plays the game randomly until termination
     * @return  if win -> 1,
     *          else -> 0
     */
    public int playout()
    {
        plays++;

        Color startingColour = colour;
        Color currentColor = colour;

        while (!grid.isFinished(currentColor))
        {
            Vector v = heuristic.getNextMove(grid, currentColor);
            grid.setTile(v, currentColor);
            currentColor = toggleColour(currentColor);
        }

        if(currentColor == startingColour)
            return 0;
        else
            return 1;
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
