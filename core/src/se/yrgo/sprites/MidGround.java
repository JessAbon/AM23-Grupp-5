package se.yrgo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MidGround {

    private Vector2 position;
    private Texture ground;

    public MidGround(int x, int y) {
        position = new Vector2(x, y);
        ground = new Texture("mg.png");
    }

    public void reposition(float x) {
        position.set(x, 40);
    }

    //Parallax movement prototype
    public void moveGroundParallax() {
        position.add(2, 0);
    }

    public Vector2 getPosition() { return position; }

    public Texture getGround() { return ground; }

    public void dispose() {
        ground.dispose();
    }


}
