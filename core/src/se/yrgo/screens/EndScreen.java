package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.Button;
import se.yrgo.util.Score;

public class EndScreen implements Screen {

    private static final long DELAY_TIME = 1500;
    final JumpyBirb game;
    OrthographicCamera camera;
    private final long timeStamp;
    private GlyphLayout gLayout;

    private Texture gameOver;

    private Texture playTexture;

    private Texture playTexturePressed;

    private Texture homeTexture;

    private Texture homeTexturePressed;

    private Texture stopTexture;

    private Texture stopTexturePressed;

    private Button playButton;

    private Button homeButton;

    private Button stopButton;

    private ScreenViewport viewport;

    // TODO: 2023-04-14 background disappears when resizing
    public EndScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        viewport = new ScreenViewport();
        timeStamp = TimeUtils.millis();
        gLayout = new GlyphLayout();

        gameOver = new Texture("menu/Bg.png");
        playTexture = new Texture("menu/PlayTest.png");
        playTexturePressed = new Texture("menu/Play-pressed.png");
        homeTexture = new Texture("menu/Home.png");
        homeTexturePressed = new Texture("menu/Home-pressed.png");
        stopTexture = new Texture("menu/Stop.png");
        stopTexturePressed = new Texture("menu/Stop-pressed.png");

        playButton = new Button(190, 50, playTexture.getWidth(), playTexture.getHeight());
        homeButton = new Button(365, 50, homeTexture.getWidth(), homeTexture.getHeight());
        stopButton = new Button(550, 50, stopTexture.getWidth(), stopTexture.getHeight());

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(gameOver, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        game.batch.draw(playTexture, playButton.getPositionButton().x, playButton.getPositionButton().y);
        game.batch.draw(homeTexture, homeButton.getPositionButton().x, homeButton.getPositionButton().y);
        game.batch.draw(stopTexture, stopButton.getPositionButton().x, stopButton.getPositionButton().y);

        gLayout.setText(game.font, "SCORE: " + Score.getScore());
        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 2.7F + gLayout.height * 2);
        gLayout.setText(game.font, "HIGHSCORE: " + Score.getHighScore());
        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 3F + gLayout.height);

        if (Gdx.input.isTouched()) {

            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            if (playButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("PLAY END");
                game.setScreen(new GameScreen(game));
                dispose();
            } else if (stopButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("QUIT");
                Gdx.app.exit();
                dispose();
            } else if (homeButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("MAIN MENU");
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        }
        game.batch.end();

        //restart();

    }

    private void restart() {

        if (game.spaceAndMouseClickInput()) {
            if (delayRestart()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            Gdx.app.exit();
        }
    }

    private boolean delayRestart() {
        return TimeUtils.millis() - timeStamp > DELAY_TIME;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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

    }
}
