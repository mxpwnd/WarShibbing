<<<<<<< HEAD
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
    private final List<Weapon> weapons = new ArrayList<>();
    private final Map<Weapon, Integer> ammo = new HashMap<>();
    private final boolean destroyed = false;
    
    public Ship(Player ref, Field[] fields)
    {
        this.playerRef = ref;
        this.fieldsRef = fields;
        
        Weapon w = new Cannon();
        
        weapons.add(w);
        ammo.put(w, Integer.MAX_VALUE);
        
        w = new NuclearWeapon();
        
        weapons.add(w);
        ammo.put(w, (int)Math.ceil(getLength() / 2.0));
    }
    
    public abstract int getLength();
    public abstract String getName();
    
    public void place(Field[] fields)
    {
        this.fieldsRef = fields;
        for(Field fl : fields) {
            fl.content = this;
        }
        this.playerRef.removeShip(this);    //"This ship ain't available no more"
    }
    
    public boolean Shoot(Player targetPlayer, Weapon usedWeapon, Point targetField) throws Exception
    {
        if(ammo.get(usedWeapon) > 0)
            return targetPlayer.handleShot(usedWeapon, targetField);
        else
            throw new Exception("out of ammo");
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

        @Override
        public String getName() {
            return "Battleship";
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

        @Override
        public String getName() {
            return "Dreadnought";
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

        @Override
        public String getName() {
            return "Submarine";
        }
    }
}
=======
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
    private final List<Weapon> weapons = new ArrayList<>();
    private final Map<Weapon, Integer> ammo = new HashMap<>();
    private final boolean destroyed = false;
    
    public Ship(Player ref, Field[] fields)
    {
        this.playerRef = ref;
        this.fieldsRef = fields;
        
        Weapon w = new Cannon();
        
        weapons.add(w);
        ammo.put(w, Integer.MAX_VALUE);
        
        w = new NuclearWeapon();
        
        weapons.add(w);
        ammo.put(w, 3);
    }
    
    public abstract int getLength();
    
    public void place(Field[] fields)
    {
        this.fieldsRef = fields;
    }
    
    public boolean Shoot(Player targetPlayer, Weapon usedWeapon, Point targetField) throws Exception
    {
        if(ammo.get(usedWeapon) > 0)
            return targetPlayer.handleShot(usedWeapon, targetField);
        else
            throw new Exception("out of ammo");
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
}
>>>>>>> 77bb27fb5558fd4877b11d4e37b8eafd6625da54
