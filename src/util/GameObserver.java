package util;

public interface GameObserver {
    void onBoardChanged(int[][] board);
    void onScoreChanged(int score);
    void onGameOver();
}