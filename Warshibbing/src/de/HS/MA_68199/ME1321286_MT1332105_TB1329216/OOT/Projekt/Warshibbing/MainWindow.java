package de.HS.MA_68199.ME1321286_MT1332105_TB1329216.OOT.Projekt.Warshibbing;

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

public class MainWindow implements ActionListener {

	//Variables
	private JFrame frame;
	private JPanel panel;
	private AdvJButton[][] buttons;
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
					e.printStackTrace();
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
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 478);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		buttons = new AdvJButton[6][6];
		
		panel = new JPanel();
		panel.setBounds(10, 11, 414, 207);
		frame.getContentPane().add(panel);
		panel.setLayout(new MigLayout("", "[][]", "[][][][][][][]"));
		
		for(int x = 0; x < buttons[0].length; x++) {
			for(int y = 0; y < buttons[1].length; y++) {
				AdvJButton button = new AdvJButton((x * buttons[0].length + y), x, y, " ");
				button.addActionListener(this);
				button.setSelected(false);
				panel.add(button, "cell " + x + " " + y);	// => cell *column* *row*
			}	
		}
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
