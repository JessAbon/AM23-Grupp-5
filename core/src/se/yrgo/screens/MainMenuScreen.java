package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.JumpyBirb;

public class MainMenuScreen implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;
    private GlyphLayout gLayout;

    private Texture startbg;

    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        gLayout = new GlyphLayout();
        startbg = new Texture("Startplaceholder.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 1, 1, 1);
        gLayout.setText(game.font, "MAIN MENU SCREEN");

        game.batch.begin();

        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH/2.0f - gLayout.width/2, JumpyBirb.HEIGHT/2.0f + gLayout.height/2);
        game.batch.draw(startbg, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
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

}
