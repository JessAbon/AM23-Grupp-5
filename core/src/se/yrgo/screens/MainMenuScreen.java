package se.yrgo.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.Button;
import se.yrgo.util.HighScoreHandler;
import se.yrgo.util.Misc;
import se.yrgo.util.Settings;

public class MainMenuScreen extends ApplicationAdapter implements Screen {

    private static final long DELAY_TIME = 300;
    private long timeStamp;
    final JumpyBirb game;
    OrthographicCamera camera;
    private GlyphLayout gLayout;
    private Texture startbg;
    private Texture fg;
    private Texture playTexture;
    private Texture exitTexture;
    private Texture highScoreTexture;
    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button playButton;
    private Button highscoreButton;
    private Button exitButton;
    private ScreenViewport viewport;
    //private ScalingViewport viewport;
    //private Scaling scaling;

    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        //viewport = new ScalingViewport((scaling) = scaling.fit, 800, 600,camera);
        viewport = new ScreenViewport();
        gLayout = new GlyphLayout();
        startbg = new Texture("menu/bg-mainmenu.png");
        fg = new Texture("menu/fg-main-menu.png");

        playTexture = new Texture("menu/playbtn-wood.png");
        highScoreTexture = new Texture("menu/highscore_button.png");
        exitTexture = new Texture("menu/exit_button.png");

        hardButton = new Button("hard/", 510, 40, "hard-btn.png");
        mediumButton = new Button("medium/",310, 20, "medium-btn.png");
        easyButton = new Button("easy/",110, 30, "easy-btn.png");

        playButton = new Button(310, 300, playTexture.getWidth(), playTexture.getHeight());
        highscoreButton = new Button(705, 20, highScoreTexture.getWidth(), highScoreTexture.getHeight());
        exitButton = new Button(15, 550, exitTexture.getWidth(), exitTexture.getHeight());
        timeStamp = TimeUtils.millis();

        hardButton.checkStatus();
        mediumButton.checkStatus();
        easyButton.checkStatus();

        //camera.update();
    }

    @Override
    public void render(float delta) {
        hardButton.checkStatus();
        mediumButton.checkStatus();
        easyButton.checkStatus();

        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2.0f - gLayout.width / 2, JumpyBirb.HEIGHT / 2.0f + gLayout.height / 2);
        game.batch.draw(startbg, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        game.batch.draw(easyButton.getTexture(), easyButton.getPositionButton().x, easyButton.getPositionButton().y);
        game.batch.draw(mediumButton.getTexture(), mediumButton.getPositionButton().x, mediumButton.getPositionButton().y);
        game.batch.draw(hardButton.getTexture(), hardButton.getPositionButton().x, hardButton.getPositionButton().y);

        game.batch.draw(playTexture, playButton.getPositionButton().x, playButton.getPositionButton().y);
        game.batch.draw(highScoreTexture, highscoreButton.getPositionButton().x, highscoreButton.getPositionButton().y);
        game.batch.draw(exitTexture, exitButton.getPositionButton().x, exitButton.getPositionButton().y);

        if(easyButton.isPressed()) {
            game.batch.draw(easyButton.getTextureChecked(), easyButton.getPositionButton().x -20, easyButton.getPositionButton().y + 60f);
        }
        if(mediumButton.isPressed()) {
            game.batch.draw(mediumButton.getTextureChecked(), mediumButton.getPositionButton().x -10, mediumButton.getPositionButton().y + 110f);
        }
        if(hardButton.isPressed()) {
            game.batch.draw(hardButton.getTextureChecked(), hardButton.getPositionButton().x -15, hardButton.getPositionButton().y + 145f);
        }

        game.batch.end();

        if (Gdx.input.isTouched() && delayClick()) {

            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            if (playButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("PLAY");
                game.setScreen(new GameScreen(game));
                dispose();

            } else if (easyButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.easy();

                System.out.println("EASY");

            } else if (mediumButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.medium();

                System.out.println("Medium");

            } else if (hardButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.hard();

                System.out.println("Hard");

            } else if (highscoreButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("HIGHSCORE");
                HighScoreHandler.readFile();
                game.setScreen(new HighscoreScreen(game));
                Misc.setPreviousScreen(game.getScreen());

            } else if (exitButton.getBoundsButton().contains(click.x, click.y)) {
                Gdx.app.exit();
            }

        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
            dispose();
        }

        game.batch.begin();
        game.batch.draw(fg, 0, 0, fg.getWidth(), fg.getHeight());
        game.batch.end();
    }

    private boolean delayClick() {
        return TimeUtils.millis() - timeStamp > DELAY_TIME;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width , height);
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
        highScoreTexture.dispose();
        gLayout.reset();

    }

}
