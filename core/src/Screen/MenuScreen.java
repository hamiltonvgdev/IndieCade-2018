package Screen;

import Game.Config;
import Game.Core;
import Util.MenuButton;

import com.badlogic.gdx.Gdx;

public class MenuScreen extends MyScreen
{
	Core core;
	
	MenuButton play;
	MenuButton exit;
	
	public MenuScreen(Core core)
	{
		this.core = core;
		
		play = new MenuButton(Config.GAME_WIDTH / 2
				, Config.GAME_HEIGHT / 2, 1.5F).setPhrase("PLAY");
		exit = new MenuButton(Config.GAME_WIDTH / 2
				, Config.GAME_HEIGHT / 2 - Config.MENU_BUTTON_HEIGHT, 1F).setPhrase("EXIT");
	}
	
	@Override
	public void show() 
	{
		
	}
	
	@Override
	public void update(float delta) 
	{
		play.update(delta);
		exit.update(delta);
		
		if(play.confirmed)
		{
			 core.getSM().setScreen(new SaveScreen(core));
		}
		
		if(exit.confirmed)
		{
			Gdx.app.exit();
		}
	}
	
	
	public void render(float delta) 
	{
		play.render(core.batch);
		exit.render(core.batch);
	}

	@Override
	public void resize(int width, int height) 
	{
		
	}

	@Override
	public void pause() 
	{
		
	}

	@Override
	public void resume() 
	{
		
	}

	@Override
	public void hide() 
	{
		
	}

	@Override
	public void dispose() 
	{
		play.dispose();
	}

}
