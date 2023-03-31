package se.yrgo.util;

import com.badlogic.gdx.graphics.Texture;

public final class Settings {
    public static int GRAVITY = -15;
    public static int HERO_JUMP_VELOCITY = 280;
    public static int HERO_FORWARD_MOVEMENT = 200;
    public static int TUBE_SPACING = 200;
    public static int TUBE_FLUCTUATION = 250;
    public static int TUBE_GAP = 250;
    public static int TUBE_LOWEST_OPENING = 120;

    public static Texture BACKGROUND = new Texture("bg.png");
    public static Texture FOREGROUND = new Texture("fg.png");
    public static Texture TOPTUBE = new Texture("toptube.png");
    public static Texture BOTTOMTUBE = new Texture("bottomtube.png");
    public static Texture GROUND = new Texture("ground.png");
    public static Texture MIDGROUND = new Texture("mg.png");

    public static void easy() {
        GRAVITY = -15;
        HERO_JUMP_VELOCITY = 280;
        HERO_FORWARD_MOVEMENT = 200;
        TUBE_SPACING = 200;
        TUBE_FLUCTUATION = 250;
        TUBE_GAP = 230;
        TUBE_LOWEST_OPENING = 130;
        BACKGROUND = new Texture("easy/bg.png");
        FOREGROUND = new Texture("easy/fg.png");
        TOPTUBE = new Texture("easy/Top-smallcarrot.png");
        BOTTOMTUBE = new Texture("easy/Bottom-smallcarrot.png");
        GROUND = new Texture("ground.png");
        MIDGROUND = new Texture("easy/mg.png");
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
        HERO_FORWARD_MOVEMENT = 250;
        TUBE_SPACING = 170;
        TUBE_FLUCTUATION = 250;
        TUBE_GAP = 220;
        TUBE_LOWEST_OPENING = 130;
        BACKGROUND = new Texture("hard/bg.png");
        FOREGROUND = new Texture("hard/fg.png");
        TOPTUBE = new Texture("hard/toptube.png");
        BOTTOMTUBE = new Texture("hard/bottomtube.png");
        GROUND = new Texture("hard/ground.png");
        MIDGROUND = new Texture("hard/mg.png");

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
