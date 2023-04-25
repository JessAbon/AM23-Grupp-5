package se.yrgo.util;

import java.util.Comparator;
import java.util.Objects;

public class MyScore implements Comparable<MyScore> {
    private int score;
    private String name;

    public MyScore(int score, String name) {
        this.score = score;
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(MyScore o) {
        return o.getScore() - this.score;
    }
}


