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
import java.util.Collections;

public class AllTimeHighHandler {
    private static final String FILEPATH = "../assets/top10.txt";
    private static final File file = new File(FILEPATH);
    public static ArrayList<MyScore> scoreArray = new ArrayList<>();


    public static void readFile() {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String playerName = parts[0];
                int score = Integer.parseInt(parts[1]);
                MyScore scoreObj = new MyScore(100, "Test");
                scoreArray.add(scoreObj);
            }
        } catch (IOException e) {
            System.out.println("So much fuckin error" + e.getMessage());
        }
    }


    //CHECK IF SCORE > LOWEST COLLECTION MEMBER
    public static void addScore(MyScore newScore) {
        Collections.sort(scoreArray);

        for (MyScore s : scoreArray) {
            if (newScore.getScore() > s.getScore()) {
                scoreArray.add(newScore);
            }
        }
    }


    public void writeFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (MyScore s : scoreArray) {
                bw.append(s.getName() + ":" + s.getScore());
                bw.newLine();
                System.out.println(s.getName() + ":" + s.getScore());

            }
        } catch (IOException e) {
            System.out.println("Failed to save high scores to file: " + e.getMessage());
        }
    }
}

