package Bamboo.controller.MCTS;

import Bamboo.controller.Vector;
import Bamboo.controller.heuristics.Heuristic;
import Bamboo.controller.heuristics.OuterWeighted;
import Bamboo.controller.heuristics.Uniform;
import Bamboo.model.Grid;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Node
{
    private int visits = 1;
    private int plays = 0;
    private int wins = 0;
    private Color colour;
    private Grid grid;
    private Vector move;
    private Node parent;
    private Heuristic heuristic;
    private ArrayList<Node> children;
    private Stack<Vector> unexplored;

    public Node(Grid grid, Color colour, Vector move, Node parent)
    {
        this.grid = grid.copy();
        this.colour = colour;
        this.move = move;
        this.parent = parent;
        heuristic = new Uniform();
        unexplored = grid.getRemainingMovesStack();
        children = new ArrayList<>();
    }

    public Node select()
    {
        visits++;
        Node node;

        Vector v = nextLegalMove();
        if(v != null)
        {
            Color c = toggleColour(colour);
            node = new Node(grid, c, v, this);
            children.add(node);
        }

        else
        {
            node = UCBSelect();
        }

        return node;
    }

    /**
     * Plays the game randomly until termination
     * @return  if win -> 1,
     *          else -> 0
     */
    public void playout()
    {
        plays++;

        Grid temp = grid.copy();
        Color startingColour = colour;
        Color currentColor = colour;

        while (!grid.isFinished(currentColor))
        {
            Vector v = new Uniform().getNextMove(grid, currentColor);
            grid.setTile(v, currentColor);
            currentColor = toggleColour(currentColor);
        }

        if(currentColor != startingColour)
            wins++;

        grid = temp;
        temp.setTile(move,colour);
    }

    public void backprop()
    {
        if (parent != null)
        {
            parent.update(plays, wins, visits);
            parent.backprop();
        }
    }

    public Node bestSelect()
    {
        Node best = null;
        for(Node child: children)
        {
            if(best == null){
                best = child;
            }

            if(child.wins > best.wins)
                best = child;
        }
        return best;
    }

    public Node UCBSelect()
    {
        Node best = null;
        double bestUCB = 0;
        //Collections.shuffle(children);
        for(Node child: children)
        {
            if(best == null)
                best = child;

            double childUCB = UCB.calculate(child.wins, child.plays, visits, child.visits);
            if(childUCB > bestUCB)
            {
                best = child;
                bestUCB = childUCB;
            }
        }
        return best;
    }

    public Node selectChild(Vector v)
    {
        for(Node child: children)
        {
            if(child.move().equals(v))
                return child;
        }
        return null;
    }

    public void pruneAbove()
    {
        parent = null;
    }

    public Vector move()
    {
        return move;
    }

    public int getHeight()
    {
        int max = 0;
        for(Node child: children)
        {
            int h = child.getHeight();
            if(h > max)
                max = h;
        }
        return max;
    }

    public void setHeuristic(Heuristic h){
        heuristic = h;
    }

    private void update(int plays, int wins, int visits)
    {
        this.plays += plays;
        this.wins += wins;
        this.visits += visits;
    }

    private Vector nextLegalMove()
    {
        while (!unexplored.empty())
        {
            Vector v = unexplored.pop();
            if(grid.isLegalMove(v, colour))
                return v;
        }
        return null;
    }

    private Color toggleColour(Color colour)
    {
        if(colour == Color.BLUE)
            return Color.RED;
        return Color.BLUE;
    }

    @Override
    public String toString()
    {
        return "Children: " + children.size() + " Visits: " + visits + " Plays: " + plays + " Wins: " + wins;
    }
}
