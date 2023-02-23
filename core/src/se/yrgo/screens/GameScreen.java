package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.Sprites.Bird;
import se.yrgo.Sprites.Ground;
import se.yrgo.JumpyBirb;
import se.yrgo.Sprites.Tube;


public class GameScreen implements Screen {

    final JumpyBirb game;
    private Bird bird;
    private Ground ground;
    private OrthographicCamera camera;
    private static Texture bg;

    private Array<Tube> tube;
    //Sätt avstånd mellan tubes
    private static final int TUBE_SPACING = 125;
    //Rader med tubes som ska loopa genom skärmen
    private static final int TUBE_COUNT = 6;



    public GameScreen(JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        bird = new Bird(JumpyBirb.WIDTH/4, JumpyBirb.HEIGHT/2);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        ground = new Ground(0, 0);
        bg = new Texture(Gdx.files.internal("bg.png"));
        tube = new Array<Tube>();
        for (int i = 1; i <= TUBE_COUNT; i++) {
            tube.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));

        }

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
        game.batch.draw(bg, camera.position.x - (camera.viewportWidth / 2),0,JumpyBirb.WIDTH, JumpyBirb.HEIGHT);


        for (Tube tubes:tube) {
            game.batch.draw(tubes.getTopTube(), tubes.getPosTopTube().x, tubes.getPosTopTube().y);
            game.batch.draw(tubes.getBottomTube(), tubes.getPosBottomTube().x, tubes.getPosBottomTube().y);
        }

        game.batch.draw(ground.getGround(), ground.getPosition().x, ground.getPosition().y, ground.getGround().getWidth() * 3, ground.getGround().getHeight());
        //game.batch.draw(ground.getGround(), camera.position.x - (camera.viewportWidth / 2), ground.getPosition().y, ground.getGround().getWidth() * 3, ground.getGround().getHeight());
        game.batch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        game.batch.end();

        float gameHeightToFloat = (float)JumpyBirb.HEIGHT - 30;

        bird.update(delta);
        camera.position.x = bird.getPosition().x + 250;
        camera.update();
        //Movement tubes
        for (Tube tubes : tube) {
            if (camera.position.x - (camera.viewportWidth / 2) > tubes.getPosTopTube().x + tubes.getTopTube().getWidth()) {
                tubes.reposition(tubes.getPosTopTube().x + ((tubes.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bird.jump();
        }

        if (bird.getBounds().overlaps(ground.getBounds())) {
            game.setScreen(new EndScreen(game));
            dispose();
        }
        camera.update();
            game.batch.end();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bird.jump();
        }


        if (bird.getPosition().y >= gameHeightToFloat) {
            bird.removeVelocity();
            bird.setPositionY(gameHeightToFloat);
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
