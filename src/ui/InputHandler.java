package ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InputHandler extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                break;
            case KeyEvent.VK_UP:
                System.out.println("ROTATE");
                break;
        }
    }
}