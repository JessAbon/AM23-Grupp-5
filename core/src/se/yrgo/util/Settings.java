package se.yrgo.util;

public class Settings {
    private static int gravity = -15;
    private static int heroJumpVelocity = 280;
    private static int heroForwardMovement = 200;
    private static int tubeSpacing = 200;
    private static int tubeFluctuation = 250;
    private static int tubeGap = 250;
    private static int tubeLowestOpening = 120;
    private static String folder = "medium/";



    public static void easy() {
        folder = "easy/";

        tubeSpacing = 200;
        tubeFluctuation = 250;
        tubeGap = 190;
        tubeLowestOpening = 150;

    }

    public static void medium() {
        folder = "medium/";

        tubeSpacing = 180;
        tubeFluctuation = 250;
        tubeGap = 230;
        tubeLowestOpening = 130;

    }

    public static void hard() {
        folder = "hard/";

        heroForwardMovement = 200;
        tubeSpacing = 170;
        tubeFluctuation = 250;
        tubeGap = 220;
        tubeLowestOpening = 150;
    }



    public static String getFolder() {
        return folder;
    }

    public static int getGravity() {
        return gravity;
    }

    public static int getHeroJumpVelocity() {
        return heroJumpVelocity;
    }

    public static int getHeroForwardMovement() {
        return heroForwardMovement;
    }

    public static int getTubeSpacing() {
        return tubeSpacing;
    }

    public static int getTubeFluctuation() {
        return tubeFluctuation;
    }

    public static int getTubeGap() {
        return tubeGap;
    }

    public static int getTubeLowestOpening() {
        return tubeLowestOpening;
    }

    public enum Difficulty {
        EASY("Easy"),
        MEDIUM("Medium"),
        HARD("Hard");

        private final String name;

        Difficulty(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }


    }


}
