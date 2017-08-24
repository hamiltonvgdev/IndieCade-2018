package Screen;

import Game.Config;
import Game.Core;
import Renders.SpriterAnimationEngine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.blueacorn.spriter.LibGdxDrawer;
import com.brashmonkey.spriter.Play;

public class SplashScreen extends ModScreen
{
	Play play;
	LibGdxDrawer draw;
	SpriterAnimationEngine renderer;
	OrthographicCamera camera;
	
	long musicId;
	Sound music;
	
	long tick;
	
	public SplashScreen(Core core, String name, String ref) 
	{
		super(core);
		
		camera = new OrthographicCamera(Config.GAME_WIDTH, Config.GAME_HEIGHT);
		
		renderer = new SpriterAnimationEngine(camera.combined);
		
		draw = renderer.getDrawer(name, ref);
		play = renderer.getPlayer(name, ref);
		
		tick = System.currentTimeMillis();
	}
	
	public SplashScreen scale(float scale)
	{
		play.setScale(scale);
		return this;
	}
	
	public SplashScreen speed(int speed)
	{
		play.speed = play.speed;
		return this;
	}

	public SplashScreen setMusic(String ref)
	{
		music = Gdx.audio.newSound(Gdx.files.internal(ref));
		musicId = music.loop();
		return this;
	}
	
	@Override
	public void update(float delta) 
	{
		play.update();
		
		if(System.currentTimeMillis() - tick >= play.getAnimation().length)
		{
			core.setScreen(new TransitionScreen(core, new MenuScreen(core)).
					setFadeOutMusic(music, musicId));
		}
	}
	
	@Override
	public void render(float delta)
	{
		super.render(delta);
		
		draw.draw(play);
	}

}
