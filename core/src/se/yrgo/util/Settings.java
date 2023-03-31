package se.yrgo.util;

public final class Settings {
    public static int GRAVITY = -15;
    public static int HERO_JUMP_VELOCITY = 280;
    public static int HERO_FORWARD_MOVEMENT = 200;
    public static int TUBE_SPACING = 200;
    public static int TUBE_FLUCTUATION = 250;
    public static int TUBE_GAP = 250;
    public static int TUBE_LOWEST_OPENING = 120;

    public static void easy() {
        GRAVITY = -15;
        HERO_JUMP_VELOCITY = 280;
        HERO_FORWARD_MOVEMENT = 200;
        TUBE_SPACING = 200;
        TUBE_FLUCTUATION = 250;
        TUBE_GAP = 250;
        TUBE_LOWEST_OPENING = 120;
    }

    public static void medium() {
        GRAVITY = -15;
        HERO_JUMP_VELOCITY = 280;
        HERO_FORWARD_MOVEMENT = 200;
        TUBE_SPACING = 180;
        TUBE_FLUCTUATION = 250;
        TUBE_GAP = 230;
        TUBE_LOWEST_OPENING = 130;

    }

    public static void hard() {
        GRAVITY = -15;
        HERO_JUMP_VELOCITY = 280;
        HERO_FORWARD_MOVEMENT = 400;
        TUBE_SPACING = 150;
        TUBE_FLUCTUATION = 250;
        TUBE_GAP = 220;
        TUBE_LOWEST_OPENING = 130;

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
