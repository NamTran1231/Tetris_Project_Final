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
}
