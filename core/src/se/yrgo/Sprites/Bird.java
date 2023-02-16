package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x,y, bird.getWidth(), bird.getHeight());
    }

    public void update(float delta) {
        velocity.add(0, GRAVITY, 0);
        velocity.scl(delta);
        position.add(velocity.x ,velocity.y, 0);
        bounds.x = position.x;
        bounds.y = position.y;

        velocity.scl(1/delta);
    }


    public Vector3 getPosition() {
        return position;
    }

    public Texture getBird() {
        return bird;
    }

    public void jump() {
        velocity.y = 300;
    }


    public Rectangle getBounds() {
        return bounds;
    }

}