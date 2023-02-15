package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.Bird;
import se.yrgo.Ground;
import se.yrgo.JumpyBirb;


public class GameScreen implements Screen {

    final JumpyBirb game;
    private Bird bird;
    private Ground ground;
    private OrthographicCamera camera;


    public GameScreen(JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        bird = new Bird(JumpyBirb.WIDTH/2, JumpyBirb.HEIGHT/2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        ground = new Ground(0, 0);

    }
    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,2,3,1);

        bird.update(delta);
        ground.update(delta);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(ground.getGround(), ground.getPosition().x + 200, ground.getPosition().y);
        game.batch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bird.jump();
        }

        if (bird.getBounds().overlaps(ground.getBounds())) {
            game.setScreen(new EndScreen(game));
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
