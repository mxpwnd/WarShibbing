package de.HSMA.OOT.Warshibbing.PresentationLayer;

//Imports
import de.HSMA.OOT.Warshibbing.PresentationLayer.UI.AdvJButton;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class MainWindow implements ActionListener {

	//Variables
	private JFrame frame;
	private JPanel panel;
	private AdvJButton[][][] buttons;
        private final int uiPadding = 10, uiButtonSpace = 2, uiButtonSize = 20, fieldWidth = 10, fieldHeight = 10;
	private boolean debug = true;
        /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
                                    
				}
			}
		});
	}

	/**
	 * Create the application.
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
                
		frame.getContentPane().add(panel);
		
		for(int p = 0; p < 2; p ++) {   //For each player... (affects y)
                    for(int x = 0; x < buttons[0].length; x++) {    //Each column
                            for(int y = 0; y < buttons[1].length; y++) {    //Each row
                                    AdvJButton button = new AdvJButton(((100 * (p+1)) + x * buttons[0].length + y), x, y, "");
                                    button.addActionListener(this);
                                    button.setSelected(false);
                                    button.setBounds(
                                            x * (uiButtonSize + uiButtonSpace) + uiPadding,
                                            /* Important: We will add an offset to y if p > 0 (player 2)! */
                                            y * (uiButtonSize + uiButtonSpace) + uiPadding +  p * ( buttons[1].length * (uiButtonSize + uiButtonSpace) + p * (uiButtonSize)),
                                            uiButtonSize,
                                            uiButtonSize);
                                    panel.add(button);
                                    buttons[p][x][y] =button;
                            }	
                    }
                }
                frame.setBounds(
                        100,
                        100,
                        ((uiPadding * 2) + fieldWidth * (uiButtonSize + uiButtonSpace) + uiButtonSpace),
                        ((uiPadding * 2) + fieldHeight * (uiButtonSize + uiButtonSpace) + uiButtonSpace) * 2);
                if(debug)
                    System.out.println("Initialization complete: Frame.getWidth(): " + frame.getWidth() + ", Frame.getHeight(=: " + frame.getHeight());
                panel.setSize(frame.getSize());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof JButton) {
			AdvJButton source = (AdvJButton)arg0.getSource();
			JOptionPane.showMessageDialog(	frame,
							"Text:\t" + source.getText() + "\nID:\t" + source.getID() + "\nX:\t" + source.getIDX() + "\nY:\t" + source.getIDY(),
							"Warshibbing - Button pressed",
							0);
		}
	}
}
