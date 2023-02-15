package se.yrgo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Ground {

    private Vector3 position;
    private Texture ground;
    private Rectangle bounds;

    public Ground (int x, int y) {
        position = new Vector3(x, y, 0);
        ground = new Texture("ground.png");
        bounds = new Rectangle(x, y, JumpyBirb.WIDTH, ground.getHeight());
    }

    public void update(float delta) {
        position.add(0, 0, 0);
    }

    public Vector3 getPosition() { return position; }

    public Texture getGround() { return ground; }

    public Rectangle getBounds() { return bounds; }


}
