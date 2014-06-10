package de.HSMA.OOT.Warshibbing.PresentationLayer;

import de.HSMA.OOT.Warshibbing.BusinessLayer.*;
import de.HSMA.OOT.Warshibbing.PresentationLayer.UI.AdvJButton;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class MainWindow extends Board implements ActionListener
{

    //Variables
    private JFrame frame;
    private JPanel panel;
    private AdvJButton[][][] buttons;
    private Timer timer;
    private final int uiPadding = 10, uiButtonSpace = 2, uiButtonSize = 20;
    private boolean debug = true;

    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    e.printStackTrace(System.out);
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainWindow()
    {
        Initialize();
        initializeFrame();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initializeFrame()
    {
        buttons = new AdvJButton[2][width][height];

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLayout(null);
        panel = new JPanel(null);

        frame.getContentPane().add(panel);

        for (int p = 0; p < players; p++)
        {   //For each player... (affects y)
            for (int x = 0; x < buttons[p].length; x++)
            {    //Each column
                for (int y = 0; y < buttons[p][x].length; y++)
                {    //Each row
                    AdvJButton button = new AdvJButton(((100 * (p + 1)) + x * buttons[p][x].length + y), x, y, "");
                    button.addActionListener(this);
                    button.setSelected(false);
                    button.setText(game[p][x][y].toString());
                    button.setBounds(
                            x * (uiButtonSize + uiButtonSpace) + uiPadding,
                            /* Important: We will add an offset to y if p > 0 (player 2)! */
                            y * (uiButtonSize + uiButtonSpace) + uiPadding + (p%2) * (buttons[1].length * (uiButtonSize + uiButtonSpace) + (p%2) * (uiButtonSize)),
                            uiButtonSize,
                            uiButtonSize);
                    panel.add(button);
                    buttons[p][x][y] = button;
                }
            }
        }
        frame.setBounds(
                100,
                100,
                ((uiPadding * players) + width * (uiButtonSize + uiButtonSpace) + uiButtonSpace + 12),
                ((uiPadding * players) + height * (uiButtonSize + uiButtonSpace) + uiButtonSpace) * 2 + 30);
        if (debug)
        {
            System.out.println("Initialization complete: Frame.getWidth(): " + frame.getWidth() + ", Frame.getHeight(=: " + frame.getHeight());
        }
        panel.setSize(frame.getSize());
        
        timer = new Timer(15*60*1000, new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(15);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        if (arg0.getSource() instanceof JButton)
        {
            AdvJButton source = (AdvJButton) arg0.getSource();
            JOptionPane.showMessageDialog(frame,
                                          "Text:\t" + source.getText() + "\nID:\t" + source.getID() + "\nX:\t" + source.getIDX() + "\nY:\t" + source.getIDY(),
                                          "Warshibbing - Button pressed",
                                          0);
        }
    }

    @Override
    public boolean Mark(Player player, Point destination)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Draw()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PresentationHelper GetPresentationHelper()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
