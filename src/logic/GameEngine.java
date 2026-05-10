package logic;

import util.GameObserver;

public class GameEngine {

    private Board board;
    private Piece currentPiece;
    private GameObserver observer;
    private int score = 0;

    public GameEngine(GameObserver observer) {
        this.observer = observer;
        board = new Board();
        currentPiece = new Piece();
        updateBoard();
    }

    public void moveLeft() {
        currentPiece.moveLeft();

        if (board.isColliding(currentPiece)) {
            currentPiece.moveRight();
        }

        updateBoard();
    }

    public void moveRight() {
        currentPiece.moveRight();

        if (board.isColliding(currentPiece)) {
            currentPiece.moveLeft();
        }

        updateBoard();
    }

    public void rotate() {
        currentPiece.rotate();

        if (board.isColliding(currentPiece)) {
            currentPiece.rotate();
            currentPiece.rotate();
            currentPiece.rotate();
        }

        updateBoard();
    }

    public void moveDown() {
        currentPiece.moveDown();

        if (board.isColliding(currentPiece)) {
            currentPiece.moveUp();

            board.placePiece(currentPiece);

            int cleared = board.clearLines();
            score += cleared * 100;

            observer.onScoreChanged(score);

            currentPiece = new Piece();

            if (board.isColliding(currentPiece)) {
                observer.onGameOver();
                return;
            }
        }

        updateBoard();
    }

    private void updateBoard() {
        int[][] tempGrid = new int[Board.ROWS][Board.COLS];

        int[][] boardGrid = board.getGrid();

        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLS; j++) {
                tempGrid[i][j] = boardGrid[i][j];
            }
        }

        int[][] shape = currentPiece.getShape();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    int row = currentPiece.getY() + i;
                    int col = currentPiece.getX() + j;

                    if (row >= 0 && row < Board.ROWS &&
                        col >= 0 && col < Board.COLS) {
                        tempGrid[row][col] = currentPiece.getType() + 1;
                    }
                }
            }
        }

        observer.onBoardChanged(tempGrid);
    }
}