package ui;

import util.GameObserver;
import util.ScoreManager;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class GamePanel extends JPanel implements GameObserver {

    private int[][] grid = new int[20][10];
    private int score = 0;
    private boolean gameOver = false;

    public GamePanel() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int blockSize = 30;

        if (gameOver) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 40, 220);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Score:  " + score, 80, 270);
            g.drawString("Best:   " + ScoreManager.getInstance().getHighscore(), 80, 310);

            return;
        }

        // Nền đen
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Score và highscore
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Score: " + score, 10, 25);

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Best:  " + ScoreManager.getInstance().getHighscore(), 10, 48);

        // Vẽ grid
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 10; col++) {
                if (grid[row][col] != 0) {
                    g.setColor(getColor(grid[row][col]));
                    g.fillRect(col * blockSize, row * blockSize + 35, blockSize - 1, blockSize - 1);
                }
                g.setColor(Color.DARK_GRAY);
                g.drawRect(col * blockSize, row * blockSize + 35, blockSize, blockSize);
            }
        }
    }

    private Color getColor(int type) {
        switch (type) {
            case 1: return Color.CYAN;
            case 2: return Color.YELLOW;
            case 3: return Color.MAGENTA;
            case 4: return Color.GREEN;
            case 5: return Color.RED;
            case 6: return Color.ORANGE;
            case 7: return Color.BLUE;
            default: return Color.WHITE;
        }
    }

    @Override
    public void onBoardChanged(int[][] board) {
        this.grid = board;
        repaint();
    }

    @Override
    public void onScoreChanged(int score) {
        this.score = score;
        repaint();
    }

    @Override
    public void onGameOver() {
        gameOver = true;
        ScoreManager.getInstance().updatehighscore(score);
        repaint();
    }
}