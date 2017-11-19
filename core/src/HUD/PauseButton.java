package HUD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import Game.Config;
import Screen.GameScreen;

public class PauseButton extends HudButton
{
	
	public PauseButton(HUD hud, int index)
	{
		super(hud, index);
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
		sprite.setFlip(true, false);
		return this;
	}
	
	@Override
	public void click()
	{
		hud.pause("Pause", true);
	}

}
