package Screen;

import Game.Config;
import Game.Core;
import Util.MenuButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MenuScreen extends ModScreen
{
	MenuButton play;
	MenuButton exit;
	
	long musicId;
	Sound music;
	
	public MenuScreen(Core core)
	{
		super(core);
		
		play = new MenuButton(Config.GAME_WIDTH / 2
				, Config.GAME_HEIGHT / 2, 1.5F).setPhrase("PLAY");
		exit = new MenuButton(Config.GAME_WIDTH / 2
				, Config.GAME_HEIGHT / 2 - Config.MENU_BUTTON_HEIGHT, 1F).setPhrase("EXIT");
	}
	
	@Override
	public void update(float delta) 
	{
		if(music == null)
		{
			music = Gdx.audio.newSound(Gdx.files.internal("Audio/menu.wav"));
			musicId = music.loop();
		}
		
		play.update(delta);
		exit.update(delta);
		
		if(play.confirmed)
		{
			//core.setScreen(new TransitionScreen(core, new SaveScreen(core)));
			core.setScreen(new TransitionScreen(core, 
					new SaveScreen(core).setPreviousMusic(music, musicId)));
		}
		
		if(exit.confirmed)
		{
			Gdx.app.exit();
		}
		
	}
	
	
	public void render(float delta) 
	{
		super.render(delta);
		
		play.render(core.batch);
		exit.render(core.batch);
	}
	
	@Override
	public void dispose() 
	{
		super.dispose();
		
		play.dispose();
		exit.dispose();
	}

}
