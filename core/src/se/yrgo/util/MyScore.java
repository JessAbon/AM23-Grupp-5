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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyScore)) return false;
        MyScore myScore = (MyScore) o;
        return score == myScore.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(MyScore o) {
        return this.score - o.getScore();
    }
}


