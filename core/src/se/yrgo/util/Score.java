package se.yrgo.util;

import se.yrgo.Sprites.Bird;
import se.yrgo.screens.GameScreen;

public class Score {
    private static Float score;
    private static Float highScore;

    public static void setScore(float birdPosition) {
        score = birdPosition;
    }

    public static void setHighScore() {
        if(highScore == null) {
            highScore = 0F;
        }
        if (score > highScore) {
            highScore = score;
        }
    }
    public static String printScore() {
        return score.toString();
    }

    public static String printHighScore() {
        return highScore.toString();
    }
    public static float getPosition() {
        return score;
    }
}
