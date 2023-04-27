package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.*;
import se.yrgo.util.Score;
import se.yrgo.util.Settings;
import se.yrgo.util.Util;

// TODO: 2023-04-14 fix dispose method


public class GameScreen implements Screen {

    private static final int TUBE_COUNT = 7;
    private static final int CAMERA_OF_SET = JumpyBirb.WIDTH / 4;

    private static Texture bg;
    final JumpyBirb game;
    private Hero hero;
    private OrthographicCamera camera;
    private Array<Tube> tubes;
    private Array<Ground> grounds;
    private Array<MidGround> midGrounds;
    private Array<ForGround> forGrounds;
    private GlyphLayout glyphLayout;
    private ScalingViewport viewport;

    private Scaling scaling;


    public GameScreen(JumpyBirb game) {
        this.game = game;

        hero = new Hero(JumpyBirb.WIDTH / 4, JumpyBirb.HEIGHT / 2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        viewport = new ScalingViewport((scaling) = scaling.fit, 800, 600,camera);
        glyphLayout = new GlyphLayout();
        tubes = new Array<>();
        bg = new Texture(Gdx.files.internal(Settings.getFolder() + "bg.png"));
        for (int i = 2; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (float) (Settings.getTubeSpacing() + Tube.TUBE_WIDTH)));
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

        ScreenUtils.clear(0, 0, 0, 0);

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
        glyphLayout.setText(game.font, Score.getScoreString());

        game.batch.begin();
        game.batch.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        for (MidGround midGround : midGrounds) {
            game.batch.draw(midGround.getGround(), midGround.getPosition().x, midGround.getPosition().y - 20);
        }
        for (Tube tube : tubes) {
            game.batch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            game.batch.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        for (Ground ground : grounds) {
            game.batch.draw(ground.getGround(), ground.getPosition().x, ground.getPosition().y);
        }

        game.batch.draw(hero.getHeroAnimation(), hero.getPosition().x, hero.getPosition().y);

        for (ForGround forGround : forGrounds) {
            game.batch.draw(forGround.getGround(), forGround.getPosition().x, forGround.getPosition().y + 40);
        }
        game.font.draw(game.batch, glyphLayout, camera.position.x + (JumpyBirb.WIDTH / 2F - glyphLayout.width), JumpyBirb.HEIGHT);

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
            if (hero.getBounds().overlaps(ground.getBounds())) {

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
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + Settings.getTubeSpacing()) * TUBE_COUNT));
            }
            if (tube.collides(hero.getBounds())) {

                hero.hit();
            }
        }
    }

    public static int getTubeSpacing() {
        return Settings.getTubeSpacing() + Tube.TUBE_WIDTH;
    }


    private void death() {
        if (hero.getPosition().y <= -hero.getHero().getHeight() * 2) {

            game.setScreen(new EndScreen(game));
            Score.setHighScore();
            dispose();
        }
    }

    @Override
    public void dispose() {
        bg.dispose();

        /*Settings.BACKGROUND.dispose();
        Settings.GROUND.dispose();
        Settings.MIDGROUND.dispose();*/

        //for (Ground ground : grounds) {
        //    ground.dispose();
        //}
        //for (Tube tube : tubes) {
        //    tube.dispose();
        //}
        //for (MidGround ground : midGrounds) {
        //    ground.dispose();
        //}
        //for (ForGround ground : forGrounds) {
        //    ground.dispose();
        //}
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
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
