package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import se.yrgo.JumpyBirb;
import se.yrgo.util.Score;
import se.yrgo.util.Util;

public class EndScreen implements Screen {

    private static final long DELAY_TIME = 1500;
    final JumpyBirb game;
    OrthographicCamera camera;
    private final long timeStamp;

    public EndScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        timeStamp = TimeUtils.millis();
    }

    @Override
    public void show() {

    }

    @Override
   public void render(float delta) {

        game.batch.begin();
        game.font.draw(game.batch, "Score: " + Score.getScore(), Util.getGlobalHeroPositionX() + JumpyBirb.WIDTH/2F - 50, JumpyBirb.HEIGHT/2F + 40);
        game.font.draw(game.batch, "High Score: " + Score.getHighScore(), Util.getGlobalHeroPositionX() + JumpyBirb.WIDTH/2F - 50, JumpyBirb.HEIGHT/2F - 10);
        game.batch.end();

        restart();

    }
    private void restart() {

            if (game.spaceAndMouseClickInput()) {
                if (delayRestart()) {
                    game.setScreen(new GameScreen(game));
                    dispose();
                }

            }
            else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
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
}
