package Screen;

import Game.Config;
import Game.Core;
import Util.MenuButton;

import com.badlogic.gdx.Gdx;

public class MenuScreen extends ModScreen
{
	MenuButton play;
	MenuButton exit;
	
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
