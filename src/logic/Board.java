package logic;

public class Board {

    public static final int COLS = 10;
    public static final int ROWS = 20;

    private int[][] grid;

    public Board() {
        grid = new int[ROWS][COLS];
    }

    public int[][] getGrid() { return grid; }

    public void reset() {
        grid = new int[ROWS][COLS];
    }

    public void placePiece(Piece piece) {
    int[][] shape = piece.getShape();
    int px = piece.getX();
    int py = piece.getY();

    for (int i = 0; i < shape.length; i++)
        for (int j = 0; j < shape[0].length; j++)
            if (shape[i][j] != 0)
                grid[py + i][px + j] = piece.getType() + 1;
}
}