package de.HSMA.OOT.Warshibbing.BusinessLayer;

import java.awt.Point;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;

/**
 *
 * @author mo
 */
public class CPUPlayer extends Player {

    private SimpleEntry<Boolean, Point> lastHit;
    
    public CPUPlayer(Board ref, int shipCount) {
        super(ref, shipCount);
        lastHit = new SimpleEntry<>(false, null);
    }
    
    @Override
    public void handleTurn() {
        //Select a random player
        Random r = new Random();
        
        int pID = 0;
        do {
            pID = r.nextInt(this.boardRef.players);
        } while(pID == this.ID || boardRef.player[pID].defeated());
        
        //Select a random field
        int x = 0, y = 0;
               //Random!
        do {
            x = r.nextInt(boardRef.width);
            y = r.nextInt(boardRef.height);
        } while(boardRef.game[pID][x][y].hitted || boardRef.game[pID][x][y].marked);

        //Get random ship
        Ship sh = placedShips.get(placedShips.size() > 1 ? r.nextInt(placedShips.size()-1) : 0);
        
        //Get weapon
        Weapon wpn;
        boolean useNuclear = false;
        
        if (this.nuclearOption > 0) {
            if(lastHit.getKey()) {
                useNuclear = true;
            } else {
                useNuclear = Math.random() <= 0.05d;
            }
            if(useNuclear)
                this.nuclearOption--;
        }
        
        if(useNuclear)
            wpn = new Weapon.NuclearWeapon();
        else
            wpn = sh.getAvailWeapons().get(sh.weapons.size() > 1 ? r.nextInt(sh.weapons.size()-1) : 0);
        
        //Perform shot!
        Point pt = new Point(x, y);
        boolean hit = sh.Shoot(boardRef.player[pID], wpn, pt);
        
        lastHit = new SimpleEntry<>(hit, pt);
    }
    
    
}
