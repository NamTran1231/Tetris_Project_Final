package logic;

public class Piece {

    public static final int[][][] SHAPES = {
        {{1,1,1,1}},
        {{1,1},{1,1}},
        {{0,1,0},{1,1,1}},
        {{0,1,1},{1,1,0}},
        {{1,1,0},{0,1,1}},
        {{1,0,0},{1,1,1}},
        {{0,0,1},{1,1,1}}
    };

    private int[][] shape;
    private int x, y;
    private int type;

    public Piece(int type) {
        this.type = type;
        int rows = SHAPES[type].length;
        int cols = SHAPES[type][0].length;
        this.shape = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                this.shape[i][j] = SHAPES[type][i][j];
        this.x = 3;
        this.y = 0;
    }

    // Clone
    public Piece clone() {
        Piece copy = new Piece(this.type);
        copy.x = this.x;
        copy.y = this.y;
        int rows = this.shape.length;
        int cols = this.shape[0].length;
        copy.shape = new int[rows][cols];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                copy.shape[i][j] = this.shape[i][j];
        return copy;
    }

    // Rotate
    public void rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] newShape = new int[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                newShape[j][rows - 1 - i] = shape[i][j];
        this.shape = newShape;
    }

    // Getters
    public int[][] getShape() { return shape; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getType() { return type; }

    // Setters
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setShape(int[][] shape) { this.shape = shape; }
}