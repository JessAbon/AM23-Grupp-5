package se.yrgo.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import se.yrgo.JumpyBirb;
import se.yrgo.util.*;

import java.io.IOException;

public class EndScreen implements Screen, InputProcessor{

    private static final long DELAY_TIME = 1500;
    final JumpyBirb game;
    OrthographicCamera camera;
    private final long timeStamp;
    private GlyphLayout gLayout;
    private Texture gameOver;
    private String inputText;

    public EndScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        timeStamp = TimeUtils.millis();
        gLayout = new GlyphLayout();
        gameOver = new Texture("gameovertest.png");
        inputText = "";


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(2,0,0,0);

        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            AllTimeHighHandler.readFile();
            AllTimeHighHandler.addScore(new MyScore(1000, "TEST"));
        }

        try {
            if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {

                AllTimeHighHandler.writeFile();
            }
        } catch (IOException e) {
            System.err.println("FEEEEL " + e.getMessage());
        }


        game.batch.begin();
        game.batch.draw(gameOver, Util.getGlobalHeroPositionXzero(), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        gLayout.setText(game.font, "SCORE: " + Score.getScoreString());
        game.font.draw(game.batch, gLayout, Util.getGlobalHeroPositionXzero() + JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 2.7F + gLayout.height * 2);

        gLayout.setText(game.font, "HIGHSCORE: " + Score.getHighScoreString());
        game.font.draw(game.batch, gLayout, Util.getGlobalHeroPositionXzero() + JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 3F + gLayout.height);

        gLayout.setText(game.font, this.inputText);
        game.font.draw(game.batch, gLayout, Util.getGlobalHeroPositionXzero() + JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 2F + gLayout.height);
        game.batch.end();

        restart();



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

    @Override
    public boolean keyDown(int keycode) {
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
            return true;
        }
        if(character == ' ') {
            return false;
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
