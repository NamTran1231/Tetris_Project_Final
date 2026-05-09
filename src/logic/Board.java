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

    public int clearLines() {
    int linesCleared = 0;

    for (int row = ROWS - 1; row >= 0; row--) {
        if (isLineFull(row)) {
            removeLine(row);
            linesCleared++;
            row++; 
        }
    }
    return linesCleared;
}

private boolean isLineFull(int row) {
    for (int col = 0; col < COLS; col++)
        if (grid[row][col] == 0)
            return false;
    return true;
}

private void removeLine(int row) {
    // Dồn tất cả hàng phía trên xuống 1
    for (int r = row; r > 0; r--)
        for (int col = 0; col < COLS; col++)
            grid[r][col] = grid[r - 1][col];

    
    for (int col = 0; col < COLS; col++)
        grid[0][col] = 0;
}

    public boolean isColliding(Piece piece) {
    int[][] shape = piece.getShape();
    int px = piece.getX();
    int py = piece.getY();

    for (int i = 0; i < shape.length; i++) {
        for (int j = 0; j < shape[0].length; j++) {

            // Bỏ qua ô trống của gạch
            if (shape[i][j] == 0) continue;

            // Tọa độ thật trên board
            int row = py + i;
            int col = px + j;

            // Kiểm tra ra ngoài biên
            if (row < 0 || row >= ROWS) return true;
            if (col < 0 || col >= COLS) return true;

            // Kiểm tra ô đã có gạch
            if (grid[row][col] != 0) return true;
        }
    }
    return false; // không va chạm
}

}