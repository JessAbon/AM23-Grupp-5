package se.yrgo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Bird {
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
    }


    public void update(float delta) {
        velocity.add(0, GRAVITY, 0);
        velocity.scl(delta);
        position.add(velocity.x ,velocity.y, 0);

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

    public void forward() {
        velocity.x += 50;
    }

    public void back() {
        velocity.x -= 50;
    }

    public void still() {
        if (velocity.x > 0) {
            velocity.x -= (velocity.x/10);
        }
        if (velocity.x < 0) {
            velocity.x -= (velocity.x/10);
        }
    }



}
