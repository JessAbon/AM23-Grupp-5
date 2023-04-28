package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.ButtonInLine;
import se.yrgo.util.AllTimeHighHandler;
import se.yrgo.util.MyScore;
import se.yrgo.util.Score;

import java.io.IOException;

import static se.yrgo.util.Score.getScore;

public class EndScreen implements Screen, InputProcessor {

    private static final long DELAY_TIME = 1500;
    final JumpyBirb game;
    OrthographicCamera camera;
    private final long timeStamp;
    private GlyphLayout gLayout;

    private Texture gameOver;
    private String inputText;

    private Texture playTexture;
    private Texture bg;

    private Texture homeTexture;


    private Texture stopTexture;

    private Texture newHighscore;

    private Texture newHighscoreTitle;
    private Texture highscoreButtonTexture;
    private ButtonInLine highscoreButton;
    private ButtonInLine playButton;

    private ButtonInLine homeButton;

    private ButtonInLine stopButton;

    public String enterName;
    //private ScalingViewport viewport;
    private ScreenViewport viewport;

    private Scaling scaling;

    public EndScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        /*viewport = new ScalingViewport((scaling) = scaling.fit, 800, 600, camera);*/
        viewport = new ScreenViewport();
        timeStamp = TimeUtils.millis();
        gLayout = new GlyphLayout();
        inputText = "";
        enterName = "ENTER NAME: ";

        bg = new Texture("highscore/BgHighscore.png");
        gameOver = new Texture("menu/Bg.png");
        playTexture = new Texture("menu/Play.png");
        homeTexture = new Texture("menu/Home.png");
        stopTexture = new Texture("menu/exit_button.png");
        newHighscore = new Texture("highscore/input_highscore.png");
        newHighscoreTitle = new Texture("highscore/new_highscore.png");
        highscoreButtonTexture = new Texture("highscore/highscoreButton.png");

        playButton = new ButtonInLine(240, 45, playTexture.getWidth(), playTexture.getHeight());
        homeButton = new ButtonInLine(360, 50, homeTexture.getWidth(), homeTexture.getHeight());
        highscoreButton = new ButtonInLine(480, 50, highscoreButtonTexture.getWidth(), highscoreButtonTexture.getHeight());
        stopButton = new ButtonInLine(10, 550, stopTexture.getWidth(), stopTexture.getHeight());

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bg, 0,0, bg.getWidth(),bg.getHeight());

        if (AllTimeHighHandler.isHighScore) {
            game.batch.draw(newHighscore, 130, 70, newHighscore.getWidth(), newHighscore.getHeight());
            game.batch.draw(newHighscoreTitle, 180, 400, newHighscoreTitle.getWidth(), newHighscoreTitle.getHeight());

            game.batch.draw(playTexture, playButton.getPositionButton().x, playButton.getPositionButton().y - 10);
            game.batch.draw(homeTexture, homeButton.getPositionButton().x, homeButton.getPositionButton().y - 10);
            game.batch.draw(stopTexture, stopButton.getPositionButton().x, stopButton.getPositionButton().y - 10);
            game.batch.draw(highscoreButtonTexture, highscoreButton.getPositionButton().x, highscoreButton.getPositionButton().y - 10);


            gLayout.setText(game.font, enterName + inputText);
            game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2f - gLayout.width / 2f, JumpyBirb.HEIGHT / 2f - gLayout.height * 5);

            gLayout.setText(game.font, "SCORE: " + Score.getScoreString());
            game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 2.7F + gLayout.height * 4);

            navigateToScreen();

        } else {

            game.batch.draw(gameOver, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
            gLayout.setText(game.font, "SCORE: " + Score.getScoreString());
            game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 2.7F + gLayout.height * 2);

            gLayout.setText(game.font, "YOUR BEST: " + Score.getHighScoreString());
            game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 3F + gLayout.height);

            game.batch.draw(playTexture, playButton.getPositionButton().x, playButton.getPositionButton().y);
            game.batch.draw(homeTexture, homeButton.getPositionButton().x, homeButton.getPositionButton().y);
            game.batch.draw(stopTexture, stopButton.getPositionButton().x, stopButton.getPositionButton().y);
            game.batch.draw(highscoreButtonTexture, highscoreButton.getPositionButton().x, highscoreButton.getPositionButton().y);


            navigateToScreen();
        }
        game.batch.end();

        //restart();


    }

    private void navigateToScreen() {
        if (game.spaceAndMouseClickInput()) {

            if (delayRestart()) {

                Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(click);

                if (playButton.getBoundsButton().contains(click.x, click.y)) {
                    System.out.println("PLAY END");
                    game.setScreen(new GameScreen(game));
                    dispose();
                } else if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    if (!AllTimeHighHandler.isHighScore) {
                        game.setScreen(new GameScreen(game));
                        dispose();
                    }
                } else if (stopButton.getBoundsButton().contains(click.x, click.y)) {
                    System.out.println("QUIT");
                    Gdx.app.exit();
                    dispose();
                } else if (homeButton.getBoundsButton().contains(click.x, click.y)) {
                    System.out.println("MAIN MENU");
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                } else if (highscoreButton.getBoundsButton().contains(click.x, click.y)) {
                    System.out.println("HIGHSCORE SCREEN?");
                    game.setScreen(new HighscoreScreen(game));
                    dispose();
                }
            }
        }

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
        bg.dispose();
        gameOver.dispose();
        playTexture.dispose();
        homeTexture.dispose();
        stopTexture.dispose();
        newHighscore.dispose();
        newHighscoreTitle.dispose();
        highscoreButtonTexture.dispose();
        gLayout.reset();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == 66) {
            MyScore myScore = new MyScore(getScore(), inputText);
            try {
                AllTimeHighHandler.addScore(myScore);
                inputText = "";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            AllTimeHighHandler.isHighScore = false;
            game.setScreen(new HighscoreScreen(game));
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        String s = Character.toString(character);

        if (s.matches("[a-z]")) {
            char sChar = s.charAt(0);
            inputText += character;
            hideText();
            return true;
        } else if (s.matches("[å,ä]")) {
            inputText += "a";
            hideText();
        } else if (s.matches("ö")) {
            inputText += "o";
            hideText();
        }
        return false;
    }

    public void hideText() {
        if (inputText == null) {
            enterName = "ENTER NAME: ";
        } else {
            enterName = "";
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
