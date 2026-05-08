package util;

public class ScoreManager {
    private static ScoreManager instance;
    private int highscore;
    private static final String SAVE_FILE = "highscore.txt";

    private ScoreManager() {
        this.highscore = 0;
    }

    public static ScoreManager getInstance() {
        if (instance == null) {
            instance = new ScoreManager();
        }
        return instance;
    }

    public int calculate(int lines, int level) {
        return lines*100*level;
    }

}
