package se.yrgo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button {

    private Vector2 positionButton;
    private Rectangle boundsButton;
    private Texture texture;
    private Texture textureChecked;
    private boolean isPressed;

    public Button (float x, float y, String filename){
        texture = new Texture("menu/" + filename);
        textureChecked = new Texture("menu/button_pressed.png");
        positionButton = new Vector2(x, y);
        boundsButton = new Rectangle(positionButton.x, positionButton.y, texture.getWidth(), texture.getHeight());
        isPressed = false;
    }
    public Button (float x, float y, float width, float height){
        positionButton = new Vector2(x, y);
        boundsButton = new Rectangle(positionButton.x, positionButton.y, width, height);
        isPressed = false;
    }

    public Vector2 getPositionButton() {
        return positionButton;
    }
    public Rectangle getBoundsButton() { return boundsButton; }
    public Texture getTexture() { return texture; }
    public Texture getTextureChecked() { return textureChecked; }
    public void isPressed(boolean bool) {
        this.isPressed = bool;
    }
    public boolean isPressed() {
        return isPressed;
    }

    public void dispose() {
    }

}
