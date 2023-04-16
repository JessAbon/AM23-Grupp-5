package se.yrgo.util;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class AllTimeHighHandler {
    private static final String FILEPATH = "../assets/top10.txt";
    private static final File file = new File(FILEPATH);

    public ArrayList<Score> allTimeHighList = new ArrayList<>();

    // Metod för att läsa in från fil till lista, returnerar sen listan:
    public static ArrayList<Score> loadHighscores() {
        ArrayList<Score> allTimeHighList = new ArrayList<>();
        if (file.length() == 0) {
            return allTimeHighList;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String playerName = parts[0];
                int score = Integer.parseInt(parts[1]);
                Score scoreObj = new Score(score, playerName);
                allTimeHighList.add(scoreObj);
            }
        } catch (IOException e) {
            System.out.println("So much fuckin error" + e.getMessage());
        }
        return allTimeHighList;
    }

    //Metod för att sortera lista efter score.
    private static void sortByValue(ArrayList<Score> allTimeHighList) {

    }

    //Metod för att lägga till senaste high score i listan, om fler än 10 - tar bort sämsta score
    public void rearrangeAllTimeHigh(String playerName, int highScore) {

        ArrayList<Score> allTimeHighList = loadHighscores(); //Metoden för att läsa från fil
        allTimeHighList.add(new Score(highScore, playerName));

        sortByValue(allTimeHighList);   //Metoden för att sortera efter score
        if (allTimeHighList.size() > 10) {
            allTimeHighList.remove(allTimeHighList.size() - 1);
        }
        //updateHighscoreFile(allTimeHighList);
    }


    public void saveHighScoresToFile(Integer highScore, String playerName) {
        allTimeHighList.add(new Score(highScore, playerName));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (Score score : allTimeHighList) {
                bw.append(score.getPlayerName() + ":" + score.getIntegerHighScore());
                bw.newLine();
                System.out.println(score.getPlayerName() + ":" + score.getIntegerHighScore());

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

