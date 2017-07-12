package Screen;

import Game.Config;
import Game.Core;

import com.badlogic.gdx.Gdx;

public class ScreenManager
{
	ModScreen screen;
	Core core;
	
	public ScreenManager(Core core)
	{
		this.core = core;
	}
	
	public void dispose () 
	{
		if (screen != null) screen.hide();
	}
	
	public void pause () 
	{
		if (screen != null) screen.pause();
	}
	
	public void resume () 
	{
		if (screen != null) screen.resume();
	}
	
	public void resize (int width, int height) 
	{
		if (screen != null) screen.resize(width, height);
	}
	
	public void setScreen(ModScreen screen)
	{
		if(this.screen != null) 
		{
			this.screen.hide();
		}
		
		this.screen = screen;
		
		if(this.screen != null) 
		{
			this.screen.show();
			this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}
	
	public void update(float delta)
	{
		screen.update(delta);
	}
	
	public void render()
	{
		update(Gdx.graphics.getDeltaTime());
		screen.render(Gdx.graphics.getDeltaTime());
	}
	
	public ModScreen getScreen(){return screen;}
}
