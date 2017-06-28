package Game;


import Renders.SpriteSheetAnimation;
import Screen.MenuScreen;
import Screen.ScreenManager;
import Util.MenuButton;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Core extends Game
{		
	public SpriteBatch batch;
	
	ScreenManager sm;
	
	@Override
	public void create () 
	{
		batch = new SpriteBatch();
		
		sm = new ScreenManager(this);
		sm.setScreen(new MenuScreen(this));
	}
	
	@Override
	public void render () 
	{
		super.render();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		
		sm.render();
		
		batch.end();
	}
	
	
	
	@Override
	public void dispose () 
	{
		super.dispose();
		
		sm.dispose();
		
		batch.dispose();
	}
	
	@Override
	public void resume()
	{
		super.resume();
		super.pause();
		
		sm.resume();
	}
	
	@Override
	public void pause()
	{
		super.pause();
		sm.pause();
	}
	
	@Override 
	public void resize(int width, int height)
	{
		super.resize(width, height);
		sm.resize(width, height);
	}
	
	public ScreenManager getSM(){return sm;}
}
