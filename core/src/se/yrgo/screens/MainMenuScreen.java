package se.yrgo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import se.yrgo.JumpyBirb;

public class MainMenuScreen implements Screen {

    final JumpyBirb game;
    OrthographicCamera camera;
    private GlyphLayout gLayout;

    private Texture startbg;

    Rectangle easy;
    Rectangle medium;
    Rectangle hard;

    ShapeRenderer shapeRenderer;


    public MainMenuScreen(final JumpyBirb game) {
        this.game = game;
        camera = new OrthographicCamera();
        gLayout = new GlyphLayout();
        startbg = new Texture("Startplaceholder.png");
        easy = new Rectangle(150, 125, 175, 75);
        medium = new Rectangle(345, 150, 175, 75);
        hard = new Rectangle(540, 250, 175, 75);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                float x = screenX;
                float y = screenY;

                Vector3 worldCoords = camera.unproject(new Vector3(x, y, 0));
                System.out.println("mouseclick x" + x);
                System.out.println("mouseclick y" + y);


                if (easy.contains(worldCoords.x, worldCoords.y)) {
                    game.setScreen(new GameScreen(game));
                    System.out.println("EASY");
                } else if (medium.contains(worldCoords.x, worldCoords.y)) {
                    System.out.println("Medium button pressed");
                } else if (hard.contains(worldCoords.x, worldCoords.y)) {
                    System.out.println("Hard button pressed");
                }
                return true;
            }
        });

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(0, 1, 1, 1);


        gLayout.setText(game.font, "MAIN MENU SCREEN");

        game.batch.begin();

        game.font.draw(game.batch, gLayout, JumpyBirb.WIDTH/2.0f - gLayout.width/2, JumpyBirb.HEIGHT/2.0f + gLayout.height/2);
        game.batch.draw(startbg, camera.position.x - (camera.viewportWidth / 2), 0, JumpyBirb.WIDTH, JumpyBirb.HEIGHT);

        game.batch.end();

        shapeRenderer.begin();
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(easy.x, easy.y, easy.width, easy.height);
        shapeRenderer.rect(medium.x, medium.y, medium.width, medium.height);
        shapeRenderer.rect(hard.x, hard.y, hard.width, hard.height);
        shapeRenderer.end();

        //if (game.spaceAndMouseClickInput()) {
        //    game.setScreen(new GameScreen(game));
        //    dispose();
        //}

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
