package se.yrgo.util;

import com.badlogic.gdx.Screen;

public class Misc {
    public static Screen previousScreen;

    public static void setPreviousScreen(Screen currentScreen) {
        previousScreen = currentScreen;
    }
}
