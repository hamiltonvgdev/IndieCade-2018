package HUD;

import Environment.Thing;
import Game.Config;
import Screen.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HudButton
{
	public int scale = 15;
	private Texture texture;
	public Sprite sprite;
	
	int index;
	HUD hud;
	
	public HudButton(HUD hud, int index) 
	{
		// TODO Auto-generated constructor stub
		this.index = index;
		this.hud = hud;
	}
	
	public HudButton setSprite(String ref)
	{
		texture = new Texture(Gdx.files.internal(ref));
		sprite = new Sprite(texture);
		sprite.setX((Config.GAME_WIDTH) - 
				texture.getWidth() / scale * 6 / Config.screenScale
				- index * sprite.getWidth() / (scale / 3));
		sprite.setY((Config.GAME_HEIGHT) - 
				texture.getHeight() / scale * 6 / Config.screenScale);
		return this;
	}
	
	public void update(float delta)
	{
		if(sprite.getBoundingRectangle().contains(Gdx.input.getX(),
				Config.GAME_HEIGHT -  Gdx.input.getY())
				&& Gdx.input.justTouched())
		{
			click();
		}
	}
	public void render(SpriteBatch batch)
	{
		batch.draw(sprite,
				(Config.GAME_WIDTH) - 
					texture.getWidth() / scale * 6 / Config.screenScale 
					- index * sprite.getWidth() / (scale / 3), 
				(Config.GAME_HEIGHT) - 
					texture.getHeight() / scale * 6 / Config.screenScale,
				Config.GAME_HEIGHT / scale , Config.GAME_HEIGHT / scale);
	}
	
	public void click(){}
}
