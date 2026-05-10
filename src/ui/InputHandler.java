package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

    private GamePanel panel;

    public InputHandler(GamePanel panel) {
        this.panel = panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                panel.moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
                panel.moveRight();
                break;

            case KeyEvent.VK_DOWN:
                panel.moveDown();
                break;

            case KeyEvent.VK_UP:
                panel.rotate();
                break;
        }
    }
}