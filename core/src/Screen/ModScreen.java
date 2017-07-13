package Screen;

import Game.Core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ModScreen implements Screen
{
	Core core;
	
	public ModScreen(Core core)
	{
		this.core = core;
	}
	
	public void create()
	{
		show();
	}
	
	public abstract void update(float delta);
	
	public void render(float delta)
	{
		update(delta);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
	
	public void show()
	{
		core.batch = new SpriteBatch();
		core.batch.begin();
	}
	
	public void hide()
	{
		core.batch.end();
		dispose();
	}
	
	public void dispose(){}
	

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
}
