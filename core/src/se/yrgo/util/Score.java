package se.yrgo.util;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Score {
    private static Integer score;
    private static Integer highScore;
    //private static File top10File;
    private TreeMap<Integer,String> allTimeHighScores = new TreeMap<>(Collections.<Integer>reverseOrder());

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
    public static String getScore() { return score.toString(); }
    public static String getHighScore() {
        return highScore.toString();
    }



  /*  3 metoder:
    - registrera nytt highscore på alltimehighscore listan
    - skriva in nya highscoret i filen
    - printa ut all time high scores*/

    public void registerAllTimeHighScore(String playername, Integer highScore) {
        Integer top1Score = allTimeHighScores.firstKey();
        if (highScore > top1Score) {
            allTimeHighScores.put(highScore, playername);
            saveAllTimeHighScoresToFile();
        }
    }

    public void saveAllTimeHighScoresToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("top10.txt"))) {
            for (Map.Entry<Integer, String> entry : allTimeHighScores.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void printAllTimeHighScore() {
        try (BufferedReader br = new BufferedReader(new FileReader("top10.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
