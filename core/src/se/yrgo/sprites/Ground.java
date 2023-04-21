package se.yrgo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.yrgo.util.Settings;

public class Ground {

    private Vector2 position;
    private Texture ground;
    private Rectangle bounds;

    public Ground (int x, int y) {
        position = new Vector2(x, y);
        ground = new Texture(Settings.getFolder() + "ground.png");
        bounds = new Rectangle(position.x, position.y, ground.getWidth(), ground.getHeight());
    }

    public void reposition(float x) {
        position.set(x, 0);
        bounds.setPosition(position.x, position.y);
    }

    //Parallax movement prototype
    public void moveGround() {
        position.add(-5, 0);
    }

    public Vector2 getPosition() { return position; }

    public Texture getGround() { return ground; }

    public Rectangle getBounds() { return bounds; }

    public void dispose() {
        ground.dispose();
    }


}
