package tetris.main;

import tetris.ui.Tetris;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import tetris.main.Shape.Tetrominos;
import tetris.ui.InsertScore;
import tetris.db.*;

/**
 * Here all the actual magic happens.
 */
public class Board extends JPanel implements ActionListener {

    final int boardWidth = 10;
    final int boardHeight = 22;

    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    boolean isPaused = false;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    JLabel statusbar;
    Shape curPiece;
    Tetrominos[] board;
    private Database database;
    private HighScoreDao scores;

    /**
     * Here we input keyboard and make some prepping for game to work.
     * 
     * @param Tetris
     */
    public Board(Tetris parent) {

        setFocusable(true);
        curPiece = new Shape();
        timer = new Timer(400, this);
        timer.start();
        this.database = new Database("jdbc:sqlite:highscore.db");
        this.scores = new HighScoreDao(this.database);

        statusbar = parent.getStatusBar();
        board = new Tetrominos[boardWidth * boardHeight];
        addKeyListener(new TAdapter());
        clearBoard();
    }

    /**
     * This methods checks if piece has reached bottom, making new piece. Otherwise the piece goes
     * one line down.
     * 
     * @param ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }

    int squareWidth() {
        return (int) getSize().getWidth() / boardWidth;
    }

    int squareHeight() {
        return (int) getSize().getHeight() / boardHeight;
    }

    Tetrominos shapeAt(int x, int y) {
        return board[(y * boardWidth) + x];
    }

    /**
     * Here we actually start the game.
     */
    public void start() {
        if (isPaused) {
            return;
        }

        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();

        newPiece();
        timer.start();
    }

    /**
     * Pauses the game when player presses p-button.
     */
    private void pause() {
        if (!isStarted) {
            return;
        }

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            statusbar.setText("paused");
        } else {
            timer.start();
            statusbar.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    /**
     * Here we draw all objects on the board. First we paint all the shapes or their remains.
     * After that we pain the falling piece.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - boardHeight * squareHeight();
        for (int i = 0; i < boardHeight; ++i) {
            for (int j = 0; j < boardWidth; ++j) {
                Tetrominos shape = shapeAt(j, boardHeight - i - 1);
                if (shape != Tetrominos.NoShape) {
                    drawSquare(g, 0 + j * squareWidth(),
                            boardTop + i * squareHeight(), shape);
                }
            }
        }
        if (curPiece.getShape() != Tetrominos.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                        boardTop + (boardHeight - y - 1) * squareHeight(),
                        curPiece.getShape());
            }
        }
    }

    /**
     * If player presses spacebar, we drop the piece down.
     */
    private void dropDown() {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1)) {
                break;
            }
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown() {
        if (!tryMove(curPiece, curX, curY - 1)) {
            pieceDropped();
        }
    }

    /**
     * Fills the board with empty NoShapes.
     */
    private void clearBoard() {
        for (int i = 0; i < boardHeight * boardWidth; ++i) {
            board[i] = Tetrominos.NoShape;
        }
    }

    /**
     * Puts falling piece to our board array which holds also all remains of old pieces.
     * Then checks if we need to remove a line or more.
     */
    private void pieceDropped() {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * boardWidth) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished) {
            newPiece();
        }
    }

    /**
     * Creates new piece.
     */
    private void newPiece() {
        curPiece.setRandomShape();
        curX = boardWidth / 2 + 1;
        curY = boardHeight - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominos.NoShape);
            timer.stop();
            isStarted = false;
            
            
            InsertScore score = new InsertScore(this);
            score.setLocationRelativeTo(null);
            score.setVisible(true);
        }
    }

    /**
     * Tries to move the piece. Returns false when not possible and true when possible.
     * 
     * @param Shape
     * @param int
     * @param int
     * @return boolean
     */
    private boolean tryMove(Shape newPiece, int newX, int newY) {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
                return false;
            }
            if (shapeAt(x, y) != Tetrominos.NoShape) {
                return false;
            }
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    /**
     * Here we remove the full lines.
     */
    private void removeFullLines() {
        int numFullLines = 0;
        for (int i = boardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;
            for (int j = 0; j < boardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominos.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }
            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < boardHeight - 1; ++k) {
                    for (int j = 0; j < boardWidth; ++j) {
                        board[(k * boardWidth) + j] = shapeAt(j, k + 1);
                    }
                }
                if (numLinesRemoved % 4 == 0) {
                    if (timer.getDelay() != 150) {
                        timer.setDelay(timer.getDelay() - 5); 
                    }
                }
            }           
        }
        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominos.NoShape);
            repaint();
        }
    }

    /**
     * We draw the squares for all pieces here.
     * 
     * @param Graphics
     * @param int
     * @param int
     * @param int
     * @param Tetrominos
     */
    private void drawSquare(Graphics g, int x, int y, Tetrominos shape) {
        Color colors[] = {new Color(0, 0, 0), new Color(204, 102, 102),
            new Color(102, 204, 102), new Color(102, 102, 204),
            new Color(204, 204, 102), new Color(204, 102, 204),
            new Color(102, 204, 204), new Color(218, 170, 0)
        };

        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                x + squareWidth() - 1, y + 1);
    }
    
    public int getScore() {
        return numLinesRemoved;
    }
    
    public HighScoreDao getScores() {
        return scores;
    }
    
    

    /**
     * Since we want to control the game with keyboard, here we implement all the different occasions
     * to make it possible.
     */
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (!isStarted || curPiece.getShape() == Tetrominos.NoShape) {
                return;
            }

            int keycode = e.getKeyCode();

            if (keycode == 'p' || keycode == 'P') {
                pause();
                return;
            }

            if (isPaused) {
                return;
            }

            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    tryMove(curPiece, curX - 1, curY);
                    break;
                case KeyEvent.VK_RIGHT:
                    tryMove(curPiece, curX + 1, curY);
                    break;
                case KeyEvent.VK_DOWN:
                    oneLineDown();
                    break;
                case KeyEvent.VK_UP:
                    tryMove(curPiece.rotateLeft(), curX, curY);
                    break;
                case KeyEvent.VK_SPACE:
                    dropDown();
                    break;
            }

        }
    }
}
