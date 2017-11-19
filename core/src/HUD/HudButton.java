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
	public float scale = 1.5F;
	protected Texture texture;
	public Sprite sprite;
	
	float x, y;
	int index;
	HUD hud;
	
	public HudButton(HUD hud, int index) 
	{
		// TODO Auto-generated constructor stub
		this.index = index + 1;
		this.hud = hud;
	}
	
	public HudButton setSprite(String ref)
	{
		texture = new Texture(Gdx.files.internal(ref));
		sprite = new Sprite(texture);
		sprite.setScale(scale, scale);
		x = (Config.GAME_WIDTH) - (sprite.getWidth() + (index - 1) * 10) * (index);
		y = (Config.GAME_HEIGHT) - sprite.getHeight();
		
		float xOffset = sprite.getWidth() * (1 - scale) / 2;
		float yOffset = sprite.getHeight() * (1 - scale) / 2;
		
		sprite.setX(x + xOffset);
		sprite.setY(y + yOffset);
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
		sprite.draw(batch);
	}
	
	public void click(){}
}
