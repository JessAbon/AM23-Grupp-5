package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import se.yrgo.util.Settings;

public class Hero {
    //private static final int GRAVITY = -15;
    //private static final int FORWARD_MOVEMENT = 100;
    //private static final int JUMP_VELOCITY = 220;
    private Vector3 position;
    private Vector3 velocity;
    private Texture hero;
    private Rectangle bounds;
    private boolean hit;
    private boolean hasDeathJumped;
    private Animation heroAnimation;


    public Hero(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        //hero = new Texture("hero.png"); //hero - stilla
        hero = new Texture("heroAnimation.png");
        heroAnimation = new Animation(new TextureRegion(hero), 6,0.5f);
        //bounds = new Rectangle(x, y, hero.getWidth(), hero.getHeight()); //gamla bounds
        bounds = new Rectangle(x,y,  hero.getWidth()/6, hero.getHeight());
        hasDeathJumped = false;
    }


    public void update(float delta) {
        heroAnimation.update(delta);
        velocity.add(0, Settings.GRAVITY, 0);
        velocity.scl(delta);
        position.add(velocity.x, velocity.y, 0);
        bounds.x = position.x;
        bounds.y = position.y;
        //position.add(Settings.HERO_FORWARD_MOVEMENT * delta, velocity.y, 0); //Framåtrörelse
        forwardMovement(delta);
        velocity.scl(1 / delta);
    }

    private void forwardMovement(float delta) {
        if (hit) {
            position.add(0, velocity.y,0);
            if (!hasDeathJumped) {
                velocity.y = 200 * delta;
                hasDeathJumped = true;
            }
        }
        else position.add(Settings.HERO_FORWARD_MOVEMENT * delta, velocity.y, 0);
    }

    public void removeVelocity() {
        velocity.y = 0;
    }

    public void jump() {
        if (!hasDeathJumped) {
            velocity.y = Settings.HERO_JUMP_VELOCITY;
        }
    }
    public void hit() {
        hit = true;
    }

    public void setPositionY(float positionY) {
        position.y = positionY;
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getHero() {
        return hero;
    }
    public TextureRegion getTexture() {
        return heroAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        hero.dispose();
    }


}
