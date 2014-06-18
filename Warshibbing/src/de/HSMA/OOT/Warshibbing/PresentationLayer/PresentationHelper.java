package de.HSMA.OOT.Warshibbing.PresentationLayer;

import de.HSMA.OOT.Warshibbing.BusinessLayer.Ship;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Ship.PlaceMode;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Weapon;
import java.awt.Point;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Tobias
 */
public abstract class PresentationHelper
{   
    public abstract String GetStringInput(String question);
    public abstract char GetCharChoiceInput(String question, String ans1, String ans2);
    public abstract Ship.PlaceMode GetPlaceModeInput(String question);
    public abstract Ship GetShipInput(List<Ship> ships);
    public abstract Weapon GetWeaponInput(Ship ship, boolean nuclear);
    public abstract int GetIntInput(String question);
    public abstract int GetIntInput(String question, int low, int high);
    public abstract boolean GetBoolInput(String question);
    public abstract Point GetPointInput(String question);
    public abstract Point GetPointByBoardInput(String question);
    public abstract void PresentError(String message);
    public abstract void PresentError(Exception ex);
    public abstract void PresentInfo(String message);
    public abstract boolean IsInputNeeded();
    
    public static class CommandLinePresentationHelper extends PresentationHelper
    {
        Scanner scanner = new Scanner(System.in);

        @Override
        public String GetStringInput(String question)
        {
            System.out.println(question);
            return scanner.next() + scanner.nextLine();
        }

        @Override
        public int GetIntInput(String question)
        {
            System.out.println(question);
            return scanner.nextInt();
        }

        @Override
        public boolean GetBoolInput(String question)
        {
            System.out.println(question);
            return scanner.nextBoolean();
        }

        @Override
        public Point GetPointInput(String question)
        {
            System.out.println(question);
            System.out.print("X = ");
            int x = scanner.nextInt();
            System.out.print("Y = ");
            int y = scanner.nextInt();
            return new Point(x,y);
        }

        @Override
        public boolean IsInputNeeded()
        {
            return true;
        }

        /**
         * Returns the first character of one of the given choices
         * @param question
         * @param ans1
         * @param ans2
         * @return 
         */
        @Override
        public char GetCharChoiceInput(String question, String ans1, String ans2) {
            String input = "";
            do {
                System.out.println(question
                + "\n Choose between \"" + ans1.charAt(0) + "\" (" + ans1 + ") and \"" + ans2.charAt(0) + "\" (" + ans2 + ")!");
                input = scanner.next() + scanner.nextLine();
            } while(input.charAt(0) != ans1.charAt(0) && input.charAt(0) != ans2.charAt(0));
            return input.charAt(0);
        }

        @Override
        public void PresentError(String message) {
            System.err.println(message);
        }

        @Override
        public void PresentError(Exception ex) {
            System.err.println(ex.getMessage());
        }

        @Override
        public Point GetPointByBoardInput(String question) {
            
            System.out.println(question);
            System.out.print("X = ");
            String input = scanner.next() + scanner.nextLine();
            int x = ((int)input.toUpperCase().charAt(0)) - 65;
            System.out.print("Y = ");
            int y = scanner.nextInt() - 1;
            return new Point(x,y);
        }

        @Override
        public int GetIntInput(String question, int low, int high)
        {
            int input = -1;
            
            do
            {
                System.out.print(question);
                input = scanner.nextInt();
                
            }while(input < low || input > high);
            
            return input;
        }

        @Override
        public PlaceMode GetPlaceModeInput(String question) {
            System.out.println(question);
            String input = "";
            do {
                input = scanner.next() + scanner.nextLine();
                switch(input.charAt(0)) {
                    case 'h':
                            return PlaceMode.Horizontal;
                    case 'v':
                            return PlaceMode.Vertical;
                    case 'd':
                            return PlaceMode.Diagonal;
                }
            } while(true);
        }

        @Override
        public Ship GetShipInput(List<Ship> ships) {
            int index = 0;
            
            System.out.println("Enter the number of a ship you'd like to select!");
            for(int i = 0; i < ships.size(); i++) {
                System.out.println("\t[" + (i + 1) + "]\t" + ships.get(i).getName());
            }
            index = GetIntInput("Your choice:", 1, ships.size());
            
            return ships.get(index - 1);
        }

        @Override
        public Weapon GetWeaponInput(Ship ship, boolean nuclear) {
            int index = 0;
            List<Weapon> wpns = ship.getAvailWeapons();
            
            if(nuclear)
                wpns.add(new Weapon.NuclearWeapon());
            
            System.out.println("Enter the number of a weapon you'd like to use!");
            for(int i = 0; i < wpns.size(); i++) {
                System.out.println("\t[" + (i + 1) + "]  " + wpns.get(i).getName());
            }
            index = GetIntInput("Your choice: ", 1, wpns.size());
            
            return wpns.get(index - 1);
        }

        @Override
        public void PresentInfo(String message)
        {
            System.out.println(message);
        }
    }
    
    public static class GuiPresentationHelper extends PresentationHelper
    {

        @Override
        public String GetStringInput(String question)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int GetIntInput(String question)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean GetBoolInput(String question)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Point GetPointInput(String question)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean IsInputNeeded()
        {
            return false;
        }

        @Override
        public char GetCharChoiceInput(String question, String ans1, String ans2) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void PresentError(String message) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void PresentError(Exception ex) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Point GetPointByBoardInput(String question) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int GetIntInput(String question, int low, int high)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Ship.PlaceMode GetPlaceModeInput(String question) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Ship GetShipInput(List<Ship> ships) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Weapon GetWeaponInput(Ship ship, boolean nuclear)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void PresentInfo(String message)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}