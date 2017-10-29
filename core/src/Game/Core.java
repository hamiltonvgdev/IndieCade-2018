package Game;


import FinalBoss.BossBody;
import Screen.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Core extends Game
{		
	public SpriteBatch batch;
	
	public WeaponList wl;
	public EnemyList el;
	
	public GameData gd;
	public int id = 0;;
	
	public BossBody boss;
	
	@Override
	public void create() 
	{	
		batch = new SpriteBatch();
		wl.init();
		el = new EnemyList();
		boss = new BossBody(this);
//		boss.start();
//		setScreen(new SplashScreen(this, "Logo Animation", 
//				"Logo SplashScreen/Parts/Logo Animation.scml").scale(0.3F).
//				setMusic("Audio/title_theme.wav"));
		gd= new GameData();
		setScreen(new GameScreen(this, gd, "SpeedTest"));
		
		
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
