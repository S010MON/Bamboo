package Bamboo.model;

import Bamboo.controller.Vector2d;
import Bamboo.controller.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class HexGridArrayImp implements HexGrid
{
    private Tile[][] tiles;
    private int height;
    private int width;
    private int offset;

    public HexGridArrayImp(int radius)
    {
        height = width = (radius ^ 2) + 1;
        offset = radius;
        tiles = buildGrid(radius);
    }

    @Override
    public Tile getTile(Vector3d v3d)
    {
        Vector2d v2d = convertToVector2d(v3d);
        return tiles[v2d.getQ()][v2d.getR()];
    }

    @Override
    public void setTile(Vector3d v3d, Colour c)
    {
        Vector2d v2d = convertToVector2d(v3d);
        tiles[v2d.getQ()][v2d.getR()].setColour(c);
    }

    @Override
    public List<Tile> getAllNeighbours(Vector3d v3d)
    {
        Vector2d v2d = convertToVector2d(v3d);
        int q = v2d.getQ();
        int r = v2d.getR();
        List<Tile> list = new ArrayList<>();
        list.add(tiles[q-1][r]);
        list.add(tiles[q-1][r+1]);
        list.add(tiles[q][r-1]);
        list.add(tiles[q][r+1]);
        list.add(tiles[q][r-1]);
        list.add(tiles[q+1][r]);
        list.add(tiles[q+1][r]);
        return list;
    }

    public Vector2d convertToVector2d(Vector3d v3d)
    {
        int q = v3d.getX() + offset;
        int r = v3d.getY() + offset;
        return new Vector2d(q, r);
    }

    public Vector3d convertToVector3d(Vector2d v2d)
    {
        int x = v2d.getQ() - offset;
        int y = v2d.getR() - offset;
        int z = -x -y;
        return new Vector3d(x, y, z);
    }

    private Tile[][] buildGrid(int radius)
    {
        Tile[][] grid = new Tile[width][height];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                grid[i][j] = new Tile(Colour.EMPTY);
            }
        }

        for (int i = 0; i < radius; i++)
        {
            for (int j = 0; j < (radius-i); j++)
            {
                grid[i][j] = null;
                grid[height-1-i][height-1-j] = null;
            }
        }
        return grid;
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