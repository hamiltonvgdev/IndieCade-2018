package Util;

import Game.Config;
import Game.Core;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;

public class MenuButton extends Button
{
	BitmapFont font;
	BitmapFont.Glyph derp;
	String phrase;

	public MenuButton(Core core, float x, float y, float scale)
	{
		super(x, y, 100, 100, scale);
		setSprite("test/Button Derp/Button Derp.scml", new ShapeRenderer());
		
		font = new BitmapFont();
		phrase = "";
	}
	
	public MenuButton setPhrase(String phrase)
	{
		this.phrase = phrase;
		return this;
	}
}
