package Game;


import Renders.SpriterAnimationEngine;
import Screen.MenuScreen;
import Screen.Test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blueacorn.spriter.LibGdxDrawer;

public class Core extends Game
{		
	public SpriteBatch batch;
	
	//Test
	LibGdxDrawer draw;
	com.brashmonkey.spriter.Player play;
	SpriterAnimationEngine renderer;
	
	@Override
	public void create () 
	{	
		batch = new SpriteBatch();
		setScreen(new MenuScreen(this));
		setScreen(new Test(this));
		
		Loadevas.init();
		renderer = new SpriterAnimationEngine(this);
		play = renderer.getPlayer("derp", "test/derp.scml");
		draw = renderer.getDrawer("derp", "test/derp.scml");
	}
	
	@Override
	public void render () 
	{
		batch.begin();
		
		super.render();
		play.update();
		draw.draw(play);
		
		batch.end();
	}
	
	
	
	@Override
	public void dispose () 
	{
		super.dispose();
		batch.dispose();
	}
	
	@Override
	public void resume()
	{
		super.resume();
		
	}
	
	@Override
	public void pause()
	{
		super.pause();
	}
	
	@Override 
	public void resize(int width, int height)
	{
		super.resize(width, height);
	}
}
