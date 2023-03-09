package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.Sprites.Hero;
import se.yrgo.Sprites.Ground;
import se.yrgo.JumpyBirb;
import se.yrgo.Sprites.Tube;
import se.yrgo.util.Score;
import se.yrgo.util.Settings;


public class GameScreen implements Screen {

    //private static final int TUBE_SPACING = 200;
    private static final int TUBE_COUNT = 7;
    private static final int CAMERA_OF_SET = JumpyBirb.WIDTH / 4;

    final JumpyBirb game;
    private Hero hero;
    private OrthographicCamera camera;
    private static Texture bg;
    private Array<Tube> tubes;
    private Array<Ground> grounds;

    public GameScreen(JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        hero = new Hero(JumpyBirb.WIDTH / 4, JumpyBirb.HEIGHT / 2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        bg = new Texture(Gdx.files.internal("bg.png"));
        tubes = new Array<>();
        for (int i = 2; i <= TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (Settings.TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
        grounds = new Array<>();
        for (int i = 0; i <= 2; i++) {
            grounds.add(new Ground(i * JumpyBirb.WIDTH, 0));
        }

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 2, 3, 1);
        hero.update(delta);
        camera.update();

        Score.setScore(hero.getPosition().x);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);


        for (Tube tubes : tubes) {
            game.batch.draw(tubes.getTopTube(), tubes.getPosTopTube().x, tubes.getPosTopTube().y);
            game.batch.draw(tubes.getBottomTube(), tubes.getPosBottomTube().x, tubes.getPosBottomTube().y);
        }
        for (Ground ground : grounds) {
            game.batch.draw(ground.getGround(), ground.getPosition().x, ground.getPosition().y);

        }

        game.batch.draw(hero.getHero(), hero.getPosition().x, hero.getPosition().y);

        game.font.draw(game.batch, Score.printScore(), camera.position.x - (JumpyBirb.WIDTH / 2F),
                camera.position.y + (JumpyBirb.HEIGHT / 2F));

        game.batch.end();

        // CAMERA FOLLOW
        camera.position.x = hero.getPosition().x + CAMERA_OF_SET;
        camera.update();

        movementTubes();
        movementGround();

        handleInput();

        roofBound();
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

            //ground.moveGround(); Parallax movement (call in equivalent place for foreground and background.

            if (camera.position.x - (camera.viewportWidth / 2) >= ground.getPosition().x + ground.getGround().getWidth()) {
                ground.reposition(ground.getPosition().x + ground.getGround().getWidth() * 2);
            }
            if (hero.getBounds().overlaps(ground.getBounds()) || hero.getBounds().overlaps(ground.getBounds())) {
                death();
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
                death();
            }

        }
    }

    public static int getTubeSpacing() {
        return Settings.TUBE_SPACING + Tube.TUBE_WIDTH;
    }

    private void death() {
        game.setScreen(new EndScreen(game));
        Score.setHighScore();
        dispose();
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
