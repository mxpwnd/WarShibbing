package de.HSMA.OOT.Warshibbing.PresentationLayer;

import de.HSMA.OOT.Warshibbing.BusinessLayer.Board;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Field;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Player;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Ship;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            System.out.println("Player " + (pl + 1) + ", place your ships!");
            boolean retry = false;
            //Place all ships...
            while (this.player[pl].getUnplacedShips().size() > 0) {
                retry = false;
                Draw(pl);
                Ship sh = this.player[pl].getUnplacedShips().get(0);

                Point p1 = presHelper.GetPointByBoardInput(
                        "Where would you like to place this " + sh.getName() + " (length " + sh.getLength() + " units)?\n" +
                        "(" + this.player[pl].getUnplacedShips().size() + " ships left to place)");
                if (p1.x >= this.width || p1.y >= this.height || p1.x < 0 || p1.y < 0) {
                    presHelper.PresentError("The destination you entered is out of bounds!");
                    continue;
                }

                boolean hori = presHelper.GetCharChoiceInput("How would you like to place it?", "horizontally", "vertically") == 'h';
                if (hori) {  //Place horizontally
                    //Will the ship fit at the given destination?
                    if (p1.x + sh.getLength() > this.width) {
                        presHelper.PresentError(ErrPointOutOfBounds);
                        continue;
                    }
                    //Does any other ship intersect?
                    for (int i = p1.x; i < p1.x + sh.getLength(); i++) {
                        if (!this.game[pl][i][p1.y].isEmpty()) {
                            presHelper.PresentError(ErrShipsIntersect);
                            retry = true;
                        }
                    }
                    if (retry) {
                        continue;
                    }
                    //Place the ship!
                    Field[] fields = new Field[sh.getLength()];
                    for (int i = 0; i < sh.getLength(); i++) {
                        fields[i] = this.game[pl][p1.x + i][p1.y];
                    }
                    sh.place(fields);

                } else {  //Place vertically
                    if (p1.y + sh.getLength() > this.height) {
                        presHelper.PresentError(ErrPointOutOfBounds);
                        continue;
                    }
                    //Does any other ship intersect?
                    for (int i = p1.y; i < p1.y + sh.getLength(); i++) {
                        if (!this.game[pl][p1.x][i].isEmpty()) {
                            presHelper.PresentError(ErrShipsIntersect);
                            retry = true;
                        }
                    }
                    if (retry) {
                        continue;
                    }
                    //Place the ship!
                    Field[] fields = new Field[sh.getLength()];
                    for (int i = 0; i < sh.getLength(); i++) {
                        fields[i] = this.game[pl][p1.x][p1.y + i];
                    }
                    sh.place(fields);
                }
            }
        }
    }

    /**
     * *
     * The actual game starts here
     */
    private void Start() {
        printMenu();
        do {
            try {
                String inp = presHelper.GetStringInput("Enter the desired command:");
                processInput(inp);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } while (!aborted);
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