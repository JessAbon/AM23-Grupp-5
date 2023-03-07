package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import se.yrgo.util.Settings;

public class Bird {
    //private static final int GRAVITY = -15;
    //private static final int FORWARD_MOVEMENT = 100;
    //private static final int JUMP_VELOCITY = 220;
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
        velocity.add(0, Settings.GRAVITY, 0);
        velocity.scl(delta);
        position.add(velocity.x ,velocity.y, 0);
        bounds.x = position.x;
        bounds.y = position.y;
        position.add(Settings.BIRD_FORWARD_MOVEMENT * delta,velocity.y, 0); //Framåtrörelse
        velocity.scl(1/delta);

    }

    public void removeVelocity() {
        velocity.y = 0;
    }
    public void jump() {
        velocity.y = Settings.BIRD_JUMP_VELOCITY;
    }
    public void setPositionY(float positionY) {
        position.y = positionY;
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
