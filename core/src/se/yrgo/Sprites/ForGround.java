package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.yrgo.util.Settings;

public class ForGround {

    private Vector2 position;
    private Texture ground;
    private Rectangle bounds;

    public ForGround(int x, int y) {
        position = new Vector2(x, y);
        ground = Settings.FOREGROUND;

        bounds = new Rectangle(position.x, position.y, ground.getWidth(), ground.getHeight());
    }

    public void reposition(float x) {
        position.set(x, -40);
    }

    //Parallax movement prototype
    public void moveGroundParallax() {
        position.add(-1, 0);
    }

    public Vector2 getPosition() { return position; }

    public Texture getGround() { return ground; }

    public Rectangle getBounds() { return bounds; }

    public void dispose() {
        ground.dispose();
    }


}
