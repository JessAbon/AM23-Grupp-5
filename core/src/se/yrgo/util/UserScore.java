package se.yrgo.util;

public class UserScore implements Comparable<UserScore> {
    private int score;
    private String name;

    public UserScore(int score, String name) {
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
    public int compareTo(UserScore o) {
        return o.getScore() - this.score;
    }
}


