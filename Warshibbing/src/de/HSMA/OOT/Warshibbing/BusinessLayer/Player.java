package de.HSMA.OOT.Warshibbing.BusinessLayer;

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
    public List<Ship> ships;
    
    public Player(int shipCount)
    {
        ID = pcount++;
        ships = new ArrayList<>(shipCount);
    }
    
    protected void addShip(Ship ship)
    {
        ships.add(ship);
    }
    
    protected void removeShip(Ship ship)
    {
        ships.remove(ship);
    }
    
    public void handleTurn(Board presentation)
    {
        
    }
    
    public boolean handleShot(Weapon usedWeapon, Point target)
    {
        return false;
    }
}
