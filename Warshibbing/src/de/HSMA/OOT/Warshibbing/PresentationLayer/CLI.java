package de.HSMA.OOT.Warshibbing.PresentationLayer;

import de.HSMA.OOT.Warshibbing.BusinessLayer.Board;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Field;
import de.HSMA.OOT.Warshibbing.BusinessLayer.Player;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.Timer;

/**
 *
 * @author Tobias
 */
public class CLI extends Board
{
    private static Timer timer;
    private boolean aborted = false;
    private static CLI instance = null;
    
    public static void main(String[] args)
    {
        timer = new Timer(15*60*1000, new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(15);
            }
        });
        
        instance = new CLI(11,7,2);
        instance.Initialize();
        instance.Start();
    }
    
    public CLI()
    {
        super();
    }
    
    public CLI(int width, int heigth)
    {
        super(width, heigth);
    }
    
    public CLI(int width, int heigth, int playerCount)
    {
        super(width, heigth, playerCount);
    }

    @Override
    public boolean Mark(Player player, Point destination)
    {
        Field f = getField(player, destination);
        f.setMark();
        return f.IsHit();
    }

    @Override
    public void Draw()
    {
        String firstLine = "   ";
        
        for(int i = 0; i < this.width; i++)
            firstLine += " " + (char)((int)'A'+i) + " ";
        
        for(int p = 0; p < this.players; p++)
        {   
            System.out.println("Player: "+(p+1));
            
            for(int y = 0; y < this.height; y++)
            {
                if(y == 0)
                    System.out.println(firstLine);
                
                
                System.out.print((y+1) + " |");
                
                
                for(int x = 0; x < this.width; x++)
                {  
                    if(x == this.width-1)
                    {
                        System.out.println(game[p][x][y] + " |");
                    }
                    else
                        System.out.print(game[p][x][y]+" |");  
                }
            }
            
            System.out.println("");
        }
    }

    @Override
    public PresentationHelper GetPresentationHelper()
    {
        return new PresentationHelper.CommandLinePresentationHelper();
    }

    private void Start()
    {
        PresentationHelper ph = GetPresentationHelper();
        String Border = "****************************************";
        
        String menu = Border+"\n* Menu \n" + Border+"\n";
               menu += "* exit \t\t - exits the game\n";
               menu += "* ?. help. anything - show menu \n";
               menu += Border;
        do
        {
            try
            {
                String inp = ph.GetStringInput(menu);
            }
            catch(Exception e)
            {
                
            }
            
        }while(!aborted);
    }
}
