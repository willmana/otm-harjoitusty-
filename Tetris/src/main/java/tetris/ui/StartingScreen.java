package tetris.ui;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;


public class StartingScreen extends JFrame{
    
    public StartingScreen() {
        initUI();
    }
    
    private void initUI() {
        JButton newGameButton = new JButton("New Game");
        JButton controlsButton = new JButton("Controls");
        JButton highScoreButton = new JButton("Highscores");
        JButton quitButton = new JButton("Exit");
        JPanel panel = new JPanel();
        
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

        panel.add(newGameButton);
        panel.add(controlsButton);
        panel.add(highScoreButton);
        panel.add(quitButton);
        this.getContentPane().add(panel);
        
        setTitle("Welcome");
        setSize(150,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

}
