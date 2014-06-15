<<<<<<< HEAD
package de.HSMA.OOT.Warshibbing.BusinessLayer;

import de.HSMA.OOT.Warshibbing.PresentationLayer.PresentationHelper;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author Tobias
 */
public class Player
{
    static int pcount = 0;
    
    public int ID = 0;
    protected Board boardRef;
    public List<Ship> availableShips;
    public List<Ship> placedShips;
    
    public Player(Board ref, int shipCount)
    {
        ID = pcount++;
        boardRef = ref;
        availableShips = new ArrayList<>(shipCount);
        placedShips = new ArrayList<>(shipCount);
        
        /*
        Add ships:
        1/3 Battleships, 1/3 Submarines, 1/3 Dreadnoughts
        */
            for(int i = 0; i < shipCount; i++)
                switch(i % 3) {
                    case 0:
                        addShip(new Ship.Battleship(this, null));
                        break;
                    case 1:
                        addShip(new Ship.Submarine(this, null));
                        break;
                    case 2:
                        addShip(new Ship.Dreadnought(this, null));
                        break;
                }
    }
    
    protected void addShip(Ship ship)
    {
        availableShips.add(ship);
    }
    
    protected void removeShip(Ship ship)
    {
        availableShips.remove(ship);
    }
    
    protected void handleTurn()
    {
        PresentationHelper ph = boardRef.GetPresentationHelper();
        
        if(ph.IsInputNeeded())
        {
            
        }
    }
    
    protected boolean handleShot(Weapon usedWeapon, Point target)
    {
        
        
        return false;
    }
    
    public List<Ship> getUnplacedShips()
    {
        return availableShips;
    }
    
    public List<Ship> getPlacedShips()
    {
        return placedShips;
    }
}
=======
package de.HSMA.OOT.Warshibbing.BusinessLayer;

import de.HSMA.OOT.Warshibbing.PresentationLayer.PresentationHelper;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author Tobias
 */
public class Player
{
    static int pcount = 0;
    
    public int ID = 0;
    protected Board boardRef;
    public List<Ship> availableShips;
    public List<Ship> placedShips;
    
    public Player(Board ref, int shipCount)
    {
        ID = pcount++;
        boardRef = ref;
        availableShips = new ArrayList<>(shipCount);
        placedShips = new ArrayList<>(shipCount);
        
        addShip(new Ship.Battleship(this, null));
        addShip(new Ship.Battleship(this, null));
        addShip(new Ship.Battleship(this, null));
        addShip(new Ship.Battleship(this, null));
        addShip(new Ship.Dreadnought(this, null));
        addShip(new Ship.Dreadnought(this, null));
        addShip(new Ship.Dreadnought(this, null));
    }
    
    protected void addShip(Ship ship)
    {
        availableShips.add(ship);
    }
    
    protected void removeShip(Ship ship)
    {
        availableShips.remove(ship);
    }
    
    protected void handleTurn()
    {
        PresentationHelper ph = boardRef.GetPresentationHelper();
        
        if(ph.IsInputNeeded())
        {
            
        }
    }
    
    protected boolean handleShot(Weapon usedWeapon, Point target)
    {
        
        
        return false;
    }
    
    public List<Ship> getUnplacedShips()
    {
        return availableShips;
    }
    
    public List<Ship> getPlacedShips()
    {
        return placedShips;
    }
}
>>>>>>> 77bb27fb5558fd4877b11d4e37b8eafd6625da54
