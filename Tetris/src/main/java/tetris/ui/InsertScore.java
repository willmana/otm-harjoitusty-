package tetris.ui;


import java.awt.event.ActionEvent;
import java.sql.SQLException;
import tetris.db.*;
import javax.swing.*;
import tetris.main.Board;


public class InsertScore extends JFrame{
    
    private JLabel label;

    public InsertScore(Board board) {
        initUI(board);
    }
    
    /**
     * After game is over, this window pops up and 
     * you can submit your score and start a new game after that.
     * 
     * @param Board
     *
     */
    private void initUI(Board board) {
        JTextField field = new JTextField(15);
        label = new JLabel("<html>"
                + "Game over!<br>"
                + "Score: " + board.getScore() + ", enter your name.<br>"
                        + "</html>");
        
        JButton submit = new JButton("Submit score");
        JButton restart = new JButton("New game");
        JButton closeAll = new JButton("Close the game.");
        
        restart.addActionListener((ActionEvent event) -> {
            super.dispose();
            Tetris tetris = new Tetris();
            tetris.setLocationRelativeTo(null);
            tetris.setVisible(true);
        });
        closeAll.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        submit.addActionListener((ActionEvent event) -> {
                HighScore highscore = new HighScore(field.getText(), board.getScore());
            try {
                board.getScores().saveOrUpdate(highscore);
            } catch (SQLException ex) {

            }
            field.setText("");
            
        });

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(field);
        panel.add(submit);
        panel.add(restart);
        panel.add(closeAll);

        this.getContentPane().add(panel);
        
        setTitle("Game over!");
        setSize(300, 200);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
    }    
    
}
