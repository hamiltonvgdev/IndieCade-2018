package Util;

import Game.Config;
import Renders.SpriteSheetAnimation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button 
{	
	final float THRESHOLD = 80;
	
	float x, y, width, height;
	SpriteSheetAnimation sprite;
	
	Quad hitbox;
	boolean clicked;
	boolean hovered;
	public boolean confirmed;
	
	long tick;
	
	public Button(float x, float y, float width, float height)
	{
		this.x = x - width / 2;
		this.y = y - height /2;
		this.width = width;
		this.height = height;
		
		hovered = false;
		clicked = false;
		confirmed = false;
		
		hitbox = new Quad(x, y, width, height);
		
		tick = System.currentTimeMillis();
	}
	
	public Button setSprite(SpriteSheetAnimation sprite)
	{
		this.sprite = sprite;
		return this;
	}
	
	public void update(float delta)
	{
		hitbox.update(x + width / 2, y + height / 2, width, height);
		if(hitbox.check(Gdx.input.getX(), Config.GAME_HEIGHT - Gdx.input.getY()))
		{
			hovered = true;
			
			if(Gdx.input.isTouched())
			{
				clicked = true;
			}else
			{
				clicked = false;
			}
		}else
		{
			hovered = false;
			clicked = false;
		}
		
		if(clicked)
		{
			if(System.currentTimeMillis() - tick >= THRESHOLD)
			{
				confirmed = true;
			}
		}else
		{
			tick = System.currentTimeMillis();
		}
	}
	
	public void render(SpriteBatch batch)
	{
		if(hovered)
		{
			sprite.changeState(1);
		}else
		{
			sprite.changeState(0);
		}
		
		sprite.render(x, y, width, height, 1, 1, width / 2, height / 2, 0, batch);
		
		hitbox.render(batch);
	}
	
	public void dispose()
	{
		clicked = false;
		hovered = false;
		confirmed = false;
	}
}
