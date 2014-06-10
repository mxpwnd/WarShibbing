package de.HSMA.OOT.Warshibbing.PresentationLayer;

import java.awt.Point;
import java.util.Scanner;

/**
 *
 * @author Tobias
 */
public abstract class PresentationHelper
{   
    Scanner scanner = new Scanner(System.in);
    
    public abstract String GetStringInput(String question);
    public abstract int GetIntInput(String question);
    public abstract boolean GetBoolInput(String question);
    public abstract Point GetPointInput(String question);
    public abstract boolean IsInputNeeded();
    
    public static class CommandLinePresentationHelper extends PresentationHelper
    {

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
            System.out.print("\nY = ");
            int y = scanner.nextInt();
            return new Point(x,y);
        }

        @Override
        public boolean IsInputNeeded()
        {
            return true;
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
        
    }
}
