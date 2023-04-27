package se.yrgo.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.Button;
import se.yrgo.util.Settings;

public class MainMenuScreen extends ApplicationAdapter implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;
    private GlyphLayout gLayout;
    private Texture startbg;
    private Texture fg;
    private Texture easyTexture;
    private Texture easyButtonPressed;
    private Texture mediumTexture;
    private Texture mediumButtonPressed;
    private Texture hardBtnTexture;
    private Texture hardButtonPressed;
    private Texture playTexture;
    private Texture exitTexture;
    private Texture highScoreTexture;
    private Button mediumButton;
    private Button hardButton;
    private Button easyButton;
    private Button playButton;
    private Button highscoreButton;
    private Button exitButton;
    private ScalingViewport viewport;
    private Scaling scaling;

    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        viewport = new ScalingViewport((scaling) = scaling.fit, 800, 600,camera);
        gLayout = new GlyphLayout();
        startbg = new Texture("menu/bg-mainmenu.png");
        fg = new Texture("menu/fg-main-menu.png");

        easyTexture = new Texture("menu/easy-btn.png");
        easyButtonPressed = new Texture("menu/button_pressed.png");
        mediumTexture = new Texture("menu/medium-btn.png");
        mediumButtonPressed = new Texture("menu/button_pressed.png");
        hardBtnTexture = new Texture("menu/hard-btn.png");
        hardButtonPressed = new Texture("menu/button_pressed.png");
        playTexture = new Texture("menu/playbtn-wood.png");
        highScoreTexture = new Texture("menu/highscore_button.png");
        exitTexture = new Texture("menu/exit_button.png");

        playButton = new Button(310, 300, playTexture.getWidth(), playTexture.getHeight());
        hardButton = new Button(510, 40, hardBtnTexture.getWidth(), hardBtnTexture.getHeight());
        mediumButton = new Button(310, 20, mediumTexture.getWidth(), mediumTexture.getHeight());
        easyButton = new Button(110, 30, easyTexture.getWidth(), easyTexture.getHeight());
        highscoreButton = new Button(705, 20, highScoreTexture.getWidth(), easyButtonPressed.getHeight());
        exitButton = new Button(15, 550, exitTexture.getWidth(), exitTexture.getHeight());

        //camera.update();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2.0f - gLayout.width / 2, JumpyBirb.HEIGHT / 2.0f + gLayout.height / 2);
        game.batch.draw(startbg, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        game.batch.draw(easyTexture, easyButton.getPositionButton().x, easyButton.getPositionButton().y);
        game.batch.draw(mediumTexture, mediumButton.getPositionButton().x, mediumButton.getPositionButton().y);
        game.batch.draw(hardBtnTexture, hardButton.getPositionButton().x, hardButton.getPositionButton().y);
        game.batch.draw(playTexture, playButton.getPositionButton().x, playButton.getPositionButton().y);
        game.batch.draw(highScoreTexture, highscoreButton.getPositionButton().x, highscoreButton.getPositionButton().y);
        game.batch.draw(exitTexture, exitButton.getPositionButton().x, exitButton.getPositionButton().y);

        if (Gdx.input.isTouched()) {

            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            if (playButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("PLAY");
                game.setScreen(new GameScreen(game));
                dispose();

            } else if (easyButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.easy();
                game.batch.draw(easyButtonPressed, easyButton.getPositionButton().x, easyButton.getPositionButton().y + 40, easyTexture.getWidth(), easyTexture.getHeight() - 10);
                System.out.println("EASY");

            } else if (mediumButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.medium();
                game.batch.draw(mediumButtonPressed, mediumButton.getPositionButton().x, mediumButton.getPositionButton().y + 80, mediumTexture.getWidth(), mediumTexture.getHeight() - 50);
                System.out.println("Medium");


            } else if (hardButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.hard();
                game.batch.draw(hardButtonPressed, hardButton.getPositionButton().x, hardButton.getPositionButton().y + 115, hardBtnTexture.getWidth(), hardBtnTexture.getHeight() - 100);
                System.out.println("Hard");

            } else if (highscoreButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("HIGHSCORE");
                game.setScreen(new HighscoreScreen(game));

            } else if (exitButton.getBoundsButton().contains(click.x, click.y)) {
                Gdx.app.exit();
            }

        }

        game.batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

        game.batch.begin();
        game.batch.draw(fg, 0, 0, fg.getWidth(), fg.getHeight());
        game.batch.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width , height, true);
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
        fg.dispose();
        startbg.dispose();
        playTexture.dispose();
        exitTexture.dispose();
        easyTexture.dispose();
        mediumTexture.dispose();
        hardBtnTexture.dispose();
        easyButtonPressed.dispose();
        mediumButtonPressed.dispose();
        hardButtonPressed.dispose();
        highScoreTexture.dispose();
        gLayout.reset();
    }

}
