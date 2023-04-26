package se.yrgo.util;

public class Score {
    private static Integer score;
    private static Integer highScore;
    public static Boolean isNewHighscore;

    public static void setScore(float birdPosition) {
        score = (int) birdPosition / 10;
    }

    public static void setHighScore() {
        isNewHighscore = false;
        if (highScore == null) {
            highScore = 0;
        }
        if (score > highScore) {
            highScore = score;
            isNewHighscore = true;
        }
    }

    public static String getScoreString() {
        return score.toString();
    }

    public static String getHighScoreString() {
        return highScore.toString();
    }

    public static Integer getScore() {
        return score;
    }

    public static Integer getHighScore() {
        return highScore;
    }


}
