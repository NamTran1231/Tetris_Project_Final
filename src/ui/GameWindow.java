package ui;

import logic.GameEngine;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameWindow extends JFrame {

    public GameWindow() {
        GamePanel panel = new GamePanel();
        GameEngine engine = new GameEngine(panel);

        setTitle("Tetris");
        setSize(320, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setFocusable(true);

        addKeyListener(new InputHandler(engine));

        Timer timer = new Timer(500, e -> engine.tick());
        timer.start();

        add(panel);
        setVisible(true);
        requestFocusInWindow(); // gọi sau setVisible
    }
}