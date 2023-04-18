package se.yrgo.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import se.yrgo.util.Settings;

public class Hero {
    private Vector3 position;
    private Vector3 velocity;
    private Texture hero;
    private Rectangle bounds;
    private boolean hit;
    private boolean hasDeathJumped;
    private Animation heroAnimation;

    private Sound flap;
    private Sound deathSound;
    private boolean soundPlayed;

    // TODO: 2023-04-14 fix death sound

    public Hero(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        hero = new Texture("hero/heroanimation.png");
        heroAnimation = new Animation(new TextureRegion(hero), 6, 0.5f);
        bounds = new Rectangle(x, y, hero.getWidth() / 6, hero.getHeight());
        hasDeathJumped = false;
        flap = Gdx.audio.newSound(Gdx.files.internal("sound/jump.mp3"));
        deathSound = Gdx.audio.newSound(Gdx.files.internal("sound/hit.ogg"));
        soundPlayed = false;

    }

    public void update(float delta) {
        heroAnimation.update(delta);
        velocity.add(0, Settings.getGravity(), 0);
        velocity.scl(delta);
        position.add(velocity.x, velocity.y, 0);
        bounds.x = position.x;
        bounds.y = position.y;
        forwardMovement(delta);
        velocity.scl(1 / delta);

        if(hit && !soundPlayed){
            deathSound.play(0.1f);
            soundPlayed = true;
        }

    }

    private void forwardMovement(float delta) {
        if (hit) {
            position.add(0, velocity.y, 0);
            if (!hasDeathJumped) {
                velocity.y = 200 * delta;
                hasDeathJumped = true;
            }
        } else position.add(Settings.getHeroForwardMovement() * delta, velocity.y, 0);

    }

    public void removeVelocity() {
        velocity.y = 0;
    }

    public void jump() {
        flap.play(0.1f);
        if (!hasDeathJumped) {
            velocity.y = Settings.getHeroJumpVelocity();
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

    public TextureRegion getHeroAnimation() {
        return heroAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean getHit() {
        return hit;
    }

    public void dispose() {
        hero.dispose();
        flap.dispose();

    }


}
