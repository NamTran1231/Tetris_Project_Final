package logic;

import util.GameObserver;
import java.util.Random;

public class GameEngine {

    private Board board;
    private Piece currentPiece;
    private Piece nextPiece;
    private GameObserver observer;
    private Random random = new Random();
    private int score = 0;
    private int level = 1;
    private boolean isGameOver = false;

    public GameEngine(GameObserver observer) {
        this.observer = observer;
        board = new Board();
        currentPiece = new Piece(random.nextInt(7));
        nextPiece = new Piece(random.nextInt(7));
        updateBoard();
    }

    // Gọi mỗi 500ms từ Timer trong GameWindow
    public void tick() {
        if (isGameOver) return;

        currentPiece.setY(currentPiece.getY() + 1);

        if (board.isColliding(currentPiece)) {
            currentPiece.setY(currentPiece.getY() - 1);
            board.placePiece(currentPiece);

            int cleared = board.clearLines();
            if (cleared > 0) {
                score += cleared * 100 * level;
                level = score / 500 + 1;
                observer.onScoreChanged(score);
            }

            currentPiece = nextPiece;
            nextPiece = new Piece(random.nextInt(7));

            if (board.isColliding(currentPiece)) {
                isGameOver = true;
                observer.onGameOver();
                return;
            }
        }

        updateBoard();
    }

    // Gọi từ InputHandler
    public void handleInput(String key) {
        if (isGameOver) return;

        switch (key) {
            case "LEFT":
                currentPiece.setX(currentPiece.getX() - 1);
                if (board.isColliding(currentPiece))
                    currentPiece.setX(currentPiece.getX() + 1);
                break;

            case "RIGHT":
                currentPiece.setX(currentPiece.getX() + 1);
                if (board.isColliding(currentPiece))
                    currentPiece.setX(currentPiece.getX() - 1);
                break;

            case "DOWN":
                currentPiece.setY(currentPiece.getY() + 1);
                if (board.isColliding(currentPiece))
                    currentPiece.setY(currentPiece.getY() - 1);
                break;

            case "UP":
                Piece copy = currentPiece.clone();
                copy.rotate();
                if (!board.isColliding(copy))
                    currentPiece.rotate();
                break;

            case "SPACE":
                while (!board.isColliding(currentPiece))
                    currentPiece.setY(currentPiece.getY() + 1);
                currentPiece.setY(currentPiece.getY() - 1);
                tick();
                break;
        }

        updateBoard();
    }

    public void reset() {
        board.reset();
        score = 0;
        level = 1;
        isGameOver = false;
        currentPiece = new Piece(random.nextInt(7));
        nextPiece = new Piece(random.nextInt(7));
        updateBoard();
    }

    // Vẽ board + gạch đang rơi vào 1 grid tạm → gửi cho UI
    private void updateBoard() {
        int[][] tempGrid = new int[Board.ROWS][Board.COLS];
        int[][] boardGrid = board.getGrid();

        for (int i = 0; i < Board.ROWS; i++)
            for (int j = 0; j < Board.COLS; j++)
                tempGrid[i][j] = boardGrid[i][j];

        int[][] shape = currentPiece.getShape();
        for (int i = 0; i < shape.length; i++)
            for (int j = 0; j < shape[0].length; j++)
                if (shape[i][j] != 0) {
                    int row = currentPiece.getY() + i;
                    int col = currentPiece.getX() + j;
                    if (row >= 0 && row < Board.ROWS &&
                        col >= 0 && col < Board.COLS)
                        tempGrid[row][col] = currentPiece.getType() + 1;
                }

        observer.onBoardChanged(tempGrid);
    }
}