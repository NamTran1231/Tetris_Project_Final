package ui;

import util.GameObserver;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class GamePanel extends JPanel implements GameObserver {

    private int[][] grid = new int[20][10];
    private int score = 0;
    private boolean gameOver = false;

    public GamePanel() {
        // để trống, GameEngine được tạo trong GameWindow
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int blockSize = 30;

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 40, 250);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Final Score: " + score, 80, 300);
            return;
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);

        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 10; col++) {
                if (grid[row][col] != 0) {
                    g.setColor(getColor(grid[row][col]));
                    g.fillRect(col * blockSize, row * blockSize + 40, blockSize, blockSize);
                }
                g.setColor(Color.GRAY);
                g.drawRect(col * blockSize, row * blockSize + 40, blockSize, blockSize);
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
        repaint();
    }
}