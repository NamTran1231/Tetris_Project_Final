package ui;

import logic.GameEngine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

    private GameEngine engine;

    public InputHandler(GameEngine engine) {
        this.engine = engine;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_LEFT:
                engine.moveLeft();
                break;

            case KeyEvent.VK_RIGHT:
                engine.moveRight();
                break;

            case KeyEvent.VK_DOWN:
                engine.moveDown();
                break;

            case KeyEvent.VK_UP:
                engine.rotate();
                break;
        }
    }
}