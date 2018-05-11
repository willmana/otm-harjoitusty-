package tetris.ui;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class StartingScreen extends JFrame{
    
    public StartingScreen() {
        initUI();
    }
    
    /**
     * Just creates the starting screen with all the buttons.
     */
    private void initUI() {
        JButton newGameButton = new JButton("New Game");
        JButton controlsButton = new JButton("Controls");
        JButton highScoreButton = new JButton("Highscores");
        JButton quitButton = new JButton("Exit");
        JPanel panel = new JPanel();
        
        newGameButton.setToolTipText("Click here to start a new game.");
        controlsButton.setToolTipText("Click here to view controls.");
        highScoreButton.setToolTipText("Click here to see if your score made it to highscores!");
        quitButton.setToolTipText("Exit the game from here.");
        
        quitButton.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        newGameButton.addActionListener((ActionEvent event) -> {
            Tetris tetris = new Tetris();
            tetris.setLocationRelativeTo(null); //centering the game
            tetris.setVisible(true);
        });
        
        controlsButton.addActionListener((ActionEvent event) -> {
            Controls controls = new Controls();
            controls.setLocationRelativeTo(null);
            controls.setVisible(true);
        });
        
        highScoreButton.addActionListener((ActionEvent event) -> {
            try {
                HighScoreScreen hs = new HighScoreScreen();
                hs.setLocationRelativeTo(null);
                hs.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(StartingScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });

        panel.add(newGameButton);
        panel.add(controlsButton);
        panel.add(highScoreButton);
        panel.add(quitButton);
        panel.setBackground(Color.WHITE);
        this.getContentPane().add(panel);
        
        setTitle("Welcome!");
        setSize(150,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

}
