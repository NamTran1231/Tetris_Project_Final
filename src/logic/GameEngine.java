package logic;

import util.GameObserver;

public class GameEngine {
    private Board board;
    private Piece currentPiece;
    private Piece nextPiece;
    private GameObserver observer;
    private int score;
    private boolean isGameOver;
    private int level;

        public GameEngine() {
        this.board = new Board();
        this.score = 0;
        this.level = 1;
        this.isGameOver = false;
        this.currentPiece = generateRandomPiece();
        this.nextPiece = generateRandomPiece();
    }

    private Piece generateRandomPiece() {
        int type = (int) (Math.random() * Piece.SHAPES.length);
        return new Piece(type);
    }

    public void reset() {
        board.reset();
        score = 0;
        level = 1;
        isGameOver = false;
        currentPiece = generateRandomPiece();
        nextPiece = generateRandomPiece();

        if (observer != null) {
            observer.onBoardChanged(board.getGrid());
        }
    }

    public Board getBoard() {
        return board;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public Piece getNextPiece() {
        return nextPiece;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public boolean isGameOver() {
        return isGameOver;
    }    
}

