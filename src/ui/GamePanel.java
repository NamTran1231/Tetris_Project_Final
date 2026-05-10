package ui;

import util.GameObserver;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GamePanel extends JPanel implements GameObserver {

    private int blockRow = 0;
    private int blockCol = 4;
    private boolean vertical = true;
    private Random random = new Random();
    private Color pieceColor = Color.CYAN;
    private int pieceType = 0;
    private int score = 0;
    private boolean gameOver = false;

    private Timer timer;

    public GamePanel() {

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gameOver) {
                    moveDown();
                }
            }
        });

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int blockSize = 30;
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 50, 250);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Final Score: " + score, 90, 300);
            return;
        }
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 10; col++) {
                g.setColor(Color.GRAY);
                g.drawRect(
                        col * blockSize,
                        row * blockSize + 40,
                        blockSize,
                        blockSize
                );
            }
        }
        g.setColor(pieceColor);

        int yOffset = 40;

        if (pieceType == 0) {

            for (int i = 0; i < 4; i++) {
                if (vertical) {
                    g.fillRect(
                            blockCol * blockSize,
                            (blockRow + i) * blockSize + yOffset,
                            blockSize,
                            blockSize
                    );
                } else {
                    g.fillRect(
                            (blockCol + i) * blockSize,
                            blockRow * blockSize + yOffset,
                            blockSize,
                            blockSize
                    );
                }
            }

        } else if (pieceType == 1) {

            g.fillRect(blockCol * blockSize, blockRow * blockSize + yOffset, blockSize, blockSize);
            g.fillRect((blockCol + 1) * blockSize, blockRow * blockSize + yOffset, blockSize, blockSize);
            g.fillRect(blockCol * blockSize, (blockRow + 1) * blockSize + yOffset, blockSize, blockSize);
            g.fillRect((blockCol + 1) * blockSize, (blockRow + 1) * blockSize + yOffset, blockSize, blockSize);

        } else {

            g.fillRect(blockCol * blockSize, blockRow * blockSize + yOffset, blockSize, blockSize);
            g.fillRect((blockCol + 1) * blockSize, blockRow * blockSize + yOffset, blockSize, blockSize);
            g.fillRect((blockCol + 2) * blockSize, blockRow * blockSize + yOffset, blockSize, blockSize);
            g.fillRect((blockCol + 1) * blockSize, (blockRow + 1) * blockSize + yOffset, blockSize, blockSize);
        }
    }

    public void moveLeft() {
        if (!gameOver && blockCol > 0) {
            blockCol--;
            repaint();
        }
    }

    public void moveRight() {
        if (!gameOver && blockCol < 7) {
            blockCol++;
            repaint();
        }
    }

    public void moveDown() {
        if (blockRow < 18) {
            blockRow++;
        } else {
            spawnNewPiece();
        }
        repaint();
    }

    public void rotate() {
        if (!gameOver && pieceType != 1) {
            vertical = !vertical;
            repaint();
        }
    }

    public void spawnNewPiece() {
        blockRow = 0;
        blockCol = random.nextInt(7);
        pieceType = random.nextInt(3);
        vertical = random.nextBoolean();
        score += 10;
        if (score >= 100) {
            onGameOver();
            return;
        }
        int colorIndex = random.nextInt(5);
        switch (colorIndex) {
            case 0:
                pieceColor = Color.CYAN;
                break;
            case 1:
                pieceColor = Color.RED;
                break;
            case 2:
                pieceColor = Color.GREEN;
                break;
            case 3:
                pieceColor = Color.YELLOW;
                break;
            case 4:
                pieceColor = Color.MAGENTA;
                break;
        }
    }

    @Override
    public void onBoardChanged(int[][] board) {
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
        timer.stop();
        repaint();
    }
}