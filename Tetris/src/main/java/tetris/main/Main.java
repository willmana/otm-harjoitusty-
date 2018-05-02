package tetris.main;

import java.awt.EventQueue;
import tetris.ui.StartingScreen;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            StartingScreen ex = new StartingScreen();
            ex.setVisible(true);
        });

    }
}
