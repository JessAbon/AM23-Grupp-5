package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Sort;
import com.badlogic.gdx.utils.TimeUtils;
import se.yrgo.JumpyBirb;
import se.yrgo.Sprites.Button;
import se.yrgo.util.Score;
import se.yrgo.util.Util;

public class EndScreen implements Screen {

    private static final long DELAY_TIME = 1500;
    final JumpyBirb game;
    OrthographicCamera camera;
    private final long timeStamp;
    private GlyphLayout gLayout;

    private Texture gameOver;

    private Texture playBtn;

    private Texture playBtnPressed;

    private Texture homeBtn;

    private Texture homeBtnPressed;

    private Texture stopBtn;

    private Texture stopBtnPressed;
    private Rectangle play;
    private Rectangle quit;
    private Rectangle mainMenu;
    private Button playTestbtn;

    public EndScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        timeStamp = TimeUtils.millis();
        gLayout = new GlyphLayout();

        //playTestbtn = new Button(Util.getGlobalPositionZeroX(), 0 , 100 ,100);

        gameOver = new Texture("menu/Bg.png");
        playBtn = new Texture("menu/PlayTest.png");
        playBtnPressed = new Texture("menu/Play-pressed.png");
        homeBtn = new Texture("menu/Home.png");
        homeBtnPressed = new Texture("menu/Home-pressed.png");
        stopBtn = new Texture("menu/Stop.png");
        stopBtnPressed = new Texture("menu/Stop-pressed.png");

        //play = new Rectangle(200, 450, 100, 100);
        quit = new Rectangle(370, 450, 100, 100);
        mainMenu = new Rectangle(500, 450, 100, 100);
        //play = new Rectangle(190, 60, playBtn.getWidth(), playBtn.getHeight());

        playTestbtn = new Button(0, 0 , playBtn.getWidth() ,playBtn.getHeight());

        /*ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        Vector3 v = camera.unproject(new Vector3(playTestbtn.getBoundsButton().x, playTestbtn.getBoundsButton().y, 0),
                0, 0, camera.viewportWidth, camera.viewportHeight);

        shapeRenderer.rect(v.x, v.y, playTestbtn.getBoundsButton().width, playTestbtn.getBoundsButton().height);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(playTestbtn.getBoundsButton().x, playTestbtn.getBoundsButton().y, playTestbtn.getBoundsButton().width, playTestbtn.getBoundsButton().height);
        shapeRenderer.end();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(quit.x, quit.y, quit.width, quit.height);
        shapeRenderer.end();*/


        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.rect(mainMenu.x, mainMenu.y, mainMenu.width, mainMenu.height);
        //shapeRenderer.end();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
            System.out.println("bounds: x= "+ playTestbtn.getBoundsButton().x + "y= " + playTestbtn.getBoundsButton().y);

            System.out.println(playTestbtn.getPositionButton().x);
            System.out.println(playTestbtn.getPositionButton().y);

        }

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(gameOver, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(homeBtn, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        game.batch.draw(stopBtn, 0, 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);
        gLayout.setText(game.font, "SCORE: " + Score.getScore());

        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 2.7F + gLayout.height * 2);
        gLayout.setText(game.font, "HIGHSCORE: " + Score.getHighScore());
        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH / 2F - gLayout.width / 2F, JumpyBirb.HEIGHT / 3F + gLayout.height);
        game.batch.draw(playBtn, 190, 60, playBtn.getWidth(), playBtn.getHeight());


        game.batch.draw(playBtn, playTestbtn.getPositionButton().x, playTestbtn.getPositionButton().y);



        if (Gdx.input.isTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.input.getY();

            System.out.println("x= "+ x + " y= " + y );


            if (playTestbtn.getBoundsButton().contains(x, y - JumpyBirb.HEIGHT + playBtn.getHeight())) {
                System.out.println("PLAY END");
                game.setScreen(new GameScreen(game));
                dispose();
            } else if (quit.contains(x, y)) {
                System.out.println("QUIT");
                //Gdx.app.exit();
                dispose();
            } else if (mainMenu.contains(x, y)) {
                System.out.println("MAIN MENU");
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        }
            game.batch.end();

            //restart();

    }

    private void restart() {

        if (game.spaceAndMouseClickInput()) {
            if (delayRestart()) {
                game.setScreen(new GameScreen(game));
                dispose();
            }

        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
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
