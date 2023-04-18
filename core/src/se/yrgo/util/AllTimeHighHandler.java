/*
1 READ FILE
2 ADD TO COLLECTION
3 SORT COLLECTION
4 CHECK NEW SCORE AGAINST LOWEST SCORE IN COLLECTION
5 IF NEW SCORE > LOWEST IN COLLECTION
6 PUSH NEW SCORE POP LOWEST COLLECTION
7 SORT COLLECTION
8 OVERWRITE FILE
*/



package se.yrgo.util;

import java.io.*;
import java.util.ArrayList;

public class AllTimeHighHandler {
    private static final String FILEPATH = "../assets/top10.txt";
    private static final File file = new File(FILEPATH);
    public static ArrayList<Score> scoreArray = new ArrayList<>();


    public static void readFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String playerName = parts[0];
                int score = Integer.parseInt(parts[1]);
                Score scoreObj = new Score(score, playerName);
                scoreArray.add(scoreObj);
            }
        } catch (IOException e) {
            System.out.println("So much fuckin error" + e.getMessage());
        }
    }

    public static void sort() {
        //SORT COLLECTION
    }

    //CHECK IF SCORE > LOWEST COLLECTION MEMBER
    public static void addScore(Score newScore) {
        for (Score s : scoreArray) {
            if (newScore.getScore() > s.getScore()) {
                scoreArray.add(newScore);
            }
        }
    }


    public void saveHighScoresToFile(Integer highScore, String playerName) {
        allTimeHighList.add(new Score(highScore, playerName));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (Score score : allTimeHighList) {
                bw.append(score.getPlayerName() + ":" + score.getScore());
                bw.newLine();
                System.out.println(score.getPlayerName() + ":" + score.getScore());

            }
        } catch (IOException e) {
            System.out.println("Failed to save high scores to file: " + e.getMessage());
        }
        rearrangeAllTimeHigh(playerName, highScore);


    }
    //Skriver alla likadant
 /*   public void updateHighscoreFile(ArrayList<Score> allTimeHighList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Score score : allTimeHighList) {
                bw.append(score.getPlayerName() + ":" + score.getIntegerHighScore());
                bw.newLine();
                System.out.println(score.getPlayerName() + ":" + score.getIntegerHighScore());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

}

