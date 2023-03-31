package se.yrgo.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import se.yrgo.JumpyBirb;
import se.yrgo.util.Settings;

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

    private Texture easyButton;
    private Texture easyButtonPressed;
    private Texture mediumButton;
    private Texture mediumButtonPressed;
    private Texture hardButton;
    private Texture hardButtonPressed;

    private Texture playButton;

    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        gLayout = new GlyphLayout();
        startbg = new Texture("menu/bg-mainmenu.png");
        fg = new Texture("menu/fg-main-menu.png");
        easy = new Rectangle(130, 400, 175, 75);
        medium = new Rectangle(340, 380, 175, 75);
        hard = new Rectangle(540, 300, 180, 80);
        play = new Rectangle(330, 210, 180, 80);

        easyButton = new Texture("menu/easy-btn.png");
        easyButtonPressed = new Texture("menu/easy-btn-pressed.png");
        mediumButton = new Texture("menu/medium-btn.png");
        mediumButtonPressed = new Texture("menu/medium-btn-pressed.png");
        hardButton = new Texture("menu/hard-btn.png");
        hardButtonPressed = new Texture("menu/hard-pressed.png");
        playButton = new Texture("menu/playbtn-wood.png");
    }


    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        game.batch.begin();

        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2.0f - gLayout.width / 2, JumpyBirb.HEIGHT / 2.0f + gLayout.height / 2);
        game.batch.draw(startbg, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(easyButton, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(mediumButton, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(hardButton, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(playButton, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            if (play.contains(x, y)) {
                System.out.println("PLAY");
                game.setScreen(new GameScreen(game));
            } else if (easy.contains(x, y)) {
                Settings.easy();
                game.batch.draw(easyButtonPressed, camera.position.x - (camera.viewportWidth / 2),
                        0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

                //game.setScreen(new GameScreen(game));
                System.out.println("EASY");
                dispose();
            } else if (medium.contains(x, y)) {
                Settings.medium();
                game.batch.draw(mediumButtonPressed, camera.position.x - (camera.viewportWidth / 2),
                        0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

                //game.setScreen(new GameScreen(game));
                System.out.println("Medium");
                dispose();
            } else if (hard.contains(x, y)) {
                Settings.hard();
                game.batch.draw(hardButtonPressed, camera.position.x - (camera.viewportWidth / 2),
                        0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

                //game.setScreen(new GameScreen(game));
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
        game.batch.draw(fg, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
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
