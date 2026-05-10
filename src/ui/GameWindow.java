package ui;

import logic.GameEngine;
import javax.swing.*;
import java.awt.event.*;

public class GameWindow extends JFrame {

    public GameWindow() {
        GamePanel panel = new GamePanel();
        GameEngine engine = new GameEngine(panel);

        setTitle("Tetris");
        setSize(320, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        
        InputMap im = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = panel.getActionMap();

        im.put(KeyStroke.getKeyStroke("LEFT"),  "left");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        im.put(KeyStroke.getKeyStroke("DOWN"),  "down");
        im.put(KeyStroke.getKeyStroke("UP"),    "up");
        im.put(KeyStroke.getKeyStroke("SPACE"), "space");

        am.put("left",  new AbstractAction() { public void actionPerformed(ActionEvent e) { engine.handleInput("LEFT"); }});
        am.put("right", new AbstractAction() { public void actionPerformed(ActionEvent e) { engine.handleInput("RIGHT"); }});
        am.put("down",  new AbstractAction() { public void actionPerformed(ActionEvent e) { engine.handleInput("DOWN"); }});
        am.put("up",    new AbstractAction() { public void actionPerformed(ActionEvent e) { engine.handleInput("UP"); }});
        am.put("space", new AbstractAction() { public void actionPerformed(ActionEvent e) { engine.handleInput("SPACE"); }});

        Timer timer = new Timer(500, e -> engine.tick());
        timer.start();

        add(panel);
        setVisible(true);
    }
}