package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.JumpyBirb;
import se.yrgo.Sprites.*;
import se.yrgo.util.Score;
import se.yrgo.util.Settings;
import se.yrgo.util.Util;


public class GameScreen implements Screen {

    private static final int TUBE_COUNT = 7;
    private static final int CAMERA_OF_SET = JumpyBirb.WIDTH / 4;

    final JumpyBirb game;
    private Hero hero;
    private OrthographicCamera camera;
    private static Texture bg;
    private Array<Tube> tubes;
    private Array<Ground> grounds;
    private Array<MidGround> midGrounds;
    private Array<ForGround> forGrounds;
    private GlyphLayout glyphLayout;

    public GameScreen(JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        hero = new Hero(JumpyBirb.WIDTH / 4, JumpyBirb.HEIGHT / 2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        bg = new Texture(Gdx.files.internal("bg.png"));
        glyphLayout = new GlyphLayout();
        tubes = new Array<>();
        for (int i = 2; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (Settings.TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        midGrounds = new Array<>();
        for (int i = 0; i <= 2; i++) {
            midGrounds.add(new MidGround(i * 2400, 40));
        }
        grounds = new Array<>();
        for (int i = 0; i <= 2; i++) {
            grounds.add(new Ground(i * JumpyBirb.WIDTH, 0));
        }
        forGrounds = new Array<>();
        for (int i = 0; i <= 2; i++) {
            forGrounds.add(new ForGround(i * 2400, -40));
        }
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 2, 3, 1);

        hero.update(delta);
        camera.position.x = hero.getPosition().x + CAMERA_OF_SET; // CAMERA FOLLOW
        camera.update();

        Score.setScore(hero.getPosition().x);
        Util.setGlobalHeroPositionX(hero.getPosition().x);
        if (!hero.getHit()) {
            movementMidGround();
            movementForGround();
        }
        movementTubes();
        handleInput();
        movementGround();
        roofBound();

        game.batch.setProjectionMatrix(camera.combined);
        glyphLayout.setText(game.font, Score.getScore());

        game.batch.begin();
        game.batch.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        for (MidGround midGround : midGrounds) {
            game.batch.draw(midGround.getGround(), midGround.getPosition().x, midGround.getPosition().y);
        }
        for (Tube tubes : tubes) {
            game.batch.draw(tubes.getTopTube(), tubes.getPosTopTube().x, tubes.getPosTopTube().y);
            game.batch.draw(tubes.getBottomTube(), tubes.getPosBottomTube().x, tubes.getPosBottomTube().y);
        }
        for (Ground ground : grounds) {
            game.batch.draw(ground.getGround(), ground.getPosition().x, ground.getPosition().y);
        }

        game.batch.draw(hero.getHeroAnimation(), hero.getPosition().x, hero.getPosition().y);

        for (ForGround forGround : forGrounds) {
            game.batch.draw(forGround.getGround(), forGround.getPosition().x, forGround.getPosition().y);
        }
        game.font.draw(game.batch, glyphLayout, camera.position.x + (JumpyBirb.WIDTH/2F - glyphLayout.width), JumpyBirb.HEIGHT);

        game.batch.end();

        death();
    }

    private void roofBound() {
        float gameHeightToFloat = (float) JumpyBirb.HEIGHT - hero.getHero().getHeight();

        if (hero.getPosition().y >= gameHeightToFloat) {
            hero.removeVelocity();
            hero.setPositionY(gameHeightToFloat);
        }
    }

    private void handleInput() {
        if (game.spaceAndMouseClickInput()) {
            hero.jump();
        }
    }
    private void movementGround() {
        for (int i = 0; i < grounds.size; i++) {
            Ground ground = grounds.get(i);

            if (camera.position.x - (camera.viewportWidth / 2) >= ground.getPosition().x + ground.getGround().getWidth()) {
                ground.reposition(ground.getPosition().x + ground.getGround().getWidth() * 2);
            }
            if (hero.getBounds().overlaps(ground.getBounds()) || hero.getBounds().overlaps(ground.getBounds())) {
                hero.hit();
            }
        }
    }
    private void movementMidGround() {
        for (int i = 0; i < midGrounds.size; i++) {
            MidGround midGround = midGrounds.get(i);
            midGround.moveGroundParallax();

            if (camera.position.x - (camera.viewportWidth / 2) >= midGround.getPosition().x + midGround.getGround().getWidth()) {
                midGround.reposition(midGround.getPosition().x + midGround.getGround().getWidth() * 2);
            }
        }
    }
    private void movementForGround() {
        for (int i = 0; i < forGrounds.size; i++) {
            ForGround forGround = forGrounds.get(i);
            forGround.moveGroundParallax();

            if (camera.position.x - (camera.viewportWidth / 2) >= forGround.getPosition().x + forGround.getGround().getWidth()) {
                forGround.reposition(forGround.getPosition().x + forGround.getGround().getWidth() * 2);
            }
        }
    }


    private void movementTubes() {
        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + Settings.TUBE_SPACING) * TUBE_COUNT));
            }
            if (tube.collides(hero.getBounds())) {
                hero.hit();
            }
        }
    }

    public static int getTubeSpacing() {
        return Settings.TUBE_SPACING + Tube.TUBE_WIDTH;
    }


    private void death() {
        if (hero.getPosition().y <= -hero.getHero().getHeight() * 2) {
            game.setScreen(new EndScreen(game));
            Score.setHighScore();
            Score.checkIfAllTimeHigh();
            dispose();
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        hero.dispose();
        for (Ground ground : grounds) {
            ground.dispose();
        }
        for (Tube tube : tubes) {
            tube.dispose();
        }
        for (MidGround ground : midGrounds) {
            ground.dispose();
        }
        for (ForGround ground : forGrounds) {
            ground.dispose();
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }
}
