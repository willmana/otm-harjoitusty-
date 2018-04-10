package tetris;

import java.util.Random;

public class Shape {

    enum Tetrominos {
        NoShape(new int[][]{
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0}}),
        ZShape(new int[][]{
            {0, -1},
            {0, 0},
            {-1, 0},
            {-1, 1}}),
        SShape(new int[][]{
            {0, -1},
            {0, 0},
            {1, 0},
            {1, 1}}),
        LineShape(new int[][]{
            {0, -1},
            {0, 1},
            {0, 0},
            {0, 2}}),
        TShape(new int[][]{
            {-1, 0},
            {0, 0},
            {1, 0},
            {0, 1}}),
        SquareShape(new int[][]{
            {0, 0},
            {1, 0},
            {0, 1},
            {1, 1}}),
        LShape(new int[][]{
            {-1, -1},
            {0, -1},
            {0, 0},
            {0, 1}}),
        MirrorLShape(new int[][]{
            {1, -1},
            {0, -1},
            {0, 0},
            {0, 1}});

        public int[][] coordinates;

        private Tetrominos(int[][] coordinates) {
            this.coordinates = coordinates;
        }

    }

    private Tetrominos pieceShape;
    private int[][] coordinates;

    public Shape() {
        coordinates = new int[4][2];
    }

    public void setShape(Tetrominos shape) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                coordinates[i][j] = shape.coordinates[i][j];
            }
        }
        pieceShape = shape;
    }

    private void setX(int index, int x) {
        coordinates[index][0] = x;
    }

    private void setY(int index, int y) {
        coordinates[index][1] = y;
    }

    public int x(int index) {
        return coordinates[index][0];
    }

    public int y(int index) {
        return coordinates[index][1];
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
        int m = coordinates[0][0];

        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coordinates[i][0]);
        }

        return m;
    }

    public int minY() {
        int m = coordinates[0][1];

        for (int i = 0; i < 4; i++) {
            m = Math.min(m, coordinates[i][1]);
        }

        return m;
    }
}
