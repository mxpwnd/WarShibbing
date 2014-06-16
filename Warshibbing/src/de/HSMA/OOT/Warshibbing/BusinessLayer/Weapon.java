package de.HSMA.OOT.Warshibbing.BusinessLayer;

/**
 *
 * @author Tobias
 */
public abstract class Weapon
{
    public int Ammo = Integer.MAX_VALUE;
    
    public abstract int getHits(Ship ship);
    public abstract void handleShot(Field field);
    
    public String getName()
    {
        return this.getClass().getSimpleName();
    }
    
    public static class Cannon extends Weapon
    {
        @Override
        public int getHits(Ship ship)
        {
            return 1;
        }

        @Override
        public void handleShot(Field field)
        {
            field.setMark();
            if(field.IsHit())
            {
                Ship p = (Ship)field.content;
                
                if(p.IsDestroyed())
                    p.playerRef.placedShips.remove(p);
            }
        }
    }
    
    public static class NuclearWeapon extends Weapon
    {
        @Override
        public int getHits(Ship ship)
        {
            return ship.getLength();
        }

        @Override
        public void handleShot(Field field)
        {
            if(field.IsHit())
            {
                Ship p = (Ship)field.content;
                Field[] fields = p.fieldsRef;
                
                for(Field f : fields)
                    f.setMark();
                
                p.playerRef.placedShips.remove(p);
            }
        }
        
    }
}