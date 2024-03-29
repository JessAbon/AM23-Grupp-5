package se.yrgo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.yrgo.util.Settings;

public class Button {
    private String id;
    private Vector2 positionButton;
    private Rectangle boundsButton;
    private Texture texture;
    private Texture textureChecked;
    private boolean isPressed;

    public Button (String id, float x, float y, String filename){
        this.id = id;
        this.texture = new Texture("menu/" + filename);
        this.textureChecked = new Texture("menu/medium_pressed_v2.png");
        positionButton = new Vector2(x, y);
        boundsButton = new Rectangle(positionButton.x, positionButton.y, texture.getWidth(), texture.getHeight());
        this.isPressed = false;
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
    public void checkStatus() {
        if (Settings.getFolder().equals(this.id)) {
            this.isPressed = true;
        }
        else {
            this.isPressed = false;
        }
    }
    public void dispose() {
        texture.dispose();
        textureChecked.dispose();
    }

}
