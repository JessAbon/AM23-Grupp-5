package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.JumpyBirb;

public class EndScreen implements Screen {
    final JumpyBirb game;
    OrthographicCamera camera;

    public EndScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
    }

    @Override
    public void show() {

    }

    @Override
   public void render(float delta) {

        ScreenUtils.clear( 0, 0.2f, 1, 0);
        game.batch.begin();
        game.font.draw(game.batch, "END SCREEN PRESS SPACE TO PLAY AGAIN", 200,200);
        game.batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            game.setScreen(new GameScreen(game));
            dispose();

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
