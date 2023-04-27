package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.Button;

public class HighscoreScreen implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;

    private Texture background;
    private Texture title;

    private Texture homeTexture;
    private Texture quitTexture;

    private Button homeButton;

    private Button quitButton;

    private ScalingViewport viewport;
    private Scaling scaling;
    public HighscoreScreen(final JumpyBirb game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        viewport = new ScalingViewport((scaling) = scaling.fit, 800, 600,camera);
        background = new Texture("highscore/bg_highscore_v2.png");
        title = new Texture("highscore/highscoretitle.png");
        homeTexture = new Texture("menu/Home.png");
        quitTexture = new Texture("menu/Stop.png");

        homeButton = new Button(300, 10, homeTexture.getWidth(), homeTexture.getHeight());
        quitButton = new Button(400, 10, quitTexture.getWidth(), quitTexture.getHeight());
    }
    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0,0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(title, 250, 500, title.getWidth(), title.getHeight());

        game.batch.draw(homeTexture, homeButton.getPositionButton().x,homeButton.getPositionButton().y);
        game.batch.draw(quitTexture, quitButton.getPositionButton().x,quitButton.getPositionButton().y);
        game.batch.end();

        navigateToScreen();
    }
    private void navigateToScreen() {
        if (Gdx.input.isTouched()) {

            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            if (homeButton.getBoundsButton().contains(click.x, click.y)) {
                game.setScreen(new MainMenuScreen(game));
                dispose();

            } else if (quitButton.getBoundsButton().contains(click.x, click.y)) {
                Gdx.app.exit();
                dispose();
            }
        }
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }
    @Override
    public void show() {

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

    @Override
    public void dispose() {
        background.dispose();
        title.dispose();
        homeTexture.dispose();
        quitTexture.dispose();

    }
}
