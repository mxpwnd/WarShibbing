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
    public abstract char GetCharChoiceInput(String question, String ans1, String ans2);
    public abstract int GetIntInput(String question);
    public abstract int GetIntInput(String question, int low, int high);
    public abstract boolean GetBoolInput(String question);
    public abstract Point GetPointInput(String question);
    public abstract Point GetPointByBoardInput(String question);
    public abstract void PresentError(String message);
    public abstract void PresentError(Exception ex);
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
            int x = ((int)input.charAt(0)) - 65;
            System.out.print("\nY = ");
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
                System.out.println("");
                
            }while(input < low || input > high);
            
            return input;
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
        
    }
}