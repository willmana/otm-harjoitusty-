package tetris.ui;

import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.*;
import tetris.db.*;


public class HighScoreScreen extends JFrame{
    
    private Database database;
    private HighScoreDao scores;
    
    public HighScoreScreen() throws SQLException {
        this.database = new Database("jdbc:sqlite:highscore.db");
        this.scores = new HighScoreDao(this.database);
        initUI();
    }
    
    /**
     * We take highscore list from the database and display it in our GUI.
     */
    public void initUI() throws SQLException {
        JLabel label = new JLabel("");
        String txt = "";
        for (int i = 0; i < scores.findTop().size(); i++) {
            txt += (i + 1 + ". " + scores.findTop().get(i).getPlayer() + "  ----  " 
                    + scores.findTop().get(i).getPoints() + "<br>");
        }
        label.setText("<html>"
                + txt
                + "</html>");
        JPanel panel = new JPanel();
        panel.add(label);
        this.getContentPane().add(panel);
        
        setSize(250, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
}
