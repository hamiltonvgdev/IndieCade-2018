package Game;


import Renders.SpriterAnimationEngine;
import Screen.GameScreen;
import Screen.MenuScreen;
import Screen.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.blueacorn.spriter.LibGdxDrawer;
import com.brashmonkey.spriter.Drawer;

public class Core extends Game
{		
	public SpriteBatch batch;
	

	
	@Override
	public void create() 
	{	
		batch = new SpriteBatch();
//		setScreen(new SplashScreen(this, "Logo Animation", 
//				"Logo SplashScreen/Parts/Logo Animation.scml").scale(0.3F).
//				setMusic("Audio/title_theme.wav"));
		setScreen(new GameScreen(this, new GameData(), "test"));
		
		Loadevas.init();
	}
	
	@Override
	public void render () 
	{
		batch.begin();
		
		super.render();
		
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
