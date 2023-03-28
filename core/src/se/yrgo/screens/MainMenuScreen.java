package se.yrgo.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.JumpyBirb;

public class MainMenuScreen implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;
    private GlyphLayout gLayout;

    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        gLayout = new GlyphLayout();
    }

    @Override
    public void show() {
        gLayout.setText(game.font, "MAIN MENU SCREEN");
    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 1, 1, 1);

        game.batch.begin();
        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH/2.0f - gLayout.width/2, JumpyBirb.HEIGHT/2.0f + gLayout.height/2);
        game.batch.end();

        if (game.spaceAndMouseClickInput()) {
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

    public JumpyBirb getGame() {
        return game;
    }

}
