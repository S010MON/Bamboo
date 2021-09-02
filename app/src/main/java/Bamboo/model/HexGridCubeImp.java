package Bamboo.model;

import Bamboo.controller.Vector;

import java.util.List;

public class HexGridCubeImp implements HexGrid
{
    private Tile[][] tiles;
    private int height;
    private int width;

    public HexGridCubeImp(int radius)
    {
        height = width = (radius ^ 2) + 1;
        tiles = new Tile[width][height];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                tiles[i][j] = new Tile(Colour.EMPTY);
            }
        }

        for (int i = 0; i < radius; i++)
        {
            for (int j = 0; j < (radius-i); j++)
            {
                tiles[i][j] = null;
                tiles[height-1-i][height-1-j] = null;
            }
        }
    }

    @Override
    public Tile getTile(Vector v)
    {
        return null;
    }

    @Override
    public void setTile(Vector v, Colour c)
    {

    }

    @Override
    public List<Tile> getAllNeighbours(Vector v)
    {
        return null;
    }

    public void prettyPrint()
    {
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                if(tiles[i][j] == null)
                    System.out.print(" ");
                else if (tiles[i][j].getColour() == Colour.BLUE)
                    System.out.print("O");
                else if (tiles[i][j].getColour() == Colour.BLUE)
                    System.out.print("X");
                else
                    System.out.print("_");
            }
            System.out.println("");
        }
    }
}