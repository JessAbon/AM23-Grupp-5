package se.yrgo.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import se.yrgo.JumpyBirb;
import se.yrgo.Sprites.Button;
import se.yrgo.util.Settings;
import se.yrgo.util.Util;

public class MainMenuScreen extends ApplicationAdapter implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;
    private GlyphLayout gLayout;

    private Texture startbg;

    private Texture fg;

    private Rectangle easy;
    private Rectangle medium;
    private Rectangle hard;

    private Rectangle play;

    private Texture easyTexture;
    private Texture easyButtonPressed;
    private Texture mediumTexture;
    private Texture mediumButtonPressed;
    private Texture hardBtnTexture;
    private Texture hardButtonPressed;

    private Texture playTexture;

    private Button mediumButton;
    private Button hardButton;

    private Button easyButton;

    private Button playButton;

    // TODO: 2023-04-14 Fixa alla knappar till png i sin egna storlek och positionera på skärm i mainMenu och EndScreen
    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        gLayout = new GlyphLayout();

        startbg = new Texture("menu/bg-mainmenu.png");
        fg = new Texture("menu/fg-main-menu.png");

        easyTexture = new Texture("menu/easy-btn.png");
        easyButtonPressed = new Texture("menu/easy-btn-pressed.png");
        mediumTexture = new Texture("menu/medium-btn.png");
        mediumButtonPressed = new Texture("menu/medium-btn-pressed.png");
        hardBtnTexture = new Texture("menu/hard-btn.png");
        hardButtonPressed = new Texture("menu/hard-pressed.png");
        playTexture = new Texture("menu/playbtn-wood.png");

        playButton = new Button(310, 300, playTexture.getWidth(), playTexture.getHeight());
        hardButton = new Button(510, 40, hardBtnTexture.getWidth(), hardBtnTexture.getHeight());
        mediumButton = new Button(310, 20, mediumTexture.getWidth(), mediumTexture.getHeight());
        easyButton = new Button(110, 30, easyTexture.getWidth(), easyTexture.getHeight());


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();

        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2.0f - gLayout.width / 2, JumpyBirb.HEIGHT / 2.0f + gLayout.height / 2);
        game.batch.draw(startbg, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        game.batch.draw(easyTexture, easyButton.getPositionButton().x, easyButton.getPositionButton().y);
        game.batch.draw(mediumTexture, mediumButton.getPositionButton().x, mediumButton.getPositionButton().y);
        game.batch.draw(hardBtnTexture, hardButton.getPositionButton().x, hardButton.getPositionButton().y);
        game.batch.draw(playTexture, playButton.getPositionButton().x, playButton.getPositionButton().y);

        if (Gdx.input.isTouched()) {

            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            if (playButton.getBoundsButton().contains(click.x, click.y)) {
                System.out.println("PLAY");
                game.setScreen(new GameScreen(game));

            } else if (easyButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.easy();
                game.batch.draw(easyButtonPressed, easyButton.getPositionButton().x,
                        easyButton.getPositionButton().y, easyTexture.getWidth(), easyTexture.getHeight());
                System.out.println("EASY");
                dispose();

            } else if (mediumButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.medium();
                game.batch.draw(mediumButtonPressed, mediumButton.getPositionButton().x,
                        mediumButton.getPositionButton().y, mediumTexture.getWidth(), mediumTexture.getHeight());
                System.out.println("Medium");
                dispose();

            } else if (hardButton.getBoundsButton().contains(click.x, click.y)) {
                Settings.hard();
                game.batch.draw(hardButtonPressed,hardButton.getPositionButton().x,
                        hardButton.getPositionButton().y, hardBtnTexture.getWidth(), hardBtnTexture.getHeight());
                System.out.println("Hard");
                dispose();
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
