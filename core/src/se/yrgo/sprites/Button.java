package se.yrgo.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {

    private Vector2 positionButton;
    private Rectangle boundsButton;

    public Button (float x, float y, float width, float height){
        positionButton = new Vector2(x, y);
        boundsButton = new Rectangle(positionButton.x, positionButton.y, width, height);
    }

    public Vector2 getPositionButton() {
        return positionButton;
    }
    public Rectangle getBoundsButton() {
        return boundsButton;
    }

    public void dispose() {
    }

}
