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
    protected final Player playerRef;
    protected Field[] fieldsRef;
    protected final List<Weapon> weapons = new ArrayList<>();
    protected final Map<String, Integer> ammo = new HashMap<>();
    
    public abstract int getLength();
    
    public Ship(Player ref, Field[] fields)
    {
        this.playerRef = ref;
        this.fieldsRef = fields;
        
        Weapon w = new Cannon();
        
        weapons.add(w);
        ammo.put(w.getName(), Integer.MAX_VALUE);
    }
    
    public String getName() {
        return this.getClass().getSimpleName();
    }
    
    public List<Weapon> getAvailWeapons() {
        ArrayList<Weapon> wpns = new ArrayList<>();
        for(Weapon wpn : weapons)
            if(wpn.Ammo > 0)
                wpns.add(wpn);
        return wpns;
    }
    
    public void place(Field[] fields)
    {
        this.fieldsRef = fields;
        
        for(Field fl : fields) 
        {
            fl.content = this;
        }
        
        this.playerRef.removeShip(this);    //"This ship ain't available no more"
        this.playerRef.placedShips.add(this);
    }
    
    public boolean Shoot(Player targetPlayer, Weapon usedWeapon, Point targetField)
    {
        if(!(usedWeapon instanceof NuclearWeapon))
            ammo.put(usedWeapon.getName(), ammo.get(usedWeapon.getName())-1);
        
        this.playerRef.boardRef.GetPresentationHelper().PresentInfo(String.format("Player[%d] targets Player[%d] with %s and shoot with %s on field %s", this.playerRef.ID+1, targetPlayer.ID+1, this.getName().toLowerCase(), usedWeapon.getName().toLowerCase(), targetField.toString()));
        return targetPlayer.handleShot(usedWeapon, targetField);
    }
    
    public boolean IsDestroyed()
    {
        for(Field f : fieldsRef)
            if(!f.hitted)
                return false;
        
        return true;
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