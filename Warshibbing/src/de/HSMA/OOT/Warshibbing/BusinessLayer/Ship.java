package de.HSMA.OOT.Warshibbing.BusinessLayer;

import de.HSMA.OOT.Warshibbing.BusinessLayer.Weapon.*;
import java.awt.Point;
import java.util.*;

/**
 *
 * @author Tobias
 */
public abstract class Ship
{
    private final Player playerRef;
    private Field[] fieldsRef;
    protected final List<Weapon> weapons = new ArrayList<>();
    protected final Map<Weapon, Integer> ammo = new HashMap<>();
    protected final boolean destroyed = false;
    
    public Ship(Player ref, Field[] fields)
    {
        this.playerRef = ref;
        this.fieldsRef = fields;
        
        Weapon w = new Cannon();
        
        weapons.add(w);
        ammo.put(w, Integer.MAX_VALUE);
    }
    
    public abstract int getLength();
    
    public String getName() {
        return this.getClass().getSimpleName();
    }
    
    public void place(Field[] fields)
    {
        this.fieldsRef = fields;
        
        for(Field fl : fields) 
        {
            fl.content = this;
        }
        
        this.playerRef.removeShip(this);    //"This ship ain't available no more"
    }
    
    public boolean Shoot(Player targetPlayer, Weapon usedWeapon, Point targetField)
    {
        if(ammo.get(usedWeapon) > 0)
        {
            ammo.put(usedWeapon, ammo.get(usedWeapon)-1);
            return targetPlayer.handleShot(usedWeapon, targetField);
        }
        else
            playerRef.boardRef.GetPresentationHelper().PresentError("out of ammo");
        
        return false;
    }
    
    public boolean IsDestroyed()
    {
        return destroyed;
    }
    
    public static class Battleship extends Ship
    {
        public Battleship(Player ref, Field[] fields)
        {
            super(ref, fields);
        }
        
        @Override
        public int getLength()
        {
            return 2;
        }
        
        @Override
        public String toString()
        {
            return "B";
        }
    }
    
    public static class Dreadnought extends Ship
    {
        public Dreadnought(Player ref, Field[] fields)
        {
            super(ref, fields);
        }
        
        @Override
        public int getLength()
        {
            return 4;
        }
        
        @Override
        public String toString()
        {
            return "D";
        }
    }
    
    public static class Submarine extends Ship
    {
        public Submarine(Player ref, Field[] fields)
        {
            super(ref, fields);
        }
        
        @Override
        public int getLength()
        {
            return 3;
        }
        
        @Override
        public String toString()
        {
            return "S";
        }
    }
    
    public static enum PlaceMode
    {
        Horizontal,
        Vertical, 
        Diagonal
    };
}