package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import se.yrgo.JumpyBirb;
import se.yrgo.Sprites.Bird;
import se.yrgo.util.Score;

public class EndScreen implements Screen {
    final JumpyBirb game;
    OrthographicCamera camera;
    private final long delayTime;

    public EndScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        delayTime = TimeUtils.millis();
    }

    @Override
    public void show() {

    }

    @Override
   public void render(float delta) {

        //ScreenUtils.clear(Color.BLACK);

        game.batch.begin();
        //game.batch.draw(GameScreen.screenShot, 0,0);
        game.font.draw(game.batch, "Score: " + Score.printScore(), Score.getPosition(), 400);
        game.font.draw(game.batch, "High Score: " + Score.printHighScore(), Score.getPosition(), 300);
        game.batch.end();


        //if isKeyPressed && keyPressedTime > desiredDelay

        if (TimeUtils.millis() - delayTime > 1500) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                game.setScreen(new GameScreen(game));
                dispose();

            }else if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
                Gdx.app.exit();
            }
        }

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
