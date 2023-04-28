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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;

public class AllTimeHighHandler {
    private static Path filePath = Path.of("../assets/score/top10.txt");
    private static ArrayList<MyScore> scoreArray = new ArrayList<>();
    public static boolean isHighScore;


    public static void readFile() {
        scoreArray.clear();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(":");
                int score = Integer.parseInt(part[0]);
                String name = part[1];
                MyScore scoreObj = new MyScore(score, name);
                scoreArray.add(scoreObj);
            }
        } catch (IOException e) {
            System.out.println("So much fuckin error" + e.getMessage());
        }
    }

    public static ArrayList<MyScore> getScoreArray() {
        return scoreArray;
    }

    public static void checkScore() {
        isHighScore = false;
        if (scoreArray.size() < 9) {
            isHighScore = true;
        }
        else {
            for (MyScore s : scoreArray) {
                if (Score.getScore() > s.getScore()) {
                    isHighScore = true;
                    break;
                }
            }
        }
    }


    //CHECK IF SCORE > LOWEST COLLECTION MEMBER
    public static void addScore(MyScore newScore) throws IOException {
        if (scoreArray.size() > 9) {
            for (MyScore s : scoreArray) {
                if (newScore.getScore() > s.getScore()) {
                    scoreArray.add(newScore);
                    break;
                }
            }
            Collections.sort(scoreArray);
            scoreArray.remove(scoreArray.size() - 1);
        } else {
            isHighScore = true;
            scoreArray.add(newScore);
        }
        writeFile();
    }


    public static void writeFile() throws IOException {
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }

        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
            Collections.sort(scoreArray);
            for (MyScore s : scoreArray) {
                bw.append(s.getScore() + ":" + s.getName());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save high scores to file: " + e.getMessage());
        }
    }
}

