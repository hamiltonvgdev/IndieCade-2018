package Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Game.Core;
import Screen.ModScreen;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

public class MapTransition extends ModScreen{
	ModScreen ns;
	
	private SpriteBatch batch;
	private Sprite splash;
	private TweenManager tweenmanager;
	
	public MapTransition(Core core, ModScreen nextScreen)
	{
		super(core);
		ScreenshotFactory.saveScreenshot();
		ns = nextScreen;
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
		Tween.set(splash,SpriteAccessor.ALPHA).target(1).start(tweenmanager);
		Tween.to(splash,SpriteAccessor.ALPHA,2).target(0).
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
	}
	@Override
	public void update(float delta) 
	{
		// TODO Auto-generated method stub
		tweenmanager.update(delta);
	}

}
