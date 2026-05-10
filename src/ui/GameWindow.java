package ui;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GameWindow extends JFrame {

    private GamePanel panel;

    public GameWindow() {

        panel = new GamePanel();

        setTitle("Tetris");
        setSize(320, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        add(panel);

        addKeyListener(new InputHandler(panel.getEngine()));

        Timer timer = new Timer(500, e -> {
            panel.getEngine().moveDown();
        });

        timer.start();

        setVisible(true);
    }
}