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
                engine.handleInput("LEFT");
                break;

            case KeyEvent.VK_RIGHT:
                engine.handleInput("RIGHT");
                break;

            case KeyEvent.VK_DOWN:
                engine.handleInput("DOWN");
                break;

            case KeyEvent.VK_UP:
                engine.handleInput("UP");
                break;
        }
    }
}