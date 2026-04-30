package ui;

import util.GameObserver;

public class GamePanel implements GameObserver {

    @Override
    public void onBoardChanged(int[][] board) {
        System.out.println("Board updated");
    }

    @Override
    public void onScoreChanged(int score) {
        System.out.println("Score: " + score);
    }

    @Override
    public void onGameOver() {
        System.out.println("Game Over");
    }
}