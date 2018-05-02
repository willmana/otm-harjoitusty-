package tetris.main;

import java.util.Random;

public class Shape {
    //Tetrominos enum has all the different shapes. Also we have the empty shape NoShape
    enum Tetrominos {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    };

    private Tetrominos pieceShape;
    private int coords[][];
    private int[][][] coordsTable;

    //Array coords in the constructor holds all the actual coordinates of pieces
    public Shape() {

        coords = new int[4][2];
        setShape(Tetrominos.NoShape);

    }

    public void setShape(Tetrominos shape) {
        //coordsTable is a template from which all pieces take their coordinate values.
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
    
    //This rotates piece to the left. You can use coordinate system to understand why 
    //this method works this way.
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
    
    //This rotates piece to the right.
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
