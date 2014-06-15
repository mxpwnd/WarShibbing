package de.HS.MA_68199.ME1321286_MT1332105_TB1329216.OOT.Projekt.Warshibbing.UI.Controls;

import javax.swing.JButton;

public class AdvJButton extends JButton {
	
	// Variables
	int id, 	//	The button's ID
            idx, 	//	"column"-ID
            idy,	//	"row"-ID
            idp;        //      Player-/Field-ID
	
	//Getters
	/**
	 * The button's ID
	 * @return Cpt. Obvious at your service
	 */
	public int getID() { return this.id; }
	/**
	 * The button's "column"-ID
	 * @return Cpt. Obvious at your service
	 */
	public int getIDX() { return this.idx; }
	/**
	 * The button's "row"-ID
	 * @return Cpt. Obvious at your service
	 */
	public int getIDY() { return this.idy; }
	/**
	 * The button's Player-/Field-ID
	 * @return Cpt. Obvious at your service
	 */
	public int getIDP() { return this.idp; }
	
	//Won't need any setters...? id, idx & idy is handled by the constructor
	
	//Constructor
	/**
	 * The AdvJButton's constructor
	 * @param id	The button's ID
	 * @param idx	"column"-ID
	 * @param idy	"row"-ID
         * @param idp   Player-/Field-ID
	 * @param text	The text this button will display
	 */
	public AdvJButton(int id, int idx, int idy, int idp, String text) {
		super(text);
		this.id = id;
		this.idx = idx;
		this.idy = idy;
                this.idp = idp;
	}
	
	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

}
