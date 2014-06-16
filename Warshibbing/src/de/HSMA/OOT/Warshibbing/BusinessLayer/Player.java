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
    protected int nuclearOption = 3;
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
        
        if(ref.extendMode)
        {
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
        else
        {
            for(int i = 0; i < 4; i++)
            {
                addShip(new Ship.Battleship(this,null));
                
                if(i != 3)
                    addShip(new Ship.Dreadnought(this, null));
            }
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
            Player targetPlayer = null;
            int weaponCount = 0;
            
            
            if(boardRef.players >= 2)
            {
                 targetPlayer = boardRef.player[ph.GetIntInput("Please input target player: ")-1]; // select player
            }
            else
                targetPlayer = this.ID == 1 ? boardRef.player[1] : boardRef.player[0];
            
            for(Ship p : placedShips)
                weaponCount = weaponCount < p.weapons.size() ? p.weapons.size() : weaponCount; // search for weapons
            
            if(weaponCount > 1)
            {
                ph.GetIntInput("Please select a Ship:\n", 1, this.placedShips.size());
                
                
            }
            else
            {
                Point p = ph.GetPointByBoardInput("Please input the destination field: ");
                this.placedShips.get(0).Shoot(targetPlayer, new Weapon.Cannon(), p);
            }
        }
        else
        {
            //wait();
        }
    }
    
    protected boolean handleShot(Weapon usedWeapon, Point target)
    {
        boardRef.getField(this, target).setMark();
        return boardRef.getField(this, target).IsHit();
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