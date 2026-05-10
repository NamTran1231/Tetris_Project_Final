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
    private int gravity;
    private int gravityThreshold;

        public GameEngine() {
        this.board = new Board();
        this.score = 0;
        this.gravity = 0;
        this.gravityThreshold = 30;
        this.level = 1;
        this.isGameOver = false;
        this.currentPiece = generateRandomPiece();
        this.nextPiece = generateRandomPiece();
    }

    private Piece generateRandomPiece() {
        int type = (int) (Math.random() * Piece.SHAPES.length);
        return new Piece(type);
    }

    public void tick() {
        if (isGameOver) return;

    gravity++;

        int currentThreshold = Math.max(5, gravityThreshold - (level - 1) * 3);
        
        if (gravity >= currentThreshold) {
            gravity = 0; 
            currentPiece.setY(currentPiece.getY() + 1);
        }

        if (board.isColliding(currentPiece)) {
            currentPiece.setY(currentPiece.getY() - 1); 
            board.placePiece(currentPiece);

                if (observer != null) {
                    observer.onScoreChanged(score);
                }
            }

            currentPiece = nextPiece;
            nextPiece = generateRandomPiece();
            gravity = 0; 

            if (board.isColliding(currentPiece)) {
                isGameOver = true;
                if (observer != null) {
                    observer.onGameOver();
                }
                return;
            }
        
        if (observer != null) {
            observer.onBoardChanged(board.getGrid());
        }
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
    
    public void setObserver(GameObserver observer) {
        this.observer = observer;
    }

    public void hardDrop() {
        while (true) {
            currentPiece.setY(currentPiece.getY() + 1);
            if (board.isColliding(currentPiece)) {
                currentPiece.setY(currentPiece.getY() - 1);
                break;
            }
        }

        board.placePiece(currentPiece);
        currentPiece = nextPiece;
        nextPiece = generateRandomPiece();

        if (board.isColliding(currentPiece)) {
            isGameOver = true;
            if (observer != null) {
                observer.onGameOver();
            }
            return;
        }
    }

    public void softDrop() {
        for (int i = 0; i < 1; i++) {
            currentPiece.setY(currentPiece.getY() + 1);
            if (board.isColliding(currentPiece)) {
                currentPiece.setY(currentPiece.getY() - 1);
                board.placePiece(currentPiece);

                currentPiece = nextPiece;
                nextPiece = generateRandomPiece();

                if (board.isColliding(currentPiece)) {
                    isGameOver = true;
                    if (observer != null) {
                        observer.onGameOver();
                    }
                    return;
                }
                break;
            }
        }
    }

    public void handleInput(String key) {
        if (isGameOver) {
            return;
        }

        switch (key.toUpperCase()) {
            case "LEFT":
                currentPiece.setX(currentPiece.getX() - 1);
                if (board.isColliding(currentPiece)) {
                    currentPiece.setX(currentPiece.getX() + 1);
                }
                break;
            case "RIGHT":
                currentPiece.setX(currentPiece.getX() + 1);
                if (board.isColliding(currentPiece)) {
                    currentPiece.setX(currentPiece.getX() - 1);
                }
                break;
            case "DOWN":
                softDrop();
                break;
            case "ROTATE":
                currentPiece.rotate();
                if (board.isColliding(currentPiece)) {
                    currentPiece.rotate();
                    currentPiece.rotate();
                    currentPiece.rotate();
                }
                break;
            case "SPACE":
                hardDrop();
                break;
        }

        if (observer != null) {
            observer.onBoardChanged(board.getGrid());
        }
    }
    
}

