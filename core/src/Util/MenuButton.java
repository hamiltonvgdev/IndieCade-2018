package Util;

import Game.Config;
import Renders.SpriteSheetAnimation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class MenuButton extends Button
{
	BitmapFont font;
	BitmapFont.Glyph derp;
	String phrase;

	public MenuButton(float x, float y, float scale)
	{
		super(x, y, Config.MENU_BUTTON_WIDTH * scale, Config.MENU_BUTTON_HEIGHT * scale);
		setSprite(new SpriteSheetAnimation(new Texture("Menu Button SpriteSheet.png"), 
				150, 104).setStep(10));
		
		font = new BitmapFont();
		phrase = "";
	}
	
	public MenuButton setPhrase(String phrase)
	{
		this.phrase = phrase;
		return this;
	}
	
	public void update(float delta)
	{
		super.update(delta);
		
		
		hitbox.update(x, y, height, width);
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
		
		sprite.render(x, y, width, height, 1, 1, width / 2, height / 2, -90, batch);
		font.draw(batch, phrase, x + width / 2, y + height / 2 + font.getAscent() / 2, 
				0, Align.center, false);
	}
}
