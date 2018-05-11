package tetris.main;

import java.util.Random;

public class Shape {
    /**
     * Holds all seven tetris shape names and empty shape called "NoShape".
     */
    enum Tetrominos {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    };

    private Tetrominos pieceShape;
    private int coords[][];
    private int[][][] coordsTable;

    /**
     * The constructor, array coords holds the actual coordinates of a tetris piece.
     */
    public Shape() {

        coords = new int[4][2];
        setShape(Tetrominos.NoShape);

    }

    /**
     * coordsTable array holds all possible coordinate values of pieces. Like method name says
     * here we set the shape for a piece.
     * 
     * @param Tetrominos
     *
     */
    public void setShape(Tetrominos shape) {
        coordsTable = new int[][][]{
            {{0, 0}, {0, 0}, {0, 0}, {0, 0}},     // NoShape
            {{0, -1}, {0, 0}, {-1, 0}, {-1, 1}},  // ZShape
            {{0, -1}, {0, 0}, {1, 0}, {1, 1}},    // SShape
            {{0, -1}, {0, 0}, {0, 1}, {0, 2}},    // LineShape
            {{-1, 0}, {0, 0}, {1, 0}, {0, 1}},    // TShape
            {{0, 0}, {1, 0}, {0, 1}, {1, 1}},     // SquareShape
            {{-1, -1}, {0, -1}, {0, 0}, {0, 1}},  // LShape
            {{1, -1}, {0, -1}, {0, 0}, {0, 1}}    // MirroredLShape
        };
        
        //This for loop puts one row of coordinate value from coordsTable to coords-Array.
        //We also use ordinal method to return the 
        //current position of the enum type in the enum object
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; ++j) {
                coords[i][j] = coordsTable[shape.ordinal()][i][j];
            }
        }
        pieceShape = shape;

    }

    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    private void setY(int index, int y) {
        coords[index][1] = y;
    }

    public int x(int index) {
        return coords[index][0];
    }

    public int y(int index) {
        return coords[index][1];
    }

    public Tetrominos getShape() {
        return pieceShape;
    }

    /**
     * We want our pieces to generate randomly so we have this method to thank.
     */
    public void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominos[] values = Tetrominos.values();
        setShape(values[x]);
    }

    public int minX() {
        int m = coords[0][0];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][0]);
        }
        return m;
    }

    public int minY() {
        int m = coords[0][1];
        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coords[i][1]);
        }
        return m;
    }
    
    /**
     * Simply rotates the piece to left. You may use coordinate table to understand if unclear
     * why this works.
     *
     */
    public Shape rotateLeft() {
        if (pieceShape == Tetrominos.SquareShape) {
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, y(i));
            result.setY(i, -x(i));
        }
        return result;
    }
    

    /**
     * Like rotateLeft(), but right.
     */
    public Shape rotateRight() {
        if (pieceShape == Tetrominos.SquareShape) {
            return this;
        }

        Shape result = new Shape();
        result.pieceShape = pieceShape;

        for (int i = 0; i < 4; ++i) {
            result.setX(i, -y(i));
            result.setY(i, x(i));
        }
        return result;
    }
}
