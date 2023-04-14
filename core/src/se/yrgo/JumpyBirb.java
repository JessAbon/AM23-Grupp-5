package se.yrgo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import se.yrgo.screens.MainMenuScreen;

public class JumpyBirb extends Game {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	private Music music;
	public SpriteBatch batch;
	public BitmapFont font;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("Fonts/Font3.1.fnt"));
		this.setScreen(new MainMenuScreen(this));
		music = Gdx.audio.newMusic(Gdx.files.internal("sound/radish-music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		music.dispose();

	}
	public boolean spaceAndMouseClickInput() {
		return Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched();
	}
}
