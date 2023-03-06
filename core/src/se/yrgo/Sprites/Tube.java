package se.yrgo.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import se.yrgo.screens.GameScreen;

import java.util.Random;

public class Tube {

    //Detta ska ändras när vi byter sprite anpassad efter storlek på bild
    public static final int TUBE_WIDTH = 52;

    private static final int FLUCTUATION = 250;
    //Avstånd mellan övre och undre hindret
    private static final int TUBE_GAP = 200;
    //Bestäm lägsta höjd för undre tube
    private static final int LOWEST_OPENING = 180;
    //sprite tubes
    private Texture topTube, bottomTube;
    //Position för tubes
    private Vector2 posTopTube, posBottomTube;
    //används för att sätta tubes på random position
    private Random rand;
    //Prepare for collision
    private Rectangle boundsTop, boundsBottom;

    public Tube(float x){
    topTube = new Texture("topTube.png");
    bottomTube = new Texture("bottomTube.png");
    rand = new Random();
    posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
    posBottomTube = new Vector2(x,posTopTube.y - TUBE_GAP - bottomTube.getHeight());
    //Positionera collisionbox på samma ställe som tubes
    boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
    boundsBottom = new Rectangle(posBottomTube.x, posBottomTube.y, bottomTube.getWidth() , bottomTube.getHeight());

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

    public void reposition (float x){
        posTopTube.set(x - GameScreen.getTubeSpacing(), rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBottomTube.set(x - GameScreen.getTubeSpacing() , posTopTube.y - TUBE_GAP - bottomTube.getHeight());
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
        boundsBottom.setPosition(posBottomTube.x, posBottomTube.y);
    }
    //collision mellan bird och tube
    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBottom);
    }
}
