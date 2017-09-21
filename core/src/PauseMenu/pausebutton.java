package PauseMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;

public class pausebutton extends Thing{
	public int scale = 15;
	private Texture texture;
	private Sprite pausebutton;
	public pausebutton(GameScreen gs) {
		super(gs);
		// TODO Auto-generated constructor stub
		texture = new Texture(Gdx.files.internal("test/pausebutton.png"));
		pausebutton = new Sprite(texture);
	}
	public void update(float delta){
		
	}
	public void render(SpriteBatch batch){
		super.render(batch);
		batch.draw(pausebutton,
				(Config.GAME_WIDTH) - texture.getWidth() / scale * 6 / Config.screenScale , 
				(Config.GAME_HEIGHT) - texture.getHeight() / scale * 6 / Config.screenScale,
				Config.GAME_HEIGHT / scale , Config.GAME_HEIGHT / scale);
	}
}
