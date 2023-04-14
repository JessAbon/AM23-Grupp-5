package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.yrgo.screens.GameScreen;
import se.yrgo.util.Settings;

import java.util.Random;

public class Tube {
    public static final int TUBE_WIDTH = 52;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBottomTube; //Position för tubes
    private Random rand; //används för att sätta tubes på random position
    private Rectangle boundsTop, boundsBottom; //Prepare for collision


    public Tube(float x){
    topTube = new Texture(Settings.getFolder() + "toptube.png");
    bottomTube = new Texture(Settings.getFolder() + "bottomtube.png");
    rand = new Random();
    posTopTube = new Vector2(x, rand.nextInt(Settings.getTubeFluctuation()) + Settings.getTubeGap() + Settings.getTubeLowestOpening());
    posBottomTube = new Vector2(x,posTopTube.y - Settings.getTubeGap() - bottomTube.getHeight());
    boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
    boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth() , bottomTube.getHeight());
    }

    public void reposition (float x){
        posTopTube.set(x - GameScreen.getTubeSpacing(), rand.nextInt(Settings.getTubeFluctuation()) + Settings.getTubeGap() + Settings.getTubeLowestOpening());
        posBottomTube.set(x - GameScreen.getTubeSpacing() , posTopTube.y - Settings.getTubeGap() - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBottomTube() {
        return posBottomTube;
    }
    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }


}
