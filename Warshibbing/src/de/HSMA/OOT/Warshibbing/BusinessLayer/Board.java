package de.HSMA.OOT.Warshibbing.BusinessLayer;

import de.HSMA.OOT.Warshibbing.PresentationLayer.PresentationHelper;
import java.awt.Point;
import java.util.AbstractMap.SimpleEntry;

/**
 *
 * @author Tobias
 */
public abstract class Board
{
    protected int width = 11;
    protected int height = 7;
    protected int players = 2;
    protected int placeholder = 1;
    protected boolean extendMode = false;
    
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
            player[p] = new Player(this, extendMode ? (int)Math.ceil(Math.sqrt(width * height)/2.0) : 7);
        }
    }
    
    public SimpleEntry<Boolean, String> placeFigure(Player player, Point destination, Ship ship, Ship.PlaceMode mode)
    {
        int neededFields = ship.getLength();
        Point p1 = new Point(destination.x, destination.y);
        Field[] fields = new Field[neededFields];
        int[] offset = new int[2]; /* 0=X, 1=Y */
        
        //game[player.ID][destination.x][destination.y] = new Field(ship);
        
        //Setup vector & check board-bounds
        if(p1.x < 0 || p1.y < 0)
            return new SimpleEntry<>(false, "out of bounds");
        switch(mode) {
            case Horizontal:
                if(p1.x + neededFields > width)
                    return new SimpleEntry<>(false, "out of bounds");
                offset = new int[]{ 1, 0 };
                break;
            case Vertical:
                if(p1.y + neededFields > height)
                    return new SimpleEntry<>(false, "out of bounds");
                offset = new int[]{ 0, 1 };
                break;
            case Diagonal:
                if(p1.x + neededFields > width || p1.y + neededFields > height)
                    return new SimpleEntry<>(false, "out of bounds");
                offset = new int[]{ 1, 1 };
                break;
        }
        
        for(int i = 0; i < neededFields; i++) {
            fields[i] = game[player.ID][p1.x][p1.y];
            //Field occupied?
            if(fields[i].IsHit())
                return new SimpleEntry<>(false, "field already occupied");
            
            //neighbours occupied?
            for(int d = 0; d < 2; d++) //X or Y?
                for(int w = -1; w <= 1; w+=2) //Left or Right?
                    for(int s = 1; s < placeholder + 1; s++) //Step/Distance
                        if(game[player.ID][p1.x + (d == 0 ? w * s : 0)][p1.y + (d == 0 ? w * s : 0)].IsHit())
                            return new SimpleEntry<>(false, "neighbour-field already occupied");
            
            p1.x += offset[0];
            p1.y += offset[1];
        }
        
        ship.place(fields);
        
        return new SimpleEntry<>(true, "");
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