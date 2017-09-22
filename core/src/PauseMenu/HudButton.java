package PauseMenu;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;
import Screen.HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HudButton extends Thing{
	public int scale = 15;
	private Texture texture;
	public Sprite sprite;
	
	int index;
	HUD hud;
	
	public HudButton(GameScreen gs, HUD hud, int index) 
	{
		super(gs);
		// TODO Auto-generated constructor stub
		texture = new Texture(Gdx.files.internal("test/pausebutton.png"));
		sprite = new Sprite(texture);
		this.index = index;
		sprite.setX((Config.GAME_WIDTH) - 
				texture.getWidth() / scale * 6 / Config.screenScale
				- index * sprite.getWidth() / (scale / 3));
		sprite.setY((Config.GAME_HEIGHT) - 
				texture.getHeight() / scale * 6 / Config.screenScale);
		
		this.hud = hud;
	}
	public void update(float delta)
	{
		if(sprite.getBoundingRectangle().contains(Gdx.input.getX(),
				Config.GAME_HEIGHT -  Gdx.input.getY())
				&& Gdx.input.justTouched())
		{
			hud.pause();
		}
	}
	public void render(SpriteBatch batch){
		super.render(batch);
		batch.draw(sprite,
				(Config.GAME_WIDTH) - 
					texture.getWidth() / scale * 6 / Config.screenScale 
					- index * sprite.getWidth() / (scale / 3), 
				(Config.GAME_HEIGHT) - 
					texture.getHeight() / scale * 6 / Config.screenScale,
				Config.GAME_HEIGHT / scale , Config.GAME_HEIGHT / scale);
	}
}
