package de.HSMA.OOT.Warshibbing.BusinessLayer;

import java.awt.Point;
import java.util.AbstractMap.SimpleEntry;

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
        int pID = 0;
        do {
            pID = (int)(Math.random() * (this.boardRef.player.length - 1)); 
        } while(pID != this.ID);
        
        //Select a random field
        int x = 0, y = 0;
        if(lastHit.getKey()) {  //Near our last hit
            do {
                do {
                    x = lastHit.getValue().x + (2 + (int)(Math.random() * 2) * (Math.random() > 0.5 ? 1 : -1));
                    y = lastHit.getValue().y + (2 + (int)(Math.random() * 2) * (Math.random() > 0.5 ? 1 : -1));
                } while(x < 0 || y < 0 || x >= boardRef.width || y <= boardRef.height);
            } while(boardRef.game[pID][x][y].hitted || boardRef.game[pID][x][y].marked);
        } else {                //Random!
            do {
                x = (int)(Math.random() * (this.boardRef.width-1));
                y = (int)(Math.random() * (this.boardRef.height-1));
            } while(boardRef.game[pID][x][y].hitted || boardRef.game[pID][x][y].marked);
        }
        //Get random ship
        Ship sh = this.getPlacedShips().get((int)(Math.random() * (this.getPlacedShips().size() - 1)));
        
        //Get weapon
        Weapon wpn = null;
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
        {
            wpn = new Weapon.NuclearWeapon();
        } else {
            wpn = sh.getAvailWeapons().get((int)(Math.random() * (sh.getAvailWeapons().size() - 1)));
        }
        
        //Perform shot!
        Point pt = new Point(x, y);
        boolean hit = sh.Shoot(boardRef.player[pID], wpn, pt);
        
        lastHit = new SimpleEntry<>(hit, pt);
    }
    
    
}
