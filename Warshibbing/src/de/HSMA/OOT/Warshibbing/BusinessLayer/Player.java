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
        boolean hitted = false;
        
        do
        {
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
                    Ship inputShip = ph.GetShipInput(placedShips);
                    Weapon inputWeapon = ph.GetWeaponInput(inputShip);
                    Point p = ph.GetPointByBoardInput("Please input target field: ");
                    hitted = inputShip.Shoot(targetPlayer, inputWeapon, p);
                }
                else
                {
                    Point p = ph.GetPointByBoardInput("Please input target field: ");
                    hitted = this.placedShips.get(0).Shoot(targetPlayer, new Weapon.Cannon(), p);
                }
                
                if(hitted)
                    System.out.println("HIT!!!");
                else
                    System.out.println("FAIL!!!");
            }
            else
            {
                //wait();
            }
            
        }while(hitted);
    }
    
    protected boolean handleShot(Weapon usedWeapon, Point target)
    {
        usedWeapon.handleShot(boardRef.getField(this, target));
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