package de.HSMA.OOT.Warshibbing.PresentationLayer.UI;

import javax.swing.JButton;

public class AdvJButton extends JButton
{

    // Variables
    int id,     // The button's ID
        idx,    // "column"-ID
        idy;	// "row"-ID

    //Getters
    /**
     * The button's ID
     *
     * @return Cpt. Obvious at your service
     */
    public int getID()
    {
        return this.id;
    }

    /**
     * The button's "column"-ID
     *
     * @return Cpt. Obvious at your service
     */
    public int getIDX()
    {
        return this.idx;
    }

    /**
     * The button's "row"-ID
     *
     * @return Cpt. Obvious at your service
     */
    public int getIDY()
    {
        return this.idy;
    }

    //Won't need any setters...? id, idx & idy is handled by the constructor
    //Constructor
    
    /**
     * The AdvJButton's constructor
     *
     * @param id	The button's ID
     * @param idx	"column"-ID
     * @param idy	"row"-ID
     * @param text	The text this button will display
     */
    public AdvJButton(int id, int idx, int idy, String text)
    {
        super(text);
        this.id = id;
        this.idx = idx;
        this.idy = idy;
    }

    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;

}
