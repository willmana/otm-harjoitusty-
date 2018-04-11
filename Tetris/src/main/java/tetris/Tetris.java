
package tetris;

import java.awt.BorderLayout;
import javax.swing.*;

public class Tetris extends JFrame{
    private JLabel statusBar;
    
    public Tetris() {
        statusBar = new JLabel("0");
        add(statusBar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        
        board.newPiece();
        board.repaint();
        
        setSize(200, 400);
        setTitle("My Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public JLabel getStatusBar() {
        return statusBar;
    }
    

}
