package se.yrgo.util;

public class Settings {
    private static int gravity = -15;
    private static int heroJumpVelocity = 280;
    private static int heroForwardMovement = 200;
    private static int tubeSpacing = 250;
    private static int tubeFluctuation = 250;
    private static int tubeGap = 180;
    private static int tubeLowestOpening = 120;
    private static String folder = "medium/";



    public static void easy() {
        folder = "easy/";

        tubeSpacing = 250;
        tubeFluctuation = 250;
        tubeGap = 200;
        tubeLowestOpening = 150;

    }

    public static void medium() {
        folder = "medium/";

        tubeSpacing = 250;
        tubeFluctuation = 250;
        tubeGap = 180;
        tubeLowestOpening = 130;

    }

    public static void hard() {
        folder = "hard/";

        heroForwardMovement = 240;
        tubeSpacing = 240;
        tubeFluctuation = 250;
        tubeGap = 150;
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



}
