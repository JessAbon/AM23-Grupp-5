package se.yrgo.util;

import se.yrgo.JumpyBirb;


public class Util {
    private static float globalHeroPositionX;


    public static void setGlobalHeroPositionX(float birdPositionX) {

        globalHeroPositionX = birdPositionX;
    }

    public static void initiateGlobalPositionZeroX() {
        globalHeroPositionX = JumpyBirb.WIDTH / 4F;

    }
    public static float getGlobalPositionZeroX() {

        return globalHeroPositionX - (JumpyBirb.WIDTH / 4F);
    }
    public static float getGlobalHeroPositionX() {
        return globalHeroPositionX;
    }
}
