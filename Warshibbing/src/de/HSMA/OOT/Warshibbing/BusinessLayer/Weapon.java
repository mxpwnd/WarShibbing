<<<<<<< HEAD
package de.HSMA.OOT.Warshibbing.BusinessLayer;

/**
 *
 * @author Tobias
 */
public abstract class Weapon
{
    public abstract int getHits(Ship ship);
    
    public static class Cannon extends Weapon
    {
        @Override
        public int getHits(Ship ship)
        {
            return 1;
        }
    }
    
    public static class NuclearWeapon extends Weapon
    {
        @Override
        public int getHits(Ship ship)
        {
            return ship.getLength();
        }
        
    }
}
=======
package de.HSMA.OOT.Warshibbing.BusinessLayer;

/**
 *
 * @author Tobias
 */
public abstract class Weapon
{
    public abstract int getHits(Ship ship);
    
    public static class Cannon extends Weapon
    {
        @Override
        public int getHits(Ship ship)
        {
            return 1;
        }
    }
    
    public static class NuclearWeapon extends Weapon
    {
        @Override
        public int getHits(Ship ship)
        {
            return ship.getLength();
        }
        
    }
}
>>>>>>> 77bb27fb5558fd4877b11d4e37b8eafd6625da54
