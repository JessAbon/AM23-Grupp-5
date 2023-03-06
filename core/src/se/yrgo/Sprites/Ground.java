package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import se.yrgo.JumpyBirb;

public class Ground {

    private Vector2 position;
    private Texture ground;
    private Rectangle bounds;

    public Ground (int x, int y) {
        position = new Vector2(x, y);
        ground = new Texture("ground.png");
        bounds = new Rectangle(x, y, JumpyBirb.WIDTH, ground.getHeight()); //ground.getWidth() sen
    }

    public void update(float delta) {
        position.add(0, 0);
    }

    public void reposition(float x) {
        position.set(x, 0);
    }

    public Vector2 getPosition() { return position; }

    public Texture getGround() { return ground; }

    public Rectangle getBounds() { return bounds; }


}
