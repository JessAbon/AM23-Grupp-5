package se.yrgo.util;

import java.sql.Array;
import java.util.ArrayList;

public class Score {
    private static Integer score;
    private static Integer highScore;
    private static String playerName = "Sven";


    public Score(Integer highScore, String playerName) {
        Score.highScore = highScore;
        Score.playerName = playerName;
    }

    public static void setScore(float birdPosition) {
        score = (int)birdPosition / 10;
    }

    public static void setHighScore() {
        if(highScore == null) {
            highScore = 0;
        }
        if (score > highScore) {
            highScore = score;

        }



    }

   public static void checkIfAllTimeHigh() {
        AllTimeHighHandler handler = new AllTimeHighHandler();
        handler.saveHighScoresToFile(score,playerName);
        System.out.println(playerName + score);
    }



    public static String getScore () {
        return score.toString();
    }
    public static String getHighScore () {
        return highScore.toString();
    }

    public String getPlayerName() {
        return playerName;
    }
    public Integer getIntegerHighScore() {
        return highScore;
    }


}
