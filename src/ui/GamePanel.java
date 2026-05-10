package ui;

import util.GameObserver;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class GamePanel extends JPanel implements GameObserver {
    private int[][] board;
    public GamePanel() {
        board = new int[20][10];
        board[0][4] = 1;
        board[1][4] = 1;
        board[2][4] = 1;
        board[3][4] = 1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int blockSize = 30;
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 10; col++) {
                if (board[row][col] != 0) {
                    g.setColor(Color.CYAN);
                    g.fillRect(col * blockSize, row * blockSize,
                               blockSize, blockSize);
                }
                g.setColor(Color.GRAY);
                g.drawRect(col * blockSize, row * blockSize,
                           blockSize, blockSize);
            }
        }
    }

    @Override
    public void onBoardChanged(int[][] board) {
        this.board = board;
        repaint();
    }

    @Override
    public void onScoreChanged(int score) {}

    @Override
    public void onGameOver() {}
}