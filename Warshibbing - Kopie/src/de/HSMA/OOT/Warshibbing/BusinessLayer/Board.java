package de.HSMA.OOT.Warshibbing.BusinessLayer;

import de.HSMA.OOT.Warshibbing.PresentationLayer.PresentationHelper;
import java.awt.Point;

/**
 *
 * @author Tobias
 */
public abstract class Board
{
    protected int width = 11;
    protected int height = 7;
    protected int players = 2;
    protected int activePlayer = 0;
    
    protected Field[][][] game = null;
    protected Player[] player = null;
    
    public Board()
    {
        
    }
    
    public Board(int width, int height)
    {
        this.width = width;
        this.height = height;
    }
    
    public Board(int width, int height, int playerCount)
    {
        this(width, height);
        players = playerCount;
    }
    
    public void Initialize()
    {
        game = new Field[players][width][height];
        
        for(int p = 0; p < players; p++)
            for(int x = 0; x < width; x++)
                for(int y = 0; y < height; y++)
                    game[p][x][y] = new Field();
        
        player = new Player[players];
        
        for(int p = 0; p < players; p++)
        {
            player[p] = new Player(this, (int)Math.ceil(Math.sqrt(width * height)/2.0));
        }
    }
    
    public void placeFigure(Player player, Point destination, Ship ship)
    {
        int neededFields = ship.getLength();
        game[player.ID][destination.x][destination.y] = new Field(ship);
    }
    
    public Field getField(Player player, Point destination)
    {
        return game[player.ID][destination.x][destination.y];
    }
    
    public abstract boolean Mark(Player player, Point destination);
    public abstract void Draw();
    /**
     * Prints the board of the given player-index
     * @param playerIdx 
     */
    public abstract void Draw(int playerIdx);
    public abstract PresentationHelper GetPresentationHelper();
}
