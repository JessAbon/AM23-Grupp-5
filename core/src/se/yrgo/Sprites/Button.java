package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.yrgo.JumpyBirb;
import se.yrgo.util.Util;

import java.util.Random;

public class Button {

    //private Texture buttonTexture;
    private Vector2 positionButton;
    private Rectangle boundsButton;

    public Button (float x, float y, float width, float height){
        positionButton = new Vector2(x, y);
        boundsButton = new Rectangle(positionButton.x, positionButton.y + JumpyBirb.HEIGHT - 100, width, height);
    }

    public Vector2 getPositionButton() {
        return positionButton;
    }
    public Rectangle getBoundsButton() {
        return boundsButton;
    }
}
