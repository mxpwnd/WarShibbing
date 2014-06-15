package de.HS.MA_68199.ME1321286_MT1332105_TB1329216.OOT.Projekt.Warshibbing.UI.Windows;

//Imports
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import de.HS.MA_68199.ME1321286_MT1332105_TB1329216.OOT.Projekt.Warshibbing.UI.Controls.*;
import java.awt.Color;

public class MainWindow implements ActionListener {

    /*
     * Variables
     */
    //Our frame
    public JFrame frame;
    //Panel that holds our buttons
    private JPanel panel;
    //Buttons[PlayerID][Column][Row]
    private AdvJButton[][][] buttons;
    //UI-Constants
    private final int uiPadding = 10, uiButtonSpace = 2, uiButtonSize = 20;
    //Field-Size
    private int fieldWidth, fieldHeight;
    //Print debug-info?
    private boolean debug = true;

    /**
     * Create the window
     */
    public MainWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        buttons = new AdvJButton[2][fieldWidth][fieldHeight];

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLayout(null);
        panel = new JPanel();
        panel.setLayout(null);
    }

    public void setup(int fieldWidth, int fieldHeight) {
        //Apply fieldSize
        this.fieldWidth = fieldWidth;
        this.fieldHeight = fieldHeight;
        
        frame.getContentPane().add(panel);
        //Create fields
        for (int p = 0; p < 2; p++) {   //For each player... (affects y)
            for (int x = 0; x < buttons[0].length; x++) {    //Each column
                for (int y = 0; y < buttons[1].length; y++) {    //Each row
                    AdvJButton button = new AdvJButton(((100 * (p + 1)) + x * buttons[0].length + y), x, y, p, "");
                    button.addActionListener(this);
                    button.setSelected(false);
                    button.setBounds(
                            x * (uiButtonSize + uiButtonSpace) + uiPadding,
                            /* Important: We will add an offset to y if p > 0 (player 2)! */
                            y * (uiButtonSize + uiButtonSpace) + uiPadding + p * (buttons[1].length * (uiButtonSize + uiButtonSpace) + p * (uiButtonSize)),
                            uiButtonSize,
                            uiButtonSize);
                    panel.add(button);
                    buttons[p][x][y] = button;
                }
            }
        }
        
        //Adjust size
        frame.setBounds(
                100,
                100,
                ((uiPadding * 2) + fieldWidth * (uiButtonSize + uiButtonSpace) + uiButtonSpace),
                ((uiPadding * 2) + fieldHeight * (uiButtonSize + uiButtonSpace) + uiButtonSpace) * 2);
        panel.setSize(frame.getSize());
        if (debug)
            System.out.println("Initialization complete: Frame.getWidth(): " + frame.getWidth() + ", Frame.getHeight(): " + frame.getHeight());
    }
    
    public void show() {
        frame.setVisible(true);
        frame.invalidate();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() instanceof AdvJButton) {
            AdvJButton source = (AdvJButton) arg0.getSource();
            JOptionPane.showMessageDialog(frame,
                    "Text: " + source.getText()
                    + "\nID: " + source.getID()
                    + "\nX: " + source.getIDX()
                    + "\nY: " + source.getIDY()
                    + "\nPlayer: " + source.getIDP(),
                    "Warshibbing - Button pressed",
                    0);
            source.setBackground(Color.red);
        }
    }
}
