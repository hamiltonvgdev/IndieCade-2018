package Game;


import Screen.GameScreen;
import Screen.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Core extends Game
{		
	public SpriteBatch batch;
	
	public WeaponList wl;
	public EnemyList el;
	
	@Override
	public void create() 
	{	
		batch = new SpriteBatch();
//		setScreen(new SplashScreen(this, "Logo Animation", 
//				"Logo SplashScreen/Parts/Logo Animation.scml").scale(0.3F).
//				setMusic("Audio/title_theme.wav"));
		setScreen(new GameScreen(this, new GameData(), "SpeedTest"));
		
		
		Loadevas.init();
		wl.init();
		el.init();
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
