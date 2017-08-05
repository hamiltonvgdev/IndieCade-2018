package Screen;


import Game.Core;
import Util.ScreenshotFactory;
import Util.SpriteAccessor;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TransitionScreen extends ModScreen
{
	ModScreen ns;
	
	private SpriteBatch batch;
	private Sprite splash;
	private TweenManager tweenmanager;
	private float duration;
	
	private Sound music;
	private long musicId;
	private float volume;
	
	public TransitionScreen(Core core, ModScreen nextScreen)
	{
		super(core);
		ScreenshotFactory.saveScreenshot();
		ns = nextScreen;
		duration = 1.5F;
	}
	
	public TransitionScreen setFadeOutMusic(Sound music, long musicId)
	{
		this.music = music;
		this.musicId = musicId;
		volume = 1;
		return this;
	}
	
	@Override
	public void show() 
	{
		super.show();
		tweenmanager = new TweenManager();
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		// TODO Auto-generated method stub
		batch = new SpriteBatch();
		Texture splashTexture = new Texture(Gdx.files.internal("screenshot.png"));
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		splash.flip(false, true);
		Tween.set(splash, SpriteAccessor.ALPHA).target(1).start(tweenmanager);
		Tween.to(splash, SpriteAccessor.ALPHA, duration).target(0).
		setCallback(new TweenCallback()
			{
				public void onEvent(int type,BaseTween<?> source ){
					core.setScreen(ns);
			}
		}).start(tweenmanager);
		
	}

	@Override
	public void render(float delta) 
	{
		super.render(delta);
		
		// TODO Auto-generated method stub

		batch.begin();
		
		splash.draw(batch);
		batch.end();

	}

	@Override
	public void resize(int width, int height) 
	{
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void hide() 
	{
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		super.dispose();
		batch.dispose();
		splash.getTexture().dispose();
		music.stop();
	}
	@Override
	public void update(float delta) 
	{
		// TODO Auto-generated method stub
		tweenmanager.update(delta);
		
		if(music != null)
		{
			volume -= delta / duration;
			music.setVolume(musicId, volume);
		}
	}

}