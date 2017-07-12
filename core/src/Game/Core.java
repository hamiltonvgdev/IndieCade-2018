package Game;


import Renders.SpriteSheetAnimation;
import Renders.SpriterAnimationEngine;
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
import com.blueacorn.spriter.LibGdxDrawer;

public class Core extends Game
{		
	public SpriteBatch batch;
	
	ScreenManager sm;
	
	//Test
	LibGdxDrawer draw;
	com.brashmonkey.spriter.Player play;
	SpriterAnimationEngine renderer;
	
	@Override
	public void create () 
	{	
		batch = new SpriteBatch();
		
		sm = new ScreenManager(this);
		sm.setScreen(new MenuScreen(this));
		
		Loadevas.init();
		renderer = new SpriterAnimationEngine(this);
		play = renderer.getPlayer("derp", "test/derp.scml");
		draw = renderer.getDrawer("derp", "test/derp.scml");
	}
	
	@Override
	public void render () 
	{
		super.render();
		sm.render();
		
		play.update();
		draw.draw(play);
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
