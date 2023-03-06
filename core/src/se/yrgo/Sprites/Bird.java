package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import se.yrgo.JumpyBirb;

public class Bird {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private int jumpVelocity;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        bounds = new Rectangle(x,y, bird.getWidth(), bird.getHeight());
        jumpVelocity = 220;
    }

    public void update(float delta) {
        velocity.add(0, GRAVITY, 0);
        velocity.scl(delta);
        position.add(velocity.x ,velocity.y, 0);
        bounds.x = position.x;
        bounds.y = position.y;
        //Framåt
        position.add(MOVEMENT * delta,velocity.y, 0);

        velocity.scl(1/delta);
    }

    public void removeVelocity() {
        velocity.y = 0;
    }
    public void jump() {
        velocity.y = jumpVelocity;
    }
    public void setPositionY(float positionY) {
        position.y = positionY;
    }

    public void stillY() {
        velocity.set(0,0,0);
    }

    public Vector3 getPosition() {
        return position; }

    public Texture getBird() {
        return bird; }

    public Rectangle getBounds() {
        return bounds; }

    public void dispose() {
        bird.dispose();
    }



}
