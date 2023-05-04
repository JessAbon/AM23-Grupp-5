package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.Button;
import se.yrgo.util.HighScoreHandler;
import se.yrgo.util.Misc;
import se.yrgo.util.Settings;

public class HighscoreScreen implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;

    private Texture background;
    private Texture title;

    private Texture homeTexture;
    private Texture quitTexture;
    private Texture backgroundScreen;

    private Button backButton;
    private GlyphLayout gLayout;
    float addOn = 0;

    /*private ScalingViewport viewport;*/
    private ScreenViewport viewport;
    private Scaling scaling;

    public HighscoreScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        /*viewport = new ScalingViewport((scaling) = scaling.fit, 800, 600,camera);*/
        viewport = new ScreenViewport();
        backgroundScreen = new Texture("highscoreGraphics/Background_highscore_v2.png");
        background = new Texture("highscoreGraphics/BgHighscore.png");
        title = new Texture("highscoreGraphics/highscoretitle.png");
        homeTexture = new Texture("menu/Home.png");
        quitTexture = new Texture("highscoreGraphics/back_button.png");

        backButton = new Button(400, 10, quitTexture.getWidth(), quitTexture.getHeight());
        gLayout = new GlyphLayout();
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(backgroundScreen, 0, 0, backgroundScreen.getWidth(), backgroundScreen.getHeight());
        game.batch.draw(title, 250, 500, title.getWidth(), title.getHeight());

        game.batch.draw(quitTexture, backButton.getPositionButton().x, backButton.getPositionButton().y);

        for (int i = 0; i < HighScoreHandler.getScoreArray().size(); i++) {

            gLayout.setText(game.font, HighScoreHandler.getScoreArray().get(i).getName() + " : " + HighScoreHandler.getScoreArray().get(i).getScore());
            game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, (JumpyBirb.HEIGHT - 130f) - i * (gLayout.height * 1.4f));
        }

        game.batch.end();

        navigateToScreen();
    }

    private void navigateToScreen() {
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            if (backButton.getBoundsButton().contains(click.x, click.y) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                if (Misc.previousScreen instanceof EndScreen) {
                    game.setScreen(new EndScreen(game));
                } else {
                    game.setScreen(new MainMenuScreen(game));

                }


            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
