package de.HS.MA_68199.ME1321286_MT1332105_TB1329216.OOT.Projekt.Warshibbing;

import de.HS.MA_68199.ME1321286_MT1332105_TB1329216.OOT.Projekt.Warshibbing.UI.Windows.MainWindow;
import java.awt.EventQueue;

public class Main {

    //Variables
    private static MainWindow mainWindow;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    mainWindow = new MainWindow();
                    mainWindow.setup(10, 10);
                    mainWindow.show();
                } catch (Exception e) {

                }
            }
        });
    }
}
