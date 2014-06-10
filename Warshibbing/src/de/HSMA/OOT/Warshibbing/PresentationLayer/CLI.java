package de.HSMA.OOT.Warshibbing.PresentationLayer;

import de.HSMA.OOT.Warshibbing.BusinessLayer.Board;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Player;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Tobias
 */
public class CLI extends Board
{
    private static Timer timer;
    
    public static void main(String[] args)
    {
        timer = new Timer(15*60*1000, new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(15);
            }
        });
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
        return new PresentationHelper.CommandLinePresentationHelper();
    }
}
