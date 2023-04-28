package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import se.yrgo.JumpyBirb;
import se.yrgo.sprites.Button;
import se.yrgo.util.AllTimeHighHandler;
import se.yrgo.util.MyScore;
import se.yrgo.util.Score;

public class HighscoreScreen implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;

    private Texture background;
    private Texture title;

    private Texture homeTexture;
    private Texture quitTexture;

    private Button homeButton;

    private Button quitButton;
    private GlyphLayout gLayout;
    float addOn = 0;

    /*private ScalingViewport viewport;*/
    private ScreenViewport viewport;
    private Scaling scaling;
    public HighscoreScreen(final JumpyBirb game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        /*viewport = new ScalingViewport((scaling) = scaling.fit, 800, 600,camera);*/
        viewport = new ScreenViewport();
        background = new Texture("highscore/bg_highscore_v2.png");
        title = new Texture("highscore/highscoretitle.png");
        homeTexture = new Texture("menu/Home.png");
        quitTexture = new Texture("menu/Stop.png");

        homeButton = new Button(300, 10, homeTexture.getWidth(), homeTexture.getHeight());
        quitButton = new Button(400, 10, quitTexture.getWidth(), quitTexture.getHeight());
        gLayout = new GlyphLayout();
    }
    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 0, 0, 0);
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(background, 0,0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(title, 250, 500, title.getWidth(), title.getHeight());

        game.batch.draw(homeTexture, homeButton.getPositionButton().x,homeButton.getPositionButton().y);
        game.batch.draw(quitTexture, quitButton.getPositionButton().x,quitButton.getPositionButton().y);

        for (int i = 0; i < AllTimeHighHandler.getScoreArray().size(); i++) {

            gLayout.setText(game.font, AllTimeHighHandler.getScoreArray().get(i).getName()  + " : " + AllTimeHighHandler.getScoreArray().get(i).getScore());
            game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, (JumpyBirb.HEIGHT - 130f) - i * (gLayout.height*1.4f));
        }



        game.batch.end();

        navigateToScreen();
    }
    private void navigateToScreen() {
        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {

            Vector3 click = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(click);

            if (homeButton.getBoundsButton().contains(click.x, click.y)) {
                game.setScreen(new MainMenuScreen(game));
                dispose();

            } else if (quitButton.getBoundsButton().contains(click.x, click.y) || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                game.setScreen(new EndScreen(game));
                dispose();
            }
        }
    }
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
    @Override
    public void show() {
        AllTimeHighHandler.readFile();



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
        background.dispose();
        title.dispose();
        homeTexture.dispose();
        quitTexture.dispose();

    }
}
