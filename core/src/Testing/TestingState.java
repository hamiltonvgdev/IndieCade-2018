package Testing;

import com.badlogic.gdx.Gdx;

import Game.Config;
import Game.Core;
import Screen.ModScreen;
import Screen.SaveScreen;
import Util.MenuButton;

public class TestingState extends ModScreen
{
	MenuButton play;
	MenuButton exit;
	Core core;
	public TestingState(Core core)
	{
		super(core);
		this.core = core;
		play = new MenuButton(Config.GAME_WIDTH / 2
				, Config.GAME_HEIGHT / 2, 1.5F).setPhrase("PLAY");
		exit = new MenuButton(Config.GAME_WIDTH / 2
				, Config.GAME_HEIGHT / 2 - Config.MENU_BUTTON_HEIGHT, 1F).setPhrase("EXIT");
	}
	
	@Override
	public void update(float delta) 
	{
		play.update(delta);
		exit.update(delta);
		
		if(play.confirmed)
		{
			core.setScreen(new SaveScreen(core));
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