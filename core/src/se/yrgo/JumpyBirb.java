package se.yrgo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.yrgo.screens.MainMenuScreen;

public class JumpyBirb extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;


	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("Fonts/Font2.fnt"));
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
	public boolean spaceAndMouseClickInput() {
		return Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched();
	}
}
