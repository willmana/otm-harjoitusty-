package tetris.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import tetris.main.Board;

public class Tetris extends JFrame {

    JLabel statusbar;

    public Tetris() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        board.start();

        setSize(200, 400);
        setTitle("Tetris");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    public JLabel getStatusBar() {
        return statusbar;
    }
}