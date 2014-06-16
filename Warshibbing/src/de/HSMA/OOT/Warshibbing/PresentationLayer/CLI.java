package de.HSMA.OOT.Warshibbing.PresentationLayer;

import de.HSMA.OOT.Warshibbing.BusinessLayer.*;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Tobias
 */
public class CLI extends Board {

    private static Timer timer;
    private boolean aborted = false;
    private static CLI instance = null;
    private static String Border = "****************************************";
    private static String ErrPointOutOfBounds = "The destination you entered is out of bounds";
    private static String ErrShipsIntersect = "You cannot place your ship on top of another ship!";
    private PresentationHelper presHelper;

    public static void main(String[] args) {
        timer = new Timer(15 * 60 * 1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(15);
            }
        });
        instance = new CLI(11, 7, 2);
        instance.SetupBoard();
        instance.Initialize();
        instance.SetupShips();
        instance.Start();
    }

    public CLI(int width, int heigth, int playerCount) {
        super(width, heigth, playerCount);
        presHelper = GetPresentationHelper();
    }

    @Override
    public boolean Mark(Player player, Point destination) {
        Field f = getField(player, destination);
        f.setMark();
        return f.IsHit();
    }

    @Override
    public void Draw() {
        String firstLine = "   ";

        for (int i = 0; i < this.width; i++) {
            firstLine += " " + (char) ((int) 'A' + i) + " ";
        }

        for (int p = 0; p < this.players; p++) {
            System.out.println("Player: " + (p + 1));

            for (int y = 0; y < this.height; y++) {
                if (y == 0) {
                    System.out.println(firstLine);
                }

                System.out.print((y + 1) + " |");

                for (int x = 0; x < this.width; x++) {
                    if (x == this.width - 1) {
                        System.out.println(game[p][x][y] + " |");
                    } else {
                        System.out.print(game[p][x][y] + " |");
                    }
                }
            }

            System.out.println("");
        }
    }

    @Override
    public void Draw(int playerIdx) {
        String firstLine = "\t";

        for (int i = 0; i < this.width; i++) {
            firstLine += " " + (char) ((int) 'A' + i) + " ";
        }

        //System.out.println("Player: " + (playerIdx + 1));

        for (int y = 0; y < this.height; y++) {
            if (y == 0) {
                System.out.println(firstLine);
            }

            System.out.print((y + 1) + "\t|");

            for (int x = 0; x < this.width; x++) {
                if (x == this.width - 1) {
                    System.out.println(game[playerIdx][x][y] + " |");
                } else {
                    System.out.print(game[playerIdx][x][y] + " |");
                }
            }
        }

        System.out.println("");
    }

    @Override
    public PresentationHelper GetPresentationHelper() {
        return new PresentationHelper.CommandLinePresentationHelper();
    }

    /**
     * *
     * Get information about board-size and players before we initialize the
     * board
     */
    private void SetupBoard() {
        this.width = presHelper.GetIntInput("Please enter the board's width:");
        this.height = presHelper.GetIntInput("Please enter the board's height:");
        this.players = presHelper.GetIntInput("Please enter the number of players:");
    }

    /**
     * *
     * Place ships and stuff
     */
    private void SetupShips() {
        for (int pl = 0; pl < this.players; pl++) {
            
            if(this.player[pl] instanceof CPUPlayer)
            {
                System.out.println("Computer is placing his Ships!");
                Random r = new Random();
                
                while(this.player[pl].getUnplacedShips().size() > 0)
                {
                    Ship sh = this.player[pl].getUnplacedShips().get(0);
                    Point p = new Point(r.nextInt(this.width-1), r.nextInt(this.height-1));
                    Ship.PlaceMode mode = Ship.PlaceMode.Horizontal;
                    int n = r.nextInt(100);
                    
                    if(n > 33 && n < 66)
                        mode = Ship.PlaceMode.Vertical;
                    else if(n > 33)
                        mode = Ship.PlaceMode.Diagonal; 
                    
                    AbstractMap.SimpleEntry<Boolean, String> result = null;
                    
                    if(this.extendMode)
                        result = placeFigure(player[pl], p, sh, mode);
                    else
                        result = placeFigure(player[pl], p, sh, Ship.PlaceMode.Horizontal);
                }
            }
            else
            {
                System.out.println("Player " + (pl + 1) + ", place your ships!");
                boolean retry = false;
                //Place all ships...
                while (this.player[pl].getUnplacedShips().size() > 0) 
                {
                    Draw(pl);
                    Ship sh = this.player[pl].getUnplacedShips().get(0);

                    Point p1 = presHelper.GetPointByBoardInput(
                            "Where would you like to place this " + sh.getName() + " (length " + sh.getLength() + " units)?\n" +
                            "(" + this.player[pl].getUnplacedShips().size() + " ships left to place)");
                    
                    Ship.PlaceMode mode = null;
                    
                    if(extendMode)
                        mode = presHelper.GetPlaceModeInput("How would you like to place it?\nEnter 'h' for horizontal, 'v' for vertical or 'd' for diagonal placement.");
                    else
                        mode = Ship.PlaceMode.Horizontal;
                                
                    AbstractMap.SimpleEntry<Boolean, String> result = placeFigure(player[pl], p1, sh, mode);
                    if(!result.getKey()) {
                        System.err.println("Error: " + result.getValue());
                    }
                }
            }
        }
    }

    /**
     * *
     * The actual game starts here
     */
    private void Start() {
        //printMenu();
        int counter = 0;
        do {
            try {
                Draw();
                player[counter].handleTurn();
                do {
                    counter++;
                    counter %= this.player.length;
                } while(player[counter].defeated());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } while (!aborted && !gameOver());
    }
    
    private boolean gameOver() {
        int defeatedPlayers = 0;
        for(Player pl : player) 
            if(pl.defeated())
                defeatedPlayers++;
        return defeatedPlayers == player.length - 1;
    }

    private void printMenu() {
        System.out.println(
                Border + "\n"
                + " ~Warshibbing~\n"
                + Border + "\n"
                + " Command         Explanation\n"
                + Border + "\n"
                + " help            Show this menu\n"
                + " exit            Exits the game"
                + Border + "\n"
        );
    }

    private void processInput(String input) {
        switch (input) {
            case "exit":
                aborted = true;
                break;
            case "?":
            case "help":
                printMenu();
                break;
            default:
                presHelper.PresentError("Unknown command \"" + input + "\"!");
                break;
        }
    }
}