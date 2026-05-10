package ui;

import logic.GameEngine;
import javax.swing.JFrame;
import javax.swing.Timer;

public class GameWindow extends JFrame {

    public GameWindow() {
        // 1. Tạo panel và engine
        GamePanel panel = new GamePanel();
        GameEngine engine = new GameEngine(panel);

        // 2. Cài đặt cửa sổ
        setTitle("Tetris");
        setSize(320, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // 3. Kết nối input với engine
        addKeyListener(new InputHandler(engine));
        setFocusable(true);

        Timer timer = new Timer(500, e -> engine.tick());
        timer.start();

        add(panel);
        setVisible(true);
    }
}