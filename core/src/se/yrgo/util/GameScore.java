package se.yrgo.util;

public class GameScore {
    private static Integer score;
    private static Integer easyHighScore = 0;
    private static Integer mediumHighScore = 0;
    private static Integer hardHighScore = 0;

    public static void setScore(float birdPosition) {
        score = (int) birdPosition / 10;
    }

    public static void setBestScore() {
        if (Settings.getLevel() == 2) {
            if (score > hardHighScore) {
                hardHighScore = score;
            }
        }
        else if (Settings.getLevel() == 1) {
            if (score > mediumHighScore) {
                mediumHighScore = score;
            }
        }
        else {
            if (score > easyHighScore) {
                easyHighScore = score;
            }
        }
    }

    public static String getScoreString() {
        return score.toString();
    }

    public static String getHighScoreString() {
        if (Settings.getLevel() == 2) {
            return hardHighScore.toString();
        }
        else if (Settings.getLevel() == 1) {
            return mediumHighScore.toString();
        }
        else {
            return easyHighScore.toString();
        }
    }

    public static Integer getScore() {
        return score;
    }

}
