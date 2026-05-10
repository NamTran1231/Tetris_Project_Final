package util;

import java.io.FileWriter;
import java.io.IOException;

public class ScoreManager {
    private static ScoreManager instance;
    private int highscore;
    private static final String SAVE_FILE = "highscore.txt";
    private ScoreManager() {
        this.highscore = loadHighscore();
    }
    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }
    public int calculate(int lines, int level) {
        int baseScore = 0;
        switch (lines) {
            case 1:
                baseScore = 100;
                break;
            case 2:
                baseScore = 300;
                break;
            case 3:
                baseScore = 500;
                break;
            case 4:
                baseScore = 800;
                break;
            default:
                baseScore = 0;
        }
        return baseScore * level;
    }
        private void saveHighscore() {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            writer.write(Integer.toString(highscore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private int loadHighscore() {
    try {
        java.io.BufferedReader reader = new java.io.BufferedReader(
            new java.io.FileReader(SAVE_FILE));
        int saved = Integer.parseInt(reader.readLine().trim());
        reader.close();
        return saved;
    } catch (Exception e) {
        return 0;
    }
}
    public void updatehighscore (int score) {
        if (score > highscore) {
            highscore = score;
            saveHighscore();
        }
    }
    public int getHighscore() {
        return highscore;
    }
}
